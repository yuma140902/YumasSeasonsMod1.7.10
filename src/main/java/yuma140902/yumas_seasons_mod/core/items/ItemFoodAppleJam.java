package yuma140902.yumas_seasons_mod.core.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import yuma140902.yumas_seasons_mod.YumasSeasonsMod;
import yuma140902.yumas_seasons_mod.core.CoreItems;
import yuma140902.yumas_seasons_mod.lib.IHasRecipe;
import yuma140902.yumas_seasons_mod.lib.IRegisterable;
import yuma140902.yumas_seasons_mod.lib.recipes.YSRecipes;
import yuma140902.yumas_seasons_mod.lib.util.NameUtil;

public class ItemFoodAppleJam extends ItemFood implements IRegisterable, IHasRecipe {
	public ItemFoodAppleJam() {
		super(2, false);
		setCreativeTab(YumasSeasonsMod.MOD_CREATIVETAB);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(NameUtil.getDomainedUnlocalizedName("apple_jam"));
		this.setTextureName(NameUtil.getDomainedTextureName("apple_jam"));
		GameRegistry.registerItem(this, "appleJam");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addSmelting(CoreItems.appleJuice, new ItemStack(this), YSRecipes.SMELTING_XP_FOR_FOOD);
	}
	
	@Override
	public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player) {
		return LibraryForItems.returnsBottleOnEaten(this, itemstack, world, player);
	}
}
