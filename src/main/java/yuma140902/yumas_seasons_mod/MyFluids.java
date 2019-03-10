package yuma140902.yumas_seasons_mod;

import net.minecraftforge.fluids.FluidRegistry;
import yuma140902.yumas_seasons_mod.fluid.FluidAppleJuice;

public final class MyFluids {
	private MyFluids() {}
	
	public static void register() {
		FluidRegistry.registerFluid(appleJuice);
	}
	
	public static final FluidAppleJuice appleJuice = new FluidAppleJuice();
}
