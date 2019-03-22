package yuma140902.yumas_seasons_mod.core.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.yumas_seasons_mod.core.event_handlers.CoreCommonEventHandler;
import yuma140902.yumas_seasons_mod.core.tileentities.TileEntityFluidTank;
import yuma140902.yumas_seasons_mod.core.tileentities.TileEntitySqueezer;

public class CoreCommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(CoreCommonEventHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(CoreCommonEventHandler.INSTANCE);
	}
	
	public void registerEntities() {
	}
	
	public void registerRenderers() { }
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityFluidTank.class, TileEntityFluidTank.tileEntityId);
		GameRegistry.registerTileEntity(TileEntitySqueezer.class, TileEntitySqueezer.tileEntityId);
	}
	
	public int getNextRenderId() {
		return -1;
	}
	
	public void registerVanillaOreDictinaries() {
		OreDictionary.registerOre("foodApple", Items.apple);
		OreDictionary.registerOre("ingredientSuger", Items.sugar);
		OreDictionary.registerOre("bottleGlass", Items.glass_bottle);
	}
	
}
