package yuma140902.yumas_seasons_mod.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.yumas_seasons_mod.util.ItemStackUtil;

public class SqueezerRecipe {
	
	private FluidStack output;
	private ItemStack input;
	private int inputOreDict;
	
	public SqueezerRecipe(FluidStack output, ItemStack input) {
		this.output = output;
		this.input = input;
		this.inputOreDict = -1;
	}
	
	public SqueezerRecipe(FluidStack output, String inputOreDict) {
		this.output = output;
		this.input = null;
		this.inputOreDict = OreDictionary.getOreID(inputOreDict);
	}
	
	public boolean matches(ItemStack input) {
		if(this.input != null) {
			return ItemStackUtil.areItemStacksEqual(this.input, input);
		}
		else if(this.inputOreDict != -1) {
			int[] oreIds = OreDictionary.getOreIDs(input);
			for (int id : oreIds) {
				if(inputOreDict == id) return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	public FluidStack getProduct() {
		return output;
	}
}
