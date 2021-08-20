package mrthomas20121.tfc_tinker.objects.items;

import mrthomas20121.rocksalt.Utils;
import mrthomas20121.tfc_tinker.objects.Cast;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class TFCTinkerItems {
    @GameRegistry.ObjectHolder("tfc_tinker:grout_ball")
    public static Item grout;

    @GameRegistry.ObjectHolder("tfc_tinker:grout_brick")
    public static Item grout_brick;

    private static ArrayList<Item> items = new ArrayList<>();

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static String[] metals = {"sterling_silver", "rose_gold", "brass"};

    public static void init(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();
        register(r, new ItemGrout("grout_ball"));
        register(r, new ItemGroutBrick("grout_brick", 0.20f, 410));

        for(String metalName: metals)
        {
            for(Cast cast: Cast.values())
            {
                register(r, new ItemCast(Utils.getMetal(metalName), cast));
            }
        }
    }

    public static <T extends Item> T register(IForgeRegistry<Item> r,T item)
    {
        r.register(item);
        items.add(item);
        return item;
    }
}
