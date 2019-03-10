package yuma140902.yumas_seasons_mod;

import yuma140902.yumas_seasons_mod.blocks.BlockLiquidAppleJuice;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		appleJuice.register();
	}
	
	public static final BlockLiquidAppleJuice appleJuice = new BlockLiquidAppleJuice();
	
}
