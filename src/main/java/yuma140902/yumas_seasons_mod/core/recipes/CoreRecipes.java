package yuma140902.yumas_seasons_mod.core.recipes;

import yuma140902.yumas_seasons_mod.core.CoreBlocks;
import yuma140902.yumas_seasons_mod.core.CoreFluids;
import yuma140902.yumas_seasons_mod.core.CoreItems;

public final class CoreRecipes {
	private CoreRecipes() {}
	
	public static final SqueezerRecipeRegistry squeezer = new SqueezerRecipeRegistry();
	
	public static final void register() {
		CoreItems.appleJuice.registerRecipes();
		CoreItems.appleJam.registerRecipes();
		
		CoreBlocks.tank.registerRecipes();
		CoreBlocks.squeezer.registerRecipes();
		
		CoreFluids.appleJuice.registerRecipes();
	}
}
