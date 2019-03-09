package yuma140902.yumas_seasons_mod.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.yumas_seasons_mod.event_handlers.CommonEventHandler;

public class CommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(CommonEventHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(CommonEventHandler.INSTANCE);
	}
	
	public void registerEntities() {
	}
	
	public void registerRenderers() { }
}
