package mrthomas20121.tfc_tinker.registry;

import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

import static mrthomas20121.tfc_tinker.items.Items.*;

public class ItemsRegistry {

    public static ArrayList<ItemTFC> items = new ArrayList<>();

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        register(r, grout, "grout", "itemGrout");
        register(r, grout_brick, "itemBrickGrout", "brickGrout");
        register(r, castDoubleIngot, "cast");
        register(r, castDoubleSheet, "cast");
        register(r, castSheet, "cast");
        register(r, castTuyere, "cast");
        register(r, castScrap, "cast");
        register(r, castWire, "cast");
        register(r, castLongRod, "cast");
        register(r, castRackwheel, "cast");
        register(r, castRackwheelPiece, "cast");
        register(r, castBlowpipe, "cast");
    }
    private static <T extends ItemTFC> void register(IForgeRegistry<Item> r, T item, String ...oredicts)
    {
        register(r, item);
        for(String oredict : oredicts)
        {
            OreDictionary.registerOre(oredict, item);
        }
    }
    private static <T extends ItemTFC> void register(IForgeRegistry<Item> r, T item)
    { ;
        r.register(item);
        items.add(item);
    }
}
