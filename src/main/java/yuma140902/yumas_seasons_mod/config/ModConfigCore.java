package yuma140902.yumas_seasons_mod.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ModConfigCore {
	public static final String
		CATEGORY_GENERAL = "General";
	
	public static final String
		CONFIG_PROP_LANGKEY = "config.YumasSeasonsMod.prop.",
		CONFIG_CATEGORY_LANGKEY = "config.YumasSeasonsMod.category.";
	
	public static Configuration cfg;
	
	public static boolean debugMode;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
	}
	
	private static void initConfig() {
		// General
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "Settings of Yuma's Seasons Mod");
		cfg.setCategoryLanguageKey(CATEGORY_GENERAL, CONFIG_CATEGORY_LANGKEY + "general");
		cfg.setCategoryRequiresMcRestart(CATEGORY_GENERAL, true);
	}
	
	public static void syncConfig() {
		// General
		debugMode = cfg.getBoolean("enableDebugMode", CATEGORY_GENERAL, false, "", CONFIG_PROP_LANGKEY + "debug_mode");
		
		cfg.save();
	}
	
}
