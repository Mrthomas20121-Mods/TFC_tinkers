package mrthomas20121.tfc_tinker.client;

import mrthomas20121.tfc_tinker.api.knapping.TFCTinkerKnappingType;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.client.gui.GuiKnapping;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class TFCTinkerKnappingGui extends GuiKnapping {

    private KnappingType type = null;

    public TFCTinkerKnappingGui(Container container, EntityPlayer player, KnappingType type, ResourceLocation buttonTexture)
    {
        super(container, player, type, buttonTexture);
        this.type = type;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        if (type.equals(TFCTinkerKnappingType.GROUT))
        {
            GlStateManager.color(1, 1, 1, 1);
            mc.getTextureManager().bindTexture(TFCTicGuiHandler.GROOUT_TEXTURE_DISABLED);
            for (GuiButton button : buttonList)
            {
                if (!button.visible)
                {
                    Gui.drawModalRectWithCustomSizedTexture(button.x, button.y, 0, 0, 16, 16, 16, 16);
                }
            }
        }
    }
}
