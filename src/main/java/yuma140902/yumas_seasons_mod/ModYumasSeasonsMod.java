package yuma140902.yumas_seasons_mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.yumas_seasons_mod.util.Consts;

@Mod(modid = ModYumasSeasonsMod.MOD_ID, version = ModYumasSeasonsMod.MOD_VERSION, useMetadata = true, guiFactory = Consts.MOD_CONFIG_GUI_FACTORY)
public class ModYumasSeasonsMod {
	@Mod.Metadata
	public static ModMetadata modMetadata;
	
	@Mod.Instance
	public static ModYumasSeasonsMod INSTANCE;
	
	@SidedProxy(clientSide = Consts.PROXY_CLIENT, serverSide = Consts.PROXY_SERVER)
	public static yuma140902.yumas_seasons_mod.proxy.CommonProxy proxy;
	
	public static final String MOD_ID = "YumasSeasonsMod";
	public static final String MOD_NAME = "Yuma's Seasons Mod";
	public static final String MINECRAFT_VERSION = "1.7.10";
	public static final String MOD_VERSION = "0.0.0";
	public static final String MOD_TEXTURE_DOMAIN = "yumas_seasons_mod";
	public static final CreativeTabs MOD_CREATIVETAB = new MyCreativeTab();
	
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
		yuma140902.yumas_seasons_mod.config.ModConfigCore.loadConfig(event);
		
		MyBlocks.register();
		MyItems.register();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Recipes.register();
		
		proxy.registerEntities();
		proxy.registerRenderers();
		
		WorldGenerators.register();
		
		proxy.registerEventHandlers();
	}
}
