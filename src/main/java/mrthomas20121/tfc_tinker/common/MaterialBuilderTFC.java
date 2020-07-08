package mrthomas20121.tfc_tinker.common;

import mrthomas20121.biolib.common.MaterialBuilder;
import mrthomas20121.tfc_tinker.Traits.TraitsHelper;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.tools.TinkerTraits;

public class MaterialBuilderTFC extends MaterialBuilder {
    private Metal metal = null;
    public MaterialBuilderTFC(Metal metal)
    {
        super(new Material(metal.getRegistryName().getPath(), metal.getColor()));
        this.metal = metal;
    }
    public Metal getMetal()
    {
        return this.metal;
    }

    public void setTraits()
    {
        switch (this.metal.getRegistryName().getPath())
        {
            case "bismuth_bronze":
            case "black_bronze":
                this.setTrait(TinkerTraits.dense);
            break;
            case "red_steel":
            case "blue_steel":
            case "black_steel":
            case "tungsten_steel":
                this.setTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
                this.setTrait(TinkerTraits.stiff, MaterialTypes.HANDLE);
                this.setTrait(TinkerTraits.stiff, MaterialTypes.EXTRA);
            break;
            case "wrought_iron":
                this.setTrait(TinkerTraits.magnetic2, MaterialTypes.HEAD);
                this.setTrait(TinkerTraits.magnetic, MaterialTypes.HANDLE);
                this.setTrait(TinkerTraits.magnetic, MaterialTypes.EXTRA);
            break;
            case "boron":
            case "invar":
                this.setTrait(TinkerTraits.crude2);
            break;
            case "osmium":
                this.setTrait(TinkerTraits.established, MaterialTypes.HEAD);
                this.setTrait(TinkerTraits.dense, MaterialTypes.HANDLE);
                this.setTrait(TinkerTraits.dense, MaterialTypes.EXTRA);
            break;
            case "nickel_silver":
                this.setTrait(TraitsHelper.brittle);
            break;
            case "mithril":
            case "aluminum":
            case "titanium":
                this.setTrait(TinkerTraits.lightweight);
            break;
            case "tungsten":
                this.setTrait(TinkerTraits.jagged);
            break;
            case "beryllium_copper":
            case "zircaloy":
                this.setTrait(TinkerTraits.crumbling);
            break;
            default:
                this.setTrait(TinkerTraits.momentum);
            break;
        }
    }
    private ResourceLocation getLoc(String path)
    {
        return new ResourceLocation(TerraFirmaCraft.MOD_ID, path);
    }
    private boolean equals(ResourceLocation loc)
    {
        return this.metal.getRegistryName().equals(loc);
    }
}