package yuma140902.yumas_seasons_mod.transport;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import yuma140902.yumas_seasons_mod.IModPartition;

public final class YSTransport implements IModPartition {
	private YSTransport() {}
	
	public static final YSTransport instance = new YSTransport();
	
	public final float pipeDurability = 2.0F;
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
	}
	
}
