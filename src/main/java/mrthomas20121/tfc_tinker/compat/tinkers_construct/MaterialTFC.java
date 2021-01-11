package mrthomas20121.tfc_tinker.compat.tinkers_construct;

import net.dries007.tfc.api.types.Metal;
import slimeknights.tconstruct.library.materials.Material;

public class MaterialTFC {

    private Metal metal;
    private Material material;

    public MaterialTFC(Metal metal, Material material)
    {
        this.metal = metal;
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public Metal getMetal() {
        return metal;
    }
}
