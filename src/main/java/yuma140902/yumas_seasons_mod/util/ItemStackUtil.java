package yuma140902.yumas_seasons_mod.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class ItemStackUtil {
	private ItemStackUtil() {}
	
	/**
	 * アイテムの種類、数、メタデータ、NBT、メタデータのワイルドカードを使用して
	 * 2つのItemStackを比較します
	 * @param a
	 * @param b
	 */
	public static boolean areItemStacksEqual(ItemStack a, ItemStack b) {
		boolean equalsWithoutWildcard = ItemStack.areItemStacksEqual(a, b);
		if(!equalsWithoutWildcard) {
			return a != null && b != null && a.getItemDamage() == OreDictionary.WILDCARD_VALUE && b.getItemDamage() == OreDictionary.WILDCARD_VALUE;
		}
		return equalsWithoutWildcard;
	}
}
