package yuma140902.yumas_seasons_mod.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import yuma140902.yumas_seasons_mod.IHasRecipe;
import yuma140902.yumas_seasons_mod.recipes.Recipes;
import yuma140902.yumas_seasons_mod.util.NameUtil;

public class FluidAppleJuice extends Fluid implements IHasRecipe {
	public FluidAppleJuice() {
		super(NameUtil.getDomainedUnlocalizedName("apple_juice"));
	}
	
	@Override
	public void registerRecipes() {
		Recipes.squeezer.registerRecipe(new FluidStack(this, 100), "foodApple");
	}
}
