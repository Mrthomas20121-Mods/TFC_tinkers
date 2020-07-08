package mrthomas20121.tfc_tinker.Items;

import mrthomas20121.tfc_tinker.Api.Types.Type;
import mrthomas20121.tfc_tinker.Client.TFCTicGuiHandler;
import mrthomas20121.tfc_tinker.TFC_Tinker;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemGrout extends ItemTFC {

    public ItemGrout(String name) {
        this.setMaxDamage(0);
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

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote && !player.isSneaking() && stack.getCount() > 1)
        {
            TFCTicGuiHandler.openGui(world, player.getPosition(), player, Type.KNAPPING_GROUT);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
