package mrthomas20121.tfc_tinker.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import mrthomas20121.tfc_tinker.Api.Knapping.KnappingTypes;
import mrthomas20121.tfc_tinker.Client.TFCTicGuiHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.dries007.tfc.util.interaction.IRightClickBlockAction;
import net.dries007.tfc.util.interaction.IRightClickItemAction;

import static mrthomas20121.tfc_tinker.TFC_Tinker.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class InteractionManager {
    private static final Map<Predicate<ItemStack>, IRightClickBlockAction> USE_ACTIONS = new HashMap<>();
    private static final Map<Predicate<ItemStack>, IRightClickItemAction> RIGHT_CLICK_ACTIONS = new HashMap<>();
    static {
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "grout") && stack.getCount() >= KnappingTypes.GROUT.getAmountToConsume(), (worldIn, playerIn, handIn) -> {
            if (!worldIn.isRemote)
            {
                TFCTicGuiHandler.openGui(worldIn, playerIn, TFCTicGuiHandler.Type.KNAPPING_GROUT);
            }
            return EnumActionResult.SUCCESS;
        });
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        IRightClickBlockAction action = InteractionManager.findItemUseAction(event.getItemStack());
        if (action != null)
        {
            // Use alternative handling
            EnumActionResult result;
            if (event.getSide() == Side.CLIENT)
            {
                result = ClientInteractionManager.processRightClickBlock(event, action);
            }
            else
            {
                result = ServerInteractionManager.processRightClickBlock(event, action);
            }
            event.setCancellationResult(result);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event)
    {
        IRightClickItemAction action = InteractionManager.findItemRightClickAction(event.getItemStack());
        if (action != null)
        {
            // Use alternative handling
            EnumActionResult result;
            if (event.getSide() == Side.CLIENT)
            {
                result = ClientInteractionManager.processRightClickItem(event, action);
            }
            else
            {
                result = ServerInteractionManager.processRightClickItem(event, action);
            }
            event.setCancellationResult(result);
            event.setCanceled(true);
        }
    }

    @Nullable
    private static IRightClickBlockAction findItemUseAction(ItemStack stack)
    {
        return USE_ACTIONS.entrySet().stream().filter(e -> e.getKey().test(stack)).findFirst().map(Map.Entry::getValue).orElse(null);
    }
    @Nullable
    private static IRightClickItemAction findItemRightClickAction(ItemStack stack)
    {
        return RIGHT_CLICK_ACTIONS.entrySet().stream().filter(e -> e.getKey().test(stack)).findFirst().map(Map.Entry::getValue).orElse(null);
    }
    private static void putBoth(Predicate<ItemStack> predicate, IRightClickItemAction minorAction)
    {
        USE_ACTIONS.put(predicate, minorAction);
        RIGHT_CLICK_ACTIONS.put(predicate, minorAction);
    }
}