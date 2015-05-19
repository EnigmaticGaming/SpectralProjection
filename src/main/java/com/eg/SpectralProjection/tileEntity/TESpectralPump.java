package com.eg.SpectralProjection.tileEntity;

import com.eg.SpectralProjection.api.essence.EssenceStack;
import com.eg.SpectralProjection.api.essence.IEssenceHandler;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class TESpectralPump extends TEBase implements IUpdatePlayerListBox{
    BlockPos target;

    @Override
    public void update() {
        if(worldObj == null ||worldObj.isRemote || target == null){
            return;
        }

        TileEntity tileEntity = worldObj.getTileEntity(target);
        if(tileEntity instanceof IEssenceHandler){
            IEssenceHandler target = (IEssenceHandler)tileEntity;

            if(target.canBind()) {
                tileEntity = worldObj.getTileEntity(pos.down());
                if (tileEntity instanceof IEssenceHandler) {
                    IEssenceHandler source = (IEssenceHandler) tileEntity;
                    EssenceStack essenceStack = source.pullEssence(null, 10, true);

                    if(essenceStack != null) {
                        essenceStack.amount = target.pushEssence(essenceStack, false);
                        source.pullEssence(essenceStack.essence, essenceStack.amount, false);
                    }
                }
            }
        }
    }
}
