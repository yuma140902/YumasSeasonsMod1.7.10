package yuma140902.yumas_seasons_mod;

import yuma140902.yumas_seasons_mod.items.ItemBucketAppleJuice;
import yuma140902.yumas_seasons_mod.items.ItemFoodAppleJam;
import yuma140902.yumas_seasons_mod.items.ItemFoodAppleJuice;

public final class MyItems {
	private MyItems() {}
	
	public static void register() {
		appleJuice.register();
		appleJam.register();
		appleJuiceBucket.register();
	}
	
	public static final ItemFoodAppleJuice appleJuice = new ItemFoodAppleJuice();
	public static final ItemFoodAppleJam appleJam = new ItemFoodAppleJam();
	public static final ItemBucketAppleJuice appleJuiceBucket = new ItemBucketAppleJuice();
}
