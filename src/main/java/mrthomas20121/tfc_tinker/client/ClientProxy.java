package mrthomas20121.tfc_tinker.client;
import mrthomas20121.tfc_tinker.CommonProxy;
import mrthomas20121.tfc_tinker.compat.tinkers_construct.MaterialTFC;
import mrthomas20121.tfc_tinker.objects.blocks.TFCTinkerBlocks;
import mrthomas20121.tfc_tinker.objects.items.TFCTinkerItems;
import mrthomas20121.tfc_tinker.registry.RegistryHandler;
import mrthomas20121.tfc_tinker.compat.tinkers_construct.Parts;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        Parts.postInit();
        for(MaterialTFC wrapper: RegistryHandler.materials)
        {
            Material material = wrapper.getMaterial();
            material.setRenderInfo(material.materialTextColor);
        }
    }

    @Override
    public <T extends Item & IToolPart> void registerToolPartModel(T part) {
        ModelRegisterUtil.registerPartModel(part);
    }

    @Override
    public void registerToolModel(ToolCore tc) {
        ModelRegisterUtil.registerToolModel(tc);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {

        for(Item item : TFCTinkerItems.getItems())
        {
            ModelLoader.setCustomModelResourceLocation(item, 0 , new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }

        for(Block block : TFCTinkerBlocks.getFluidBlocks())
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
            ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
                @Override
                public ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(block.getRegistryName(), "normal");
                }});
            //ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFluidBase.LEVEL).build());
        }
    }
}
