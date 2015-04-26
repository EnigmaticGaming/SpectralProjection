package com.eg.SpectralProjection.item;

import com.eg.SpectralProjection.api.essence.Essence;
import com.eg.SpectralProjection.api.essence.EssenceStack;
import com.eg.SpectralProjection.api.essence.Essences;
import com.eg.SpectralProjection.api.essence.IEssenceHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by Creysys on 26 Apr 15.
 */
public class ItemEssencemeter extends ItemBase {
    public ItemEssencemeter() {
        super("essencemeter");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {

        if(world.isRemote){
            return false;
        }

        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof IEssenceHandler){
            String msg = "Essences contained:";
            boolean essencesContained = false;

            IEssenceHandler essenceHandler = (IEssenceHandler)tileEntity;
            ArrayList<Essence> essences = Essences.getRegisteredEssences();
            for(int i = 0; i < essences.size(); i++){
                EssenceStack essenceStack = essenceHandler.pullEssence(essences.get(i), Integer.MAX_VALUE, true);

                if(essenceStack != null && essenceStack.amount > 0){
                    msg += "\n" + essenceStack.toString();
                    essencesContained = true;
                }
            }

            if(!essencesContained){
                msg = "No essences contained.";
            }

            player.addChatMessage(new ChatComponentText(msg));
            return true;
        }

        return false;
    }
}
