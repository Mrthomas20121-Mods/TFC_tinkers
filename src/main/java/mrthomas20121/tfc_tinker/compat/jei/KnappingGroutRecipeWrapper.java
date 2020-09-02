package mrthomas20121.tfc_tinker.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mrthomas20121.tfc_tinker.items.Items;
import mrthomas20121.tfc_tinker.TFC_Tinker;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
import net.dries007.tfc.compat.jei.wrappers.KnappingRecipeWrapper;
import net.dries007.tfc.util.Helpers;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class KnappingGroutRecipeWrapper extends KnappingRecipeWrapper {

    private static final ResourceLocation GROUT_TEXTURE = new ResourceLocation(TFC_Tinker.MODID, "textures/gui/grout_button.png");

    public KnappingGroutRecipeWrapper(KnappingRecipe recipe, IGuiHelper helper)
    {
        super(recipe, helper, GROUT_TEXTURE, null);

    }
    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setOutputLists(VanillaTypes.ITEM, Helpers.listOf(Helpers.listOf(recipe.getOutput(new ItemStack(Items.grout, 1)))));
    }
}
