package yuma140902.yumas_seasons_mod.lib.util;

import yuma140902.yumas_seasons_mod.YumasSeasonsMod;

public final class NameUtil {
	private NameUtil() {}
	
	public static String getDomainedTextureName(String name) {
		return YumasSeasonsMod.MOD_TEXTURE_DOMAIN + ":" + name;
	}
	
	public static String getDomainedUnlocalizedName(String name) {
		return YumasSeasonsMod.MOD_UNLOCALIZED_DOMAIN + "." + name;
	}
}
