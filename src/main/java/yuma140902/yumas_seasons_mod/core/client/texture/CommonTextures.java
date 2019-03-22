package yuma140902.yumas_seasons_mod.core.client.texture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import yuma140902.yumas_seasons_mod.lib.util.NameUtil;

@SideOnly(Side.CLIENT)
public final class CommonTextures {
	private CommonTextures() {}
	
	public static final String commonMachineTop = NameUtil.getDomainedTextureName("common_machine_top");
	public static final String commonMachineSide = NameUtil.getDomainedTextureName("common_machine_side");
	public static final String commonMachineBottom = NameUtil.getDomainedTextureName("common_machine_bottom");
}
