package yuma140902.yumas_seasons_mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.yumas_seasons_mod.core.CoreCreativeTab;
import yuma140902.yumas_seasons_mod.core.YSCore;
import yuma140902.yumas_seasons_mod.lib.YSLib;
import yuma140902.yumas_seasons_mod.lib.util.YSConstants;
import yuma140902.yumas_seasons_mod.transport.YSTransport;

@Mod(modid = YumasSeasonsMod.MOD_ID, name = YumasSeasonsMod.MOD_NAME, version = YumasSeasonsMod.MOD_VERSION, useMetadata = true, guiFactory = YSConstants.YS_CONFIG_GUI_FACTORY)
public class YumasSeasonsMod {
	@Mod.Metadata
	public static ModMetadata modMetadata;
	
	@Mod.Instance
	public static YumasSeasonsMod INSTANCE;
	
	public static final YSCore ysCore = YSCore.instance;
	public static final YSLib ysLib = YSLib.instance;
	public static final YSTransport ysTransport = YSTransport.instance;
	
	public static final String MOD_ID = "YumasSeasonsMod";
	public static final String MOD_NAME = "Yuma's Seasons Mod";
	public static final String MINECRAFT_VERSION = "1.7.10";
	public static final String MOD_VERSION = "0.0.0";
	public static final String MOD_TEXTURE_DOMAIN = "yumas_seasons_mod";
	public static final String MOD_UNLOCALIZED_DOMAIN = "yumas_seasons_mod";
	public static final CreativeTabs MOD_CREATIVETAB = new CoreCreativeTab();
	
	private void loadModMetadata(ModMetadata modMetadata) {
		modMetadata.modId = MOD_ID;
		modMetadata.name = MOD_NAME;
		modMetadata.version = MOD_VERSION;
		modMetadata.authorList.add("yuma140902");
		modMetadata.description = "四季!";
		modMetadata.autogenerated = false;
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		loadModMetadata(modMetadata);
		
		ysLib.preInit(event);
		ysCore.preInit(event);
		ysTransport.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ysLib.init(event);
		ysCore.init(event);
		ysTransport.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ysLib.postInit(event);
		ysCore.postInit(event);
		ysTransport.postInit(event);
	}
}
