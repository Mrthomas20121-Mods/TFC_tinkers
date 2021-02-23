package mrthomas20121.tfc_tinker.compat.tinkers_construct.tool;

import java.util.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mrthomas20121.tfc_tinker.compat.tinkers_construct.Parts;
import net.dries007.tfc.api.events.ProspectEvent;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.network.PacketProspectResult;
import net.dries007.tfc.objects.items.metal.ItemProspectorPick;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.AoeToolCore;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolNBT;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.player.CapabilityPlayerData;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.util.skills.ProspectingSkill;
import net.dries007.tfc.util.skills.SkillType;
import net.dries007.tfc.world.classic.worldgen.vein.VeinRegistry;
import net.dries007.tfc.world.classic.worldgen.vein.VeinType;
import slimeknights.tconstruct.tools.TinkerTools;
import net.dries007.tfc.objects.items.metal.ItemProspectorPick.ProspectResult.Type;

public class ToolProspectorPick extends AoeToolCore {
    private static final int COOLDOWN = 10;
    private static final Random RANDOM = new Random();
    private static final int PROSPECT_RADIUS = 12;
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
