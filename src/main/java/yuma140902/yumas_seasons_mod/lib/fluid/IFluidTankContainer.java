package yuma140902.yumas_seasons_mod.lib.fluid;

import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;

public interface IFluidTankContainer extends IFluidHandler {
	/**
	 * 利用可能なタンクのうち最初の一つを返します
	 * @return
	 */
	FluidTank getMainTank();
}
