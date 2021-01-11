package mrthomas20121.tfc_tinker.registry;

import mrthomas20121.tfc_tinker.api.knapping.TFCTinkerKnappingType;
import mrthomas20121.tfc_tinker.objects.items.TFCTinkerItems;
import net.dries007.tfc.api.recipes.heat.HeatRecipe;
import net.dries007.tfc.api.recipes.heat.HeatRecipeSimple;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipeSimple;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static mrthomas20121.tfc_tinker.TFC_Tinker.MODID;
import static slimeknights.tconstruct.shared.TinkerCommons.searedBrick;

@Mod.EventBusSubscriber(modid = MODID)
public final class TFCTicRecipes {

    @SubscribeEvent
    public static void onRegisterKnappingRecipeEvent(RegistryEvent.Register<KnappingRecipe> event)
    {
        IForgeRegistry<KnappingRecipe> r = event.getRegistry();

        r.register(
                new KnappingRecipeSimple(TFCTinkerKnappingType.GROUT, false, new ItemStack(TFCTinkerItems.grout_brick, 2), "     ", "XXXXX", "     ", "XXXXX", "     ").setRegistryName(MODID, "grount_brick")
        );
    }

    @SubscribeEvent
    public static void onRegisterHeatRecipeEvent(RegistryEvent.Register<HeatRecipe> event)
    {
        event.getRegistry().register(
                new HeatRecipeSimple(IIngredient.of(new ItemStack(TFCTinkerItems.grout_brick)), searedBrick, 600f, Metal.Tier.TIER_I).setRegistryName("seared_brick"));

    }
}

