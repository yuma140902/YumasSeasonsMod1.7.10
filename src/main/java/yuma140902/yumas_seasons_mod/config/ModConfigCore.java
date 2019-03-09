package yuma140902.yumas_seasons_mod.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ModConfigCore {
	public static final String
		CATEGORY_GENERAL = "General";
	
	public static final String
		CONFIG_PROP_LANGKEY = "config.uptodate.prop.",
		CONFIG_CATEGORY_LANGKEY = "config.uptodate.category.";
	
	public static Configuration cfg;
	
	public static boolean debug_mode;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		cfg = new Configuration(event.getSuggestedConfigurationFile(), true);
		initConfig();
		syncConfig();
	}
	
	private static void initConfig() {
		// General
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "Settings of UpToDateMod");
		cfg.setCategoryLanguageKey(CATEGORY_GENERAL, CONFIG_CATEGORY_LANGKEY + "general");
		cfg.setCategoryRequiresMcRestart(CATEGORY_GENERAL, true);
	}
	
	public static void syncConfig() {
		// General
		debug_mode = cfg.getBoolean("enableDebugMode", CATEGORY_GENERAL, false, "", CONFIG_PROP_LANGKEY + "debug_mode");
		
		cfg.save();
	}
	
}
