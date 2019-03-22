package yuma140902.yumas_seasons_mod.lib;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import yuma140902.yumas_seasons_mod.IModPartition;
import yuma140902.yumas_seasons_mod.lib.config.YSConfigCore;

public final class YSLib implements IModPartition {
	private YSLib() {}
	
	public static final YSLib instance = new YSLib();
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		YSConfigCore.loadConfig(event);
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
}
