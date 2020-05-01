package com.mrthomas20121.tfc_tinker.registry;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.mrthomas20121.tfc_tinker.Items.Items.grout;
import static com.mrthomas20121.tfc_tinker.Items.Items.grout_brick;
import static com.mrthomas20121.tfc_tinker.TFC_Tinker.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ItemsRegistry {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        r.registerAll(
                grout,
                grout_brick
        );
    }
}
