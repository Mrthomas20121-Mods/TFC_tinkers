package com.mrthomas20121.tinkerfirmacraft.ToolPart;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolPart;

public class Parts {


    public static ToolPart proPick;

    public static void registerItems(RegistryEvent.Register<Item> event) {
        proPick = new ToolPart(Material.VALUE_Ingot*2);
        proPick.setRegistryName("propick");
        event.getRegistry().register(proPick);
        TinkerRegistry.registerToolPart(proPick);
        
    }
}
