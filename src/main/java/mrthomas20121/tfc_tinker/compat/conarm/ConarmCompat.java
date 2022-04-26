package mrthomas20121.tfc_tinker.compat.conarm;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import net.dries007.tfc.api.types.Metal;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;

public class ConarmCompat {

    public static void addConarmSupport(Metal metal, Material material) {
        String identifier = material.getIdentifier();
        int harvest_level = metal.getToolMetal().getHarvestLevel();
        float toughness = harvest_level >= 3 ? 1:0;

        int durability = metal.getToolMetal().getMaxUses();
        float attack_dmg = metal.getToolMetal().getAttackDamage();

        TinkerRegistry.addMaterialStats(material,
                new CoreMaterialStats(durability/30f, attack_dmg*2.1f),
                new PlatesMaterialStats(0.9f, durability/18f, toughness),
                new TrimMaterialStats(durability/16f));
        if(identifier.contains("steel")) {
            material.addTrait(ArmorTraits.steady, ArmorMaterialType.CORE);
            material.addTrait(ArmorTraits.indomitable, ArmorMaterialType.TRIM);
            material.addTrait(ArmorTraits.indomitable, ArmorMaterialType.PLATES);
        }
        else if(identifier.contains("bronze")) {
            material.addTrait(ArmorTraits.dense, ArmorMaterialType.CORE);
            material.addTrait(ArmorTraits.dense, ArmorMaterialType.TRIM);
            material.addTrait(ArmorTraits.dense, ArmorMaterialType.PLATES);
        }
        else if(identifier.contains("iron")) {
            material.addTrait(ArmorTraits.magnetic2, ArmorMaterialType.CORE);
            material.addTrait(ArmorTraits.magnetic, ArmorMaterialType.TRIM);
            material.addTrait(ArmorTraits.magnetic, ArmorMaterialType.PLATES);
        }
        else {
            material.addTrait(ArmorTraits.shielding, ArmorMaterialType.CORE);
            material.addTrait(ArmorTraits.dense, ArmorMaterialType.TRIM);
            material.addTrait(ArmorTraits.dense, ArmorMaterialType.PLATES);
        }
    }
}
