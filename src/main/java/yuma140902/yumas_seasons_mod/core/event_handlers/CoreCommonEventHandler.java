package yuma140902.yumas_seasons_mod.core.event_handlers;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import yuma140902.yumas_seasons_mod.YumasSeasonsMod;
import yuma140902.yumas_seasons_mod.lib.config.YSConfigCore;
import yuma140902.yumas_seasons_mod.lib.fluid.FillBucketHandler;

public class CoreCommonEventHandler {
	private CoreCommonEventHandler() {}
	
	public static final CoreCommonEventHandler INSTANCE = new CoreCommonEventHandler();
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (YumasSeasonsMod.MOD_ID.equals(event.modID))
			YSConfigCore.syncConfig();
	}
	
	@SubscribeEvent
	public void onFillBucket(FillBucketEvent event) {
		FillBucketHandler.INSTANCE.onFillBucket(event);
	}
	
}
