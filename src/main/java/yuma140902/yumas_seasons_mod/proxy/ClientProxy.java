package yuma140902.yumas_seasons_mod.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.yumas_seasons_mod.client.renderer.RenderBlockTank;
import yuma140902.yumas_seasons_mod.client.renderer.TileEntityFluidTankRenderer;
import yuma140902.yumas_seasons_mod.event_handlers.ClientEventHandler;
import yuma140902.yumas_seasons_mod.tileentities.TileEntityFluidTank;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(ClientEventHandler.INSTANCE);
	}
	
	@Override
	public void registerRenderers() {
		super.registerRenderers();
		RenderingRegistry.registerBlockHandler(new RenderBlockTank());
	}
	
	@Override
	public void registerTileEntities() {
		ClientRegistry.registerTileEntity(TileEntityFluidTank.class, TileEntityFluidTank.tileEntityId, new TileEntityFluidTankRenderer());
	}
	
	@Override
	public int getNextRenderId() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
}
