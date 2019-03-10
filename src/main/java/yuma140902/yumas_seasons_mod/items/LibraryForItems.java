package yuma140902.yumas_seasons_mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * アイテム向けの共通処理。抽象クラスの継承では多重継承ができないのでこのクラスに共通処理を書く
 * @author yuma140902
 *
 */
public final class LibraryForItems {
	private LibraryForItems() {}
	
	/**
	 * Item#onEaten()から委譲
	 * @param itemstack
	 * @param world
	 * @param player
	 * @return
	 */
	public static ItemStack returnsBottleOnEaten(ItemFood food, ItemStack itemstack, World world, EntityPlayer player) {
		--itemstack.stackSize;
    player.getFoodStats().func_151686_a(food, itemstack);
    world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		
		if (!player.capabilities.isCreativeMode) {
			if (itemstack.stackSize <= 0) {
				return new ItemStack(Items.glass_bottle);
			}
			
			player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
		}
		
		return itemstack;
	}
}
