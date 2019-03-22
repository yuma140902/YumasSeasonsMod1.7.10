package yuma140902.yumas_seasons_mod.core;

import net.minecraftforge.fluids.FluidRegistry;
import yuma140902.yumas_seasons_mod.core.fluid.FluidAppleJuice;

public final class CoreFluids {
	private CoreFluids() {}
	
	public static void register() {
		FluidRegistry.registerFluid(appleJuice);
	}
	
	public static final FluidAppleJuice appleJuice = new FluidAppleJuice();
}
