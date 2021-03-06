package yuma140902.yumas_seasons_mod;

import yuma140902.yumas_seasons_mod.blocks.BlockLiquidAppleJuice;
import yuma140902.yumas_seasons_mod.blocks.BlockSqueezer;
import yuma140902.yumas_seasons_mod.blocks.BlockTank;

public final class MyBlocks {
	private MyBlocks() {}
	
	public static void register() {
		liquidAppleJuice.register();
		tank.register();
		squeezer.register();
	}
	
	public static final BlockLiquidAppleJuice liquidAppleJuice = new BlockLiquidAppleJuice();
	public static final BlockTank tank = new BlockTank();
	public static final BlockSqueezer squeezer = new BlockSqueezer();
	
}
