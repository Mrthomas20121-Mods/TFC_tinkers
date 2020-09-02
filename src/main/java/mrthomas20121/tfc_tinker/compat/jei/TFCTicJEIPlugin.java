package mrthomas20121.tfc_tinker.compat.jei;

import mezz.jei.api.JEIPlugin;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.api.knapping.KnappingTypes;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.client.gui.GuiKnapping;
import net.dries007.tfc.compat.jei.categories.KnappingCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.stream.Collectors;

@JEIPlugin
public class TFCTicJEIPlugin implements IModPlugin {
    public static final String KNAP_GROUT_UID = TerraFirmaCraft.MOD_ID + ".knap.grout";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_GROUT_UID));
    }

    @Override
    public void register(IModRegistry registry)
    {
        List<KnappingGroutRecipeWrapper> groutknapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.GROUT)
                .map(recipe -> new KnappingGroutRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(groutknapRecipes, KNAP_GROUT_UID);
        NonNullList<ItemStack> ores = OreDictionary.getOres("grout");
        for(ItemStack itemStack : ores)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_GROUT_UID);
        }
        registry.addRecipeClickArea(GuiKnapping.class, 97, 44, 22, 15, KNAP_GROUT_UID);
    }
}
