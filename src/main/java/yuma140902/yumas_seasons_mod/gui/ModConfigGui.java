package yuma140902.yumas_seasons_mod.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import yuma140902.yumas_seasons_mod.ModYumasSeasonsMod;
import yuma140902.yumas_seasons_mod.config.ModConfigCore;

public class ModConfigGui extends GuiConfig {
	public ModConfigGui(GuiScreen parent) {
		super(parent, (new ConfigElement<Object>(ModConfigCore.cfg.getCategory(ModConfigCore.CATEGORY_GENERAL))).getChildElements(), 
				ModYumasSeasonsMod.MOD_ID, false, false, ModYumasSeasonsMod.MOD_NAME);
	}
}
