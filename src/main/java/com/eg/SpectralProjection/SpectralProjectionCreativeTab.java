package com.eg.SpectralProjection;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class SpectralProjectionCreativeTab extends CreativeTabs {
    public SpectralProjectionCreativeTab() {
        super(SpectralProjection.modid);
    }

    @Override
    public Item getTabIconItem() {
        return SpectralProjection.itemTest;
    }
}
