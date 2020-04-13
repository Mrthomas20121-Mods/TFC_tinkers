package com.mrthomas20121.tfc_tinker.ToolPart;

import com.mrthomas20121.tfc_tinker.Tool.ToolProspectorPick;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import com.mrthomas20121.tfc_tinker.TFC_Tinker;
import slimeknights.tconstruct.tools.TinkerTools;

public class Parts {

    public static ToolPart proPickHead;
    public static ToolCore prospectorPick;

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        proPickHead = new ToolPart(Material.VALUE_Ingot);
        proPickHead.setRegistryName(TFC_Tinker.MODID,"propick_head");
        proPickHead.setTranslationKey(TFC_Tinker.MODID + ".propick_head");
        TFC_Tinker.proxy.registerToolPartModel(proPickHead);
        r.register(proPickHead);

        // prospecting Pick
        prospectorPick = new ToolProspectorPick();
        prospectorPick.setRegistryName(TFC_Tinker.MODID, "prospector_pick");
        prospectorPick.setTranslationKey(TFC_Tinker.MODID + ".prospector_pick");
        TFC_Tinker.proxy.registerToolModel(prospectorPick);
        r.register(prospectorPick);

    }
    public static void init(FMLInitializationEvent e) {

        TFC_Tinker.logger.info("Loading Tool Station/Forge Module");
        TinkerRegistry.registerToolCrafting(prospectorPick);

        TFC_Tinker.logger.info("Loading Stencil");
        registerStencil(proPickHead);
    }
    public static void postInit() {
        int[][] arr = { {25,40},{40,28},{14,62}};
        registerToolForgeGui(prospectorPick,arr);
    }
    private static void registerStencil(ToolPart part) {
        TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), part));
    }
    private static void registerToolForgeGui(ToolCore tool, int[][] pos) {
        ToolBuildGuiInfo t = new ToolBuildGuiInfo(tool);
        for(int[] p : pos) {
            t.addSlotPosition(p[0], p[1]);
        }
        TinkerRegistryClient.addToolBuilding(t);
    }
}
