package yuma140902.yumas_seasons_mod.client.texture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import yuma140902.yumas_seasons_mod.util.NameUtil;

@SideOnly(Side.CLIENT)
public final class CommonTextures {
	private CommonTextures() {}
	
	public static final String commonMachineSide = NameUtil.getDomainedTextureName("common_machine_side");
	public static final String commonMachineTop = NameUtil.getDomainedTextureName("common_machine_top");
}
