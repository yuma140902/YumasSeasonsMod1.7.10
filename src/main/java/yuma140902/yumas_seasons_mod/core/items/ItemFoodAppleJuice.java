package yuma140902.yumas_seasons_mod.core.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.yumas_seasons_mod.YumasSeasonsMod;
import yuma140902.yumas_seasons_mod.core.CoreFluids;
import yuma140902.yumas_seasons_mod.lib.IHasRecipe;
import yuma140902.yumas_seasons_mod.lib.IRegisterable;
import yuma140902.yumas_seasons_mod.lib.util.NameUtil;

public class ItemFoodAppleJuice extends ItemFood implements IRegisterable, IHasRecipe {
	public ItemFoodAppleJuice() {
		super(1, false);
		setCreativeTab(YumasSeasonsMod.MOD_CREATIVETAB);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(NameUtil.getDomainedUnlocalizedName("apple_juice"));
		this.setTextureName(NameUtil.getDomainedTextureName("apple_juice"));
		GameRegistry.registerItem(this, "appleJuice");
		FluidContainerRegistry.registerFluidContainer(new FluidStack(CoreFluids.appleJuice, 25), new ItemStack(this), new ItemStack(Items.glass_bottle));
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry
				.addRecipe(new ShapelessOreRecipe(new ItemStack(this, 4), "foodApple", "ingredientSuger", Items.glass_bottle));
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.drink;
	}
	
	@Override
	public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player) {
		return LibraryForItems.returnsBottleOnEaten(this, itemstack, world, player);
	}
}
