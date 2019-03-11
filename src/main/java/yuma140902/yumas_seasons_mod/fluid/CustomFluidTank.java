package yuma140902.yumas_seasons_mod.fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class CustomFluidTank extends FluidTank {

	public CustomFluidTank(int capacity) {
		super(capacity);
	}
	
	public CustomFluidTank(FluidStack stack, int capacity) {
		super(stack, capacity);
	}
	
	public CustomFluidTank(Fluid fluid, int amount, int capacity) {
		super(fluid, amount, capacity);
	}
	
	public boolean isEmpty() {
		return getFluid() == null || getFluid().getFluid() == null || getFluid().amount <= 0;
	}
	
	public boolean isFull() {
		return getFluid() != null && getFluid().amount == getCapacity();
	}
	
	public Fluid getFluidType() {
		return getFluid() != null ? getFluid().getFluid() : null;
	}
	
	public String getFluidName() {
		return getFluid() != null && fluid.getFluid() != null ? getFluidType().getUnlocalizedName(this.fluid) : "Empty";
	}
	
	@SideOnly(Side.CLIENT)
	public void setAmount(int amount) {
		if(this.fluid != null && this.fluid.getFluid() != null) {
			this.fluid.amount = amount;
		}
	}
}
