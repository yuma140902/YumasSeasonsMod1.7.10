package yuma140902.yumas_seasons_mod.recipes;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class SqueezerRecipeRegistry {
	private ArrayList<SqueezerRecipe> recipes = new ArrayList<SqueezerRecipe>();
	
	public void registerRecipe(FluidStack output, ItemStack input) {
		SqueezerRecipe recipe = new SqueezerRecipe(output, input);
		recipes.add(recipe);
	}
	
	public void registerRecipe(FluidStack output, String inputOreDict) {
		SqueezerRecipe recipe = new SqueezerRecipe(output, inputOreDict);
		recipes.add(recipe);
	}
	
	public FluidStack getProduct(ItemStack input) {
		for (SqueezerRecipe recipe : recipes) {
			if(recipe.matches(input)) {
				return recipe.getProduct();
			}
		}
		
		return null;
	}
}
