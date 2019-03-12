package yuma140902.yumas_seasons_mod.event_handlers;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import yuma140902.yumas_seasons_mod.ModYumasSeasonsMod;
import yuma140902.yumas_seasons_mod.config.ModConfigCore;
import yuma140902.yumas_seasons_mod.fluid.FillBucketHandler;

public class CommonEventHandler {
	private CommonEventHandler() {}
	
	public static final CommonEventHandler INSTANCE = new CommonEventHandler();
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (ModYumasSeasonsMod.MOD_ID.equals(event.modID))
			ModConfigCore.syncConfig();
	}
	
	@SubscribeEvent
	public void onFillBucket(FillBucketEvent event) {
		FillBucketHandler.INSTANCE.onFillBucket(event);
	}
	
}
