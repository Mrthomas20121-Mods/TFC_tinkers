package mrthomas20121.tfc_tinker.compat.tinkers_construct.tool;

import java.util.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mrthomas20121.tfc_tinker.compat.tinkers_construct.Parts;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.metal.ItemProspectorPick;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.AoeToolCore;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolNBT;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

public class ToolProspectorPick extends AoeToolCore {
    public static final float DURABILITY_MODIFIER = 1.1f;

    public ToolProspectorPick() {
        super(PartMaterialType.handle(TinkerTools.toolRod),
                PartMaterialType.head(Parts.proPickHead),
                PartMaterialType.extra(TinkerTools.binding));
        addCategory(Category.TOOL);
    }

    @Override
    public ToolNBT buildTagData(List<Material> materials) {
        HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
        HeadMaterialStats head = materials.get(1).getStatsOrUnknown(MaterialTypes.HEAD);
        ToolNBT data = new ToolNBT();
        data.head(head);
        data.handle(handle);

        data.harvestLevel = head.harvestLevel;
        data.durability *= DURABILITY_MODIFIER;

        return data;
    }

    @Override
    public float damagePotential() {
        return 0.7f;
    }
    @Override
    public double attackSpeed() {
        return 0.9F;
    }
    @Override
    public float getRepairModifierForPart(int index) {
        return DURABILITY_MODIFIER;
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, @Nullable EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if(ToolHelper.isBroken(stack)) {
            return EnumActionResult.FAIL;
        }
        else {
            return ItemProspectorPick.get(Metal.BISMUTH_BRONZE, Metal.ItemType.PROPICK).onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
    }
}
