package yuma140902.yumas_seasons_mod.core.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import yuma140902.yumas_seasons_mod.core.gui.invnetory.SqueezerContainer;
import yuma140902.yumas_seasons_mod.core.gui.invnetory.SqueezerGuiContainer;
import yuma140902.yumas_seasons_mod.core.tileentities.TileEntitySqueezer;

public class CoreGuiHandler implements IGuiHandler {
	public static CoreGuiHandler INSTANCE = new CoreGuiHandler();
	
	private CoreGuiHandler() {}
	
	public static final int
		SQUEEZER = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == SQUEEZER) {
			return new SqueezerContainer(player, (TileEntitySqueezer)world.getTileEntity(x, y, z));
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == SQUEEZER) {
			return new SqueezerGuiContainer(player, (TileEntitySqueezer)world.getTileEntity(x, y, z));
		}
		return null;
	}
}
