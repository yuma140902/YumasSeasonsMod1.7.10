package yuma140902.yumas_seasons_mod.lib.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.yumas_seasons_mod.YumasSeasonsMod;
import yuma140902.yumas_seasons_mod.lib.config.YSConfigCore;

public class YSConfigGui extends GuiConfig {
	public YSConfigGui(GuiScreen parent) {
		super(parent, (new ConfigElement<Object>(YSConfigCore.cfg.getCategory(YSConfigCore.CATEGORY_GENERAL))).getChildElements(), 
				YumasSeasonsMod.MOD_ID, false, false, YumasSeasonsMod.MOD_NAME);
	}
}
