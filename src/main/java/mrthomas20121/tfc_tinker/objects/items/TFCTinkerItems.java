package mrthomas20121.tfc_tinker.objects.items;

import com.google.common.collect.Lists;
import mrthomas20121.rocksalt.utils.MetalUtils;
import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.config.ConfigTic;
import mrthomas20121.tfc_tinker.objects.Cast;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;

import java.util.ArrayList;

public class TFCTinkerItems {
    public static ItemGrout grout;
    public static ItemGroutBrick grout_brick;

    private static ArrayList<Item> items = new ArrayList<>();

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static String[] metals = {"sterling_silver", "rose_gold", "brass"};

    public static void init(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();
        grout = register(r, new ItemGrout("grout_ball"));
        grout_brick = register(r, new ItemGroutBrick("grout_brick", 0.20f, 410));

        for(String metalName: metals)
        {
            for(Cast cast: Cast.values())
            {
                register(r, new ItemCast(MetalUtils.getMetal(metalName), cast));
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
