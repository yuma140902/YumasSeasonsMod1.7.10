package yuma140902.yumas_seasons_mod.fluid;

import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;

public interface IFluidTank extends IFluidHandler {
	FluidTank getInnerTank();
	void markDirty();
}
