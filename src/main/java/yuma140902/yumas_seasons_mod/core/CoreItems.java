package yuma140902.yumas_seasons_mod.core;

import yuma140902.yumas_seasons_mod.core.items.ItemBucketAppleJuice;
import yuma140902.yumas_seasons_mod.core.items.ItemFoodAppleJam;
import yuma140902.yumas_seasons_mod.core.items.ItemFoodAppleJuice;

public final class CoreItems {
	private CoreItems() {}
	
	public static void register() {
		appleJuice.register();
		appleJam.register();
		appleJuiceBucket.register();
	}
	
	public static final ItemFoodAppleJuice appleJuice = new ItemFoodAppleJuice();
	public static final ItemFoodAppleJam appleJam = new ItemFoodAppleJam();
	public static final ItemBucketAppleJuice appleJuiceBucket = new ItemBucketAppleJuice();
}
