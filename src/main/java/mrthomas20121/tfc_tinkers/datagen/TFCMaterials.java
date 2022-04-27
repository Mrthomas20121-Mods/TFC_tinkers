package mrthomas20121.tfc_tinkers.datagen;

import mrthomas20121.tfc_tinkers.TFCTinkers;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.data.material.MaterialDataProvider;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import javax.annotation.Nonnull;

import static net.dries007.tfc.common.TFCTiers.*;
import static net.minecraft.world.item.Tiers.*;

public class TFCMaterials extends MaterialDataProvider {

    private static final MaterialId bismuth_bronze = createMaterial("bismuth_bronze");
    private static final MaterialId black_bronze = createMaterial("black_bronze");
    private static final MaterialId wrought_iron = createMaterial("wrought_iron");
    private static final MaterialId black_steel = createMaterial("black_steel");
    private static final MaterialId blue_steel = createMaterial("black_steel");
    private static final MaterialId red_steel = createMaterial("red_steel");

    private static MaterialId createMaterial(String name) {
        return new MaterialId(TFCTinkers.mod_id, name);
    }

    public TFCMaterials(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addMaterials() {
        this.addMaterial(bismuth_bronze, 3, 6, false);
        this.addMaterial(black_bronze, 3, 6, false);
        this.addMaterial(wrought_iron, 2, 0, false);
        this.addMaterial(black_steel, 3, 5, false);
        this.addMaterial(blue_steel, 3, 5, false);
        this.addMaterial(red_steel, 3, 5, false);
    }

    public static class TFCMaterialTraits extends AbstractMaterialTraitDataProvider {

        public TFCMaterialTraits(DataGenerator gen, AbstractMaterialDataProvider materials) {
            super(gen, materials);
        }

        @Override
        protected void addMaterialTraits() {
            addDefaultTraits(bismuth_bronze, TinkerModifiers.maintained);
            addDefaultTraits(black_bronze, ModifierIds.crumbling);
            addDefaultTraits(wrought_iron, ModifierIds.sturdy);
            addDefaultTraits(black_steel, ModifierIds.ductile);
            addDefaultTraits(blue_steel, ModifierIds.ductile);
            addDefaultTraits(red_steel, ModifierIds.ductile);
        }

        @Nonnull
        @Override
        public String getName() {
            return "TFC Tinkers Material Traits";
        }
    }

    public static class TFCMaterialStatsProvider extends AbstractMaterialStatsDataProvider {

        public TFCMaterialStatsProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
            super(gen, materials);
        }

        @Override
        protected void addMaterialStats() {
            addMaterialStats(bismuth_bronze,
                    new HeadMaterialStats(1200, 6.0f, IRON, 4.0f),
                    HandleMaterialStats.DEFAULT.withDurability(1.2f).withMiningSpeed(1.05f),
                    ExtraMaterialStats.DEFAULT);
            addMaterialStats(black_bronze,
                    new HeadMaterialStats(1460, 5.5f, IRON, 4.25f),
                    HandleMaterialStats.DEFAULT.withDurability(1.25f).withMiningSpeed(1.05f),
                    ExtraMaterialStats.DEFAULT);
            addMaterialStats(wrought_iron,
                    new HeadMaterialStats(2200, 8.0f, IRON, 4.75f),
                    HandleMaterialStats.DEFAULT.withDurability(1.3f),
                    ExtraMaterialStats.DEFAULT);
            addMaterialStats(black_steel,
                    new HeadMaterialStats(4200, 11.0f, STEEL, 7f),
                    HandleMaterialStats.DEFAULT.withDurability(1.3f).withMiningSpeed(1.25f),
                    ExtraMaterialStats.DEFAULT);
            addMaterialStats(blue_steel,
                    new HeadMaterialStats(6500, 12.0f, NETHERITE, 9f),
                    HandleMaterialStats.DEFAULT.withDurability(1.3f).withMiningSpeed(1.35f),
                    ExtraMaterialStats.DEFAULT);
            addMaterialStats(red_steel,
                    new HeadMaterialStats(6500, 11.9f, NETHERITE, 9.1f),
                    HandleMaterialStats.DEFAULT.withDurability(1.35f).withMiningSpeed(1.40f),
                    ExtraMaterialStats.DEFAULT);
        }

        @Nonnull
        @Override
        public String getName() {
            return "TFC Tinkers Materials Stats";
        }
    }

    public static class TFCMaterialRenderInfo extends AbstractMaterialRenderInfoProvider {

        public TFCMaterialRenderInfo(DataGenerator gen, @Nullable AbstractMaterialSpriteProvider materialSprites) {
            super(gen, materialSprites);
        }

        @Override
        protected void addMaterialRenderInfo() {
            buildRenderInfo(bismuth_bronze).color(0x418E4F).fallbacks("metal");
            buildRenderInfo(black_bronze).color(0x3B2636).fallbacks("metal");
            buildRenderInfo(wrought_iron).color(0x989897).fallbacks("metal");
            buildRenderInfo(black_steel).color(0x111111).fallbacks("metal");
            buildRenderInfo(blue_steel).color(0x2D5596).fallbacks("metal");
            buildRenderInfo(red_steel).color(0x00503).fallbacks("metal");
        }

        @Nonnull
        @Override
        public String getName() {
            return "TFC Tinkers Materials Render Info";
        }
    }

    public static class TFCMaterialSpritProvider extends AbstractMaterialSpriteProvider {

        @Nonnull
        @Override
        public String getName() {
            return "TFC Tinkers Material Sprit Provider";
        }

        @Override
        protected void addAllMaterials() {
            buildMaterial(bismuth_bronze)
                    .meleeHarvest()
                    .fallbacks("metal")
                    .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF213927).addARGB(102, 0xFF346544).addARGB(140, 0xFF639074).addARGB(178, 0xFF84AF94).addARGB(216, 0xFFC4DFCE).addARGB(255, 0xFFF5FFFA).build());
            buildMaterial(black_bronze)
                    .meleeHarvest()
                    .fallbacks("metal")
                    .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF030005).addARGB(102, 0xFF25142A).addARGB(140, 0xFF4C3454).addARGB(178, 0xFF614262).addARGB(216, 0xFF825E82).addARGB(255, 0xFFB28DB2).build());
            buildMaterial(wrought_iron)
                    .meleeHarvest()
                    .fallbacks("metal")
                    .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF3A3A3A).addARGB(102, 0xFF565656).addARGB(140, 0xFF898989).addARGB(178, 0xFF9E9E9E).addARGB(216, 0xFFD7D7D7).addARGB(255, 0xFFF2F2F2).build());
            buildMaterial(black_steel)
                    .meleeHarvest()
                    .fallbacks("metal")
                    .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF020202).addARGB(102, 0xFF191919).addARGB(140, 0xFF282828).addARGB(178, 0xFF333333).addARGB(216, 0xFF505050).addARGB(255, 0xFF878787).build());
            buildMaterial(blue_steel)
                    .meleeHarvest()
                    .fallbacks("metal")
                    .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF070E2A).addARGB(102, 0xFF283467).addARGB(140, 0xFF586A9E).addARGB(178, 0xFF7A87B7).addARGB(216, 0xFFA0AEC8).addARGB(255, 0xFFD0E1F0).build());
            buildMaterial(red_steel)
                    .meleeHarvest()
                    .fallbacks("metal")
                    .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF260103).addARGB(102, 0xFF560307).addARGB(140, 0xFF902127).addARGB(178, 0xFFC8575D).addARGB(216, 0xFFEE989C).addARGB(255, 0xFFFCCDCF).build());

        }
    }
}
