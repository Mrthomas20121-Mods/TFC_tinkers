package mrthomas20121.tfc_tinker.objects.items;

import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.objects.Cast;
import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.smeltery.ICast;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ItemCast extends ItemTFC implements IMetalItem, ICast {

    private static Map<Metal, EnumMap<Cast, ItemCast>> table = new HashMap<>();

    public static Item get(Metal metal, Cast type)
    {
        return table.get(metal).get(type);
    }

    public static ItemStack get(Metal metal, Cast type, int amount)
    {
        return new ItemStack(table.get(metal).get(type), amount);
    }

    private Metal metal;

    public ItemCast(Metal metal, Cast type)
    {
        this.metal = metal;
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

    @Nullable
    @Override
    public Metal getMetal(ItemStack itemStack) {
        return metal;
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack) {
        return 100;
    }

    @Override
    public float getMeltTemp(ItemStack stack) {
        return metal.getMeltTemp();
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        return new ForgeableHeatableHandler(nbt, metal.getSpecificHeat(), metal.getMeltTemp());
    }
}
