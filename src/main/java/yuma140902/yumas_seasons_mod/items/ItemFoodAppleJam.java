package yuma140902.yumas_seasons_mod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import yuma140902.yumas_seasons_mod.IHasRecipe;
import yuma140902.yumas_seasons_mod.IRegisterable;
import yuma140902.yumas_seasons_mod.ModYumasSeasonsMod;
import yuma140902.yumas_seasons_mod.MyItems;
import yuma140902.yumas_seasons_mod.Recipes;
import yuma140902.yumas_seasons_mod.util.NameUtil;

public class ItemFoodAppleJam extends ItemFood implements IRegisterable, IHasRecipe {
	public ItemFoodAppleJam() {
		super(2, false);
		setCreativeTab(ModYumasSeasonsMod.MOD_CREATIVETAB);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(NameUtil.getDomainedUnlocalizedName("apple_jam"));
		this.setTextureName(NameUtil.getDomainedTextureName("apple_jam"));
		GameRegistry.registerItem(this, "appleJam");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addSmelting(MyItems.appleJuice, new ItemStack(this), Recipes.SMELTING_XP_FOR_FOOD);
	}
	
	@Override
	public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player) {
		return LibraryForItems.returnsBottleOnEaten(this, itemstack, world, player);
	}
}
