package yuma140902.yumas_seasons_mod.recipes;

import static yuma140902.yumas_seasons_mod.MyBlocks.*;
import static yuma140902.yumas_seasons_mod.MyItems.*;
import yuma140902.yumas_seasons_mod.MyBlocks;
import yuma140902.yumas_seasons_mod.MyFluids;

public final class Recipes {
	private Recipes() {}
	
	public static final float
		SMELTING_XP_FOR_STONE = 0.1F,
		SMELTING_XP_FOR_LOG = 0.15F,
		SMELTING_XP_FOR_CACTUS = 0.2F,
		SMELTING_XP_FOR_CLAY = 0.3F,
		SMELTING_XP_FOR_FOOD = 0.35F,
		SMELTING_XP_FOR_ORE = 0.7F,
		SMELTING_XP_FOR_RARE_ORE = 1.0F;
	
	public static final SqueezerRecipeRegistry squeezer = new SqueezerRecipeRegistry();
	
	public static void register() {
		appleJuice.registerRecipes();
		appleJam.registerRecipes();
		
		tank.registerRecipes();
		MyBlocks.squeezer.registerRecipes();
		
		MyFluids.appleJuice.registerRecipes();
	}
	
}
