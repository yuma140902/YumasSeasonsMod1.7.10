package yuma140902.yumas_seasons_mod.util;

import yuma140902.yumas_seasons_mod.ModYumasSeasonsMod;

public final class NameUtil {
	private NameUtil() {}
	
	public static String getDomainedTextureName(String name) {
		return ModYumasSeasonsMod.MOD_TEXTURE_DOMAIN + ":" + name;
	}
	
	public static String getDomainedUnlocalizedName(String name) {
		return ModYumasSeasonsMod.MOD_ID + "." + name;
	}
}
