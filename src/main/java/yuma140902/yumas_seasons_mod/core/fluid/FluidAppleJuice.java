package yuma140902.yumas_seasons_mod.core.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import yuma140902.yumas_seasons_mod.core.recipes.CoreRecipes;
import yuma140902.yumas_seasons_mod.lib.IHasRecipe;
import yuma140902.yumas_seasons_mod.lib.util.NameUtil;

public class FluidAppleJuice extends Fluid implements IHasRecipe {
	public FluidAppleJuice() {
		super(NameUtil.getDomainedUnlocalizedName("apple_juice"));
	}
	
	@Override
	public void registerRecipes() {
		CoreRecipes.squeezer.registerRecipe(new FluidStack(this, 100), "foodApple");
	}
}
