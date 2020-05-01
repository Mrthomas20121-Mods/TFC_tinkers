package com.mrthomas20121.tfc_tinker.Client;

import com.mrthomas20121.tfc_tinker.Api.Knapping.KnappingTypes;
import com.mrthomas20121.tfc_tinker.Items.ItemGrout;
import com.mrthomas20121.tfc_tinker.TFC_Tinker;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.util.IRockObject;
import net.dries007.tfc.client.TFCGuiHandler;
import net.dries007.tfc.client.gui.*;
import net.dries007.tfc.objects.container.*;
import net.dries007.tfc.objects.te.*;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.OreDictionaryHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TFCTicGuiHandler implements IGuiHandler {
    private static final ResourceLocation GROOUT_TEXTURE = new ResourceLocation(TFC_Tinker.MODID, "textures/gui/grout_button.png");

    public static void openGui(World world, BlockPos pos, EntityPlayer player, Type type)
    {
        player.openGui(TFC_Tinker.instance, type.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void openGui(World world, EntityPlayer player, Type type)
    {
        player.openGui(TFC_Tinker.instance, type.ordinal(), world, 0, 0, 0);
    }

    @Override
    @Nullable
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        ItemStack stack = player.getHeldItemMainhand();
        Type type = Type.valueOf(ID);
        switch (type)
        {
            case KNAPPING_GROUT:
                return new ContainerKnapping(KnappingTypes.GROUT, player.inventory, stack.getItem() instanceof ItemGrout ? stack : player.getHeldItemOffhand());
            default:
                return null;
        }
    }
    @Nullable
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        Container container = getServerGuiElement(ID, player, world, x, y, z);
        Type type = Type.valueOf(ID);
        BlockPos pos = new BlockPos(x, y, z);
        switch (type)
        {
            case KNAPPING_GROUT:
                return new GuiKnapping(container, player, KnappingTypes.GROUT, GROOUT_TEXTURE);
            default :
                return null;
        }
    }

    public enum Type
    {
        KNAPPING_GROUT,
        NULL; // This is special, it is a non-null null.

        private static final Type[] values = values();

        @Nonnull
        public static Type valueOf(int id)
        {
            return id < 0 || id >= values.length ? NULL : values[id];
        }
    }
}
