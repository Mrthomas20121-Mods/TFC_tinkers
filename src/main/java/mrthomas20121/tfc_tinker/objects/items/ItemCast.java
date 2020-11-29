package mrthomas20121.tfc_tinker.objects.items;

import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.objects.Cast;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.TinkerRegistry;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ItemCast extends ItemTFC {

    private static Map<Metal, EnumMap<Cast, ItemCast>> table = new HashMap<>();

    public static Item get(Metal metal, Cast type)
    {
        return table.get(metal).get(type);
    }

    public static ItemStack get(Metal metal, Cast type, int amount)
    {
        return new ItemStack(table.get(metal).get(type), amount);
    }

    public ItemCast(Metal metal, Cast type)
    {
        String name = metal.getRegistryName().getPath();
        this.setCreativeTab(TinkerRegistry.tabSmeltery);
        this.setRegistryName(TFC_Tinker.MODID, "cast/"+type.name().toLowerCase()+"/"+name);
        this.setTranslationKey(TFC_Tinker.MODID+".cast."+type.name().toLowerCase()+"."+name);

        if (!table.containsKey(metal))
            table.put(metal, new EnumMap<>(Cast.class));
        table.get(metal).put(type, this);

    }
    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.NORMAL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.LIGHT;
    }
}
