package com.mrthomas20121.tfc_tinker.Items;

import com.mrthomas20121.tfc_tinker.TFC_Tinker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemGroutBrick extends Item {

    private float heatCapacity, meltTemp;

    public ItemGroutBrick(String name, float heatCapacity, float meltTemp) {
        this.setCreativeTab(CreativeTabs.MISC);
        this.setRegistryName(TFC_Tinker.MODID, name);
        this.setTranslationKey(TFC_Tinker.MODID+"."+name);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        return new ItemHeatHandler(nbt, this.heatCapacity, this.meltTemp);
    }
}
