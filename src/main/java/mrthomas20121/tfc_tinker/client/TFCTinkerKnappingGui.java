package mrthomas20121.tfc_tinker.client;

import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.client.gui.GuiKnapping;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class TFCTinkerKnappingGui extends GuiKnapping {

    public TFCTinkerKnappingGui(Container container, EntityPlayer player, KnappingType type, ResourceLocation buttonTexture)
    {
        super(container, player, type, buttonTexture);
    }
}
