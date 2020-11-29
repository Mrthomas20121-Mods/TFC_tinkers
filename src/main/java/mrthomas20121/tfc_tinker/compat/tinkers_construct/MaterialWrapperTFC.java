package mrthomas20121.tfc_tinker.compat.tinkers_construct;

import mrthomas20121.biolib.objects.material.MaterialWrapper;
import mrthomas20121.tfc_tinker.traits.TraitsHelper;
import net.dries007.tfc.api.types.Metal;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.tools.TinkerTraits;

public class MaterialWrapperTFC extends MaterialWrapper {
    public Metal metal;

    public MaterialWrapperTFC(Metal metal)
    {
        super("tfc_",metal.getRegistryName().getPath(), metal.getColor());
        this.metal = metal;
    }

    public String getName()
    {
        return metal.getRegistryName().getPath();
    }

    public void setTraits()
    {
        switch (this.getName())
        {
            case "bismuth_bronze":
            case "black_bronze":
                this.addTrait(TinkerTraits.dense);
            break;
            case "red_steel":
            case "blue_steel":
            case "black_steel":
            case "tungsten_steel":
                this.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
                this.addTrait(TinkerTraits.stiff, MaterialTypes.HANDLE);
                this.addTrait(TinkerTraits.stiff, MaterialTypes.EXTRA);
            break;
            case "wrought_iron":
                this.addTrait(TinkerTraits.magnetic2, MaterialTypes.HEAD);
                this.addTrait(TinkerTraits.magnetic, MaterialTypes.HANDLE);
                this.addTrait(TinkerTraits.magnetic, MaterialTypes.EXTRA);
            break;
            case "boron":
            case "invar":
                this.addTrait(TinkerTraits.crude2);
            break;
            case "osmium":
                this.addTrait(TinkerTraits.established, MaterialTypes.HEAD);
                this.addTrait(TinkerTraits.dense, MaterialTypes.HANDLE);
                this.addTrait(TinkerTraits.dense, MaterialTypes.EXTRA);
            break;
            case "nickel_silver":
                this.addTrait(TraitsHelper.brittle);
            break;
            case "mithril":
            case "aluminum":
            case "titanium":
                this.addTrait(TinkerTraits.lightweight);
            break;
            case "tungsten":
            case "thaumium":
                this.addTrait(TinkerTraits.jagged);
            break;
            case "beryllium_copper":
            case "zircaloy":
                this.addTrait(TinkerTraits.crumbling);
            break;
            default:
                this.addTrait(TinkerTraits.dense);
            break;
        }
    }
}