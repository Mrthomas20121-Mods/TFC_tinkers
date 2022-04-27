package mrthomas20121.tfc_tinkers;

import mrthomas20121.tfc_tinkers.datagen.TFCMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public static void datagen(GatherDataEvent event) {
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        DataGenerator gen = event.getGenerator();

        AbstractMaterialDataProvider materials = new TFCMaterials(gen);

        if(event.includeClient()) {
            AbstractMaterialSpriteProvider provider = new TFCMaterials.TFCMaterialSpritProvider();
            gen.addProvider(new TFCMaterials.TFCMaterialRenderInfo(gen, provider));
            gen.addProvider(new MaterialPartTextureGenerator(gen, fileHelper, new TinkerPartSpriteProvider(), provider));
        }

        if(event.includeServer()) {
            gen.addProvider(materials);
            gen.addProvider(new TFCMaterials.TFCMaterialTraits(gen, materials));
            gen.addProvider(new TFCMaterials.TFCMaterialStatsProvider(gen, materials));
        }

    }
}
