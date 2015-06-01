package com.eg.SpectralProjection.tileEntity;

import com.eg.SpectralProjection.api.essence.Essence;
import com.eg.SpectralProjection.api.essence.EssenceStack;
import com.eg.SpectralProjection.api.essence.IEssenceHandler;
import com.eg.SpectralProjection.api.recipe.RecipeManager;
import com.eg.SpectralProjection.api.recipe.RecipeSoulforriumFurnace;
import com.eg.SpectralProjection.block.BlockSoulforriumFurnace;
import com.eg.SpectralProjection.net.SPNet;
import com.eg.SpectralProjection.net.packet.PacketTESync;
import com.eg.SpectralProjection.util.helper.HelperEssence;
import com.eg.SpectralProjection.util.helper.HelperItem;
import com.eg.SpectralProjection.util.helper.HelperNBT;
import com.eg.SpectralProjection.util.interfaces.ITESyncHandler;
import com.eg.SpectralProjection.util.nbt.Tags;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class TESoulforriumFurnace extends TEBase implements IEssenceHandler, ITESyncHandler, IUpdatePlayerListBox, ISidedInventory {

    public final int ESSENCE_CAPACITY = 100;

    public ItemStack[] slots;
    public EssenceStack essenceStack;
    public EssenceStack essenceBuffer;

    public int burningTime;
    public int totalBurningTime;

    private Object craftingOutput;
    public int craftingTime;
    public int totalCraftingTime;

    public TESoulforriumFurnace(){
        slots = new ItemStack[getSizeInventory()];

        burningTime = 0;
        totalBurningTime = 0;

        craftingOutput = null;
        craftingTime = 0;
        totalCraftingTime = 0;
    }

    private boolean canCraft(Object obj) {
        if (burningTime <= 0) {
            return false;
        }

        if (obj instanceof ItemStack) {
            return HelperItem.canMergeStacks(slots[2], (ItemStack) obj);
        } else if (obj instanceof EssenceStack) {
            return essenceBuffer == null;
        }

        return false;
    }

    private void craft(){
        slots[1].stackSize--;
        if(slots[1].stackSize <= 0){
            slots[1] = null;
        }

        if (craftingOutput instanceof ItemStack) {
            slots[2] = HelperItem.mergeStacks(slots[2], (ItemStack) craftingOutput);
        } else if (craftingOutput instanceof EssenceStack) {
            essenceBuffer = ((EssenceStack) craftingOutput).copy();
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return  oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        HelperNBT.writeInventory(slots, compound);

        if(essenceStack != null) {
            essenceStack.writeToNBT(Tags.Essence, compound);
        }

        if(essenceBuffer != null) {
            essenceBuffer.writeToNBT(Tags.Buffer, compound);
        }

        compound.setInteger(Tags.BurnTime, burningTime);
        compound.setInteger(Tags.TotalBurnTime, totalBurningTime);
        compound.setInteger(Tags.CraftTime, craftingTime);
        compound.setInteger(Tags.TotalCraftTime, totalCraftingTime);

        if(craftingOutput instanceof ItemStack){
            NBTTagCompound inner = new NBTTagCompound();
            ((ItemStack) craftingOutput).writeToNBT(inner);
            compound.setTag(Tags.CraftOutput, inner);
        }
        else if(craftingOutput instanceof EssenceStack){
            ((EssenceStack) craftingOutput).writeToNBT(Tags.CraftOutput, compound);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        slots = HelperNBT.readInventory(compound, slots.length);

        if(compound.hasKey(Tags.Essence)) {
            essenceStack = EssenceStack.readFromNBT(Tags.Essence, compound);
        }

        if(compound.hasKey(Tags.Buffer)) {
            essenceBuffer = EssenceStack.readFromNBT(Tags.Buffer, compound);
        }

        burningTime = compound.getInteger(Tags.BurnTime);
        totalBurningTime = compound.getInteger(Tags.TotalBurnTime);
        craftingTime = compound.getInteger(Tags.CraftTime);
        totalCraftingTime = compound.getInteger(Tags.TotalCraftTime);

        if(compound.hasKey(Tags.CraftOutput)){
            NBTTagCompound inner = compound.getCompoundTag(Tags.CraftOutput);
            if(inner.hasKey("id")){
                craftingOutput = ItemStack.loadItemStackFromNBT(inner);
            }
            else {
                craftingOutput = EssenceStack.readFromNBT(compound);
            }
        }
    }

    @Override
    public EssenceStack pullEssence(Essence essence, int max, boolean simulate) {
        if(simulate && essenceBuffer != null && essenceBuffer.essence == essence){
            return essenceBuffer;
        }

        if(essenceStack == null || (essence != null && essenceStack.essence != essence)){
            return null;
        }

        int pulled = Math.min(max, essenceStack.amount);

        if(!simulate) {
            essenceStack.amount -= pulled;
            if (essenceStack.amount <= 0) {
                essenceStack = null;
            }
        }

        return new EssenceStack(essence, pulled);
    }

    @Override
    public int pushEssence(EssenceStack stack, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canBind() {
        return false;
    }

    @Override
    public void handleTESync(byte key, Object value)
    {
        if (value instanceof Integer)
        {
            if (key == 0)
            {
                burningTime = (Integer) value;
            }
            else if (key == 1)
            {
                totalBurningTime = (Integer) value;
            }
            else if (key == 2)
            {
                craftingTime = (Integer) value;
            }
            else if (key == 3)
            {
                totalCraftingTime = (Integer) value;
            }
        }
    }

    @Override
    public void update() {
        if(worldObj.isRemote){
            return;
        }

        if(essenceBuffer != null) {
            if(HelperEssence.canMergeStacks(essenceStack, essenceBuffer, ESSENCE_CAPACITY)){
                essenceStack = HelperEssence.mergeStacks(essenceStack, essenceBuffer, ESSENCE_CAPACITY);
                essenceBuffer = null;
            }
        }


        if(burningTime > 0){
            burningTime--;
            if(burningTime <= 0 && slots[0] == null){
                worldObj.setBlockState(getPos(), worldObj.getBlockState(getPos()).withProperty(BlockSoulforriumFurnace.ACTIVE, false));
            }

            if(craftingOutput == null){
                for(RecipeSoulforriumFurnace recipe : RecipeManager.soulforriumFurnace){
                    if(recipe.matches(slots[1])){
                        Object obj = recipe.getOutput();

                        if(canCraft(obj)) {
                            craftingOutput = obj;
                            craftingTime = recipe.craftingTime;
                            totalCraftingTime = craftingTime;

                            SPNet.sendToAllAround(new PacketTESync(getPos(), (byte)3, totalCraftingTime), new NetworkRegistry.TargetPoint(worldObj.provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ(), 8));
                            break;
                        }
                    }
                }
            }
            else {
                craftingTime--;
                if(craftingTime <= 0){
                    craft();

                    craftingOutput = null;
                    craftingTime = 0;
                }
            }
        }
        else {
            if(slots[0] != null) {
                burningTime = TileEntityFurnace.getItemBurnTime(slots[0]);
                totalBurningTime = burningTime;
                slots[0].stackSize--;
                if(slots[0].stackSize <= 0){
                    slots[0] = null;
                }

                worldObj.setBlockState(getPos(), worldObj.getBlockState(getPos()).withProperty(BlockSoulforriumFurnace.ACTIVE, true));

                SPNet.sendToAllAround(new PacketTESync(getPos(), (byte)1, totalBurningTime), new NetworkRegistry.TargetPoint(worldObj.provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ(), 8));
            }
        }

        if(worldObj.getTotalWorldTime() % 20 == 0){
            SPNet.sendToAllAround(new PacketTESync(pos, (byte)0, burningTime), new NetworkRegistry.TargetPoint(worldObj.provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ(), 8));
            SPNet.sendToAllAround(new PacketTESync(pos, (byte)2, craftingTime), new NetworkRegistry.TargetPoint(worldObj.provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ(), 8));
        }
    }

    //Inventory
    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN) {
            return new int[]{0};
        }
        return new int[]{1, 2};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing direction) {
        return direction != EnumFacing.DOWN || TileEntityFurnace.isItemFuel(itemStack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return direction != EnumFacing.DOWN;
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return slots[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (slots[index] != null)
        {
            ItemStack itemstack;

            if (slots[index].stackSize <= count)
            {
                itemstack = slots[index];
                slots[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = slots[index].splitStack(count);

                if (slots[index].stackSize == 0)
                {
                    slots[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (slots[index] != null)
        {
            ItemStack itemstack = slots[index];
            slots[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        slots[index] = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {    }

    @Override
    public void closeInventory(EntityPlayer player) {    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == 1 || (index == 0 && TileEntityFurnace.isItemFuel(stack));
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for(int i = 0; i < slots.length; i++){
            slots[i] = null;
        }

        essenceStack = null;
    }

    @Override
    public String getName() {
        return getBlockType().getLocalizedName();
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(getName());
    }
}
