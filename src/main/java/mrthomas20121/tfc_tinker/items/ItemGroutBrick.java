package mrthomas20121.tfc_tinker.items;

import mrthomas20121.tfc_tinker.TFC_Tinker;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.creativetab.CreativeTabs;
import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemGroutBrick extends ItemTFC {

    private final float heatCapacity = 500;
    private final float meltTemp = 450;

    public ItemGroutBrick(String name, float heatCapacity, float meltTemp) {
        this.setCreativeTab(CreativeTabs.MISC);
        this.setRegistryName(TFC_Tinker.MODID, name);
        this.setTranslationKey(TFC_Tinker.MODID+"."+name);
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.LIGHT;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        return new ItemHeatHandler(nbt, this.heatCapacity, this.meltTemp);
    }
}
