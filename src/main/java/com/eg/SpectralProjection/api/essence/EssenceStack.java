package com.eg.SpectralProjection.api.essence;

import com.eg.SpectralProjection.util.nbt.Tags;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class EssenceStack {
    public Essence essence;
    public int amount;

    public EssenceStack(Essence essence, int amount){
        this.essence = essence;
        this.amount = amount;
    }

    public void writeToNBT(String key, NBTTagCompound compound){
        NBTTagCompound c = new NBTTagCompound();

        c.setString(Tags.Name, essence.name);
        c.setInteger(Tags.Amount, amount);

        compound.setTag(key, c);
    }

    public static EssenceStack readFromNBT(String key, NBTTagCompound compound){
        NBTTagCompound c = compound.getCompoundTag(key);

        Essence essence = Essences.getEssence(c.getString(Tags.Name));
        int amount = c.getInteger(Tags.Amount);

        return new EssenceStack(essence, amount);
    }
}
