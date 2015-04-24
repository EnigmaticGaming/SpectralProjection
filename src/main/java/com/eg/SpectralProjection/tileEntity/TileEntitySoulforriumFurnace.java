package com.eg.SpectralProjection.tileEntity;

import com.eg.SpectralProjection.api.essence.Essence;
import com.eg.SpectralProjection.api.essence.EssenceStack;
import com.eg.SpectralProjection.api.essence.IEssenceHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class TileEntitySoulforriumFurnace extends TileEntity implements IEssenceHandler {

    private EssenceStack essenceStack;

    @Override
    public EssenceStack pullEssence(Essence essence, int max, boolean simulate) {

        if(essenceStack == null || (essence != null && essenceStack.essence != essence)){
            return null;
        }

        int pulled = Math.min(max, essenceStack.amount);

        essenceStack.amount -= pulled;
        if(essenceStack.amount <= 0){
            essenceStack = null;
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
}
