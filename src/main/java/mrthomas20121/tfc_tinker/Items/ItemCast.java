package mrthomas20121.tfc_tinker.Items;

import mrthomas20121.tfc_tinker.TFC_Tinker;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemCast extends ItemTFC {
    public ItemCast(String name)
    {
        this.setCreativeTab(CreativeTabs.MISC);
        this.setRegistryName(TFC_Tinker.MODID, "cast/"+name);
        this.setTranslationKey(TFC_Tinker.MODID+".cast."+name);
    }
    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.NORMAL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.LIGHT;
    }
}
