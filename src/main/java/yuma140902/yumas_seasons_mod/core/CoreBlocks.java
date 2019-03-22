package yuma140902.yumas_seasons_mod.core;

import yuma140902.yumas_seasons_mod.core.blocks.BlockLiquidAppleJuice;
import yuma140902.yumas_seasons_mod.core.blocks.BlockSqueezer;
import yuma140902.yumas_seasons_mod.core.blocks.BlockTank;

public final class CoreBlocks {
	private CoreBlocks() {}
	
	public static void register() {
		liquidAppleJuice.register();
		tank.register();
		squeezer.register();
	}
	
	public static final BlockLiquidAppleJuice liquidAppleJuice = new BlockLiquidAppleJuice();
	public static final BlockTank tank = new BlockTank();
	public static final BlockSqueezer squeezer = new BlockSqueezer();
	
}
