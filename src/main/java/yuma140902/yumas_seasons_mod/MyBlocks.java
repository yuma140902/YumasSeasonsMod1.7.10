package yuma140902.yumas_seasons_mod;

import yuma140902.yumas_seasons_mod.blocks.BlockLiquidAppleJuice;
import yuma140902.yumas_seasons_mod.blocks.BlockTank;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		appleJuice.register();
		tank.register();
	}
	
	public static final BlockLiquidAppleJuice appleJuice = new BlockLiquidAppleJuice();
	public static final BlockTank tank = new BlockTank();
	
}
