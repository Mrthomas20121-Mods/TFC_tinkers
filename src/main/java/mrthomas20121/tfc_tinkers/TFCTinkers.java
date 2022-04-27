package mrthomas20121.tfc_tinkers;

import mrthomas20121.tfc_tinkers.init.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TFCTinkers.mod_id)
public class TFCTinkers {

	public static final String mod_id = "tfc_tinkers";

	public TFCTinkers() {
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		//TFCDecBlocks.BLOCKS.register(bus);
		//TFCDecItems.ITEMS.register(bus);
		Fluids.FLUIDS.register(bus);
	}
}
