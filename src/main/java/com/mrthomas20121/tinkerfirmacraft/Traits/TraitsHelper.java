package com.mrthomas20121.tinkerfirmacraft.Traits;

import scala.tools.nsc.doc.model.Trait;
import slimeknights.tconstruct.tools.TraitEvents;
import slimeknights.tconstruct.tools.traits.*;

public class TraitsHelper {
    public static TraitMagnetic Magnetic3 = new TraitMagnetic(3);
    public static TraitMagnetic Magnetic4 = new TraitMagnetic(4);
    public static TraitMagnetic Magnetic5 = new TraitMagnetic(5);
    public static TraitMagnetic Magnetic6 = new TraitMagnetic(6);
    public static TraitCrude Crude3 = new TraitCrude(3);
    public static TraitCrude Crude4 = new TraitCrude(4);
    public static TraitCrude Crude5 = new TraitCrude(5);
    public static TraitCrude Crude6 = new TraitCrude(6);

    // custom tinker traits
    public static TraitBrittle brittle = new TraitBrittle();
    public static TraitFerromagnetism ferromagnetism = new TraitFerromagnetism();
}
