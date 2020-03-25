package com.mrthomas20121.tinkerfirmacraft.ToolPart;

import com.mrthomas20121.tinkerfirmacraft.Tool.ToolProspectorPick;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolPart;

public class Parts {

    public static ToolPart proPick;
    public static ToolProspectorPick prospectingPick;

    public static void registerItems(RegistryEvent.Register<Item> event) {
        proPick = new ToolPart(Material.VALUE_Ingot*2);
        proPick.setRegistryName("propick_head");
        event.getRegistry().register(proPick);
        TinkerRegistry.registerToolPart(proPick);

        // prospecting Pick
        prospectingPick = new ToolProspectorPick();
        prospectingPick.setRegistryName("propick");
        event.getRegistry().register(prospectingPick);
    }
    public static void init(FMLInitializationEvent e) {
        TinkerRegistry.registerToolCrafting(prospectingPick);
    }
}
