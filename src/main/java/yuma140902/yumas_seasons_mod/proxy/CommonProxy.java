package yuma140902.yumas_seasons_mod.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.yumas_seasons_mod.event_handlers.CommonEventHandler;
import yuma140902.yumas_seasons_mod.tileentities.TileEntityFluidTank;

public class CommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(CommonEventHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(CommonEventHandler.INSTANCE);
	}
	
	public void registerEntities() {
	}
	
	public void registerRenderers() { }
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityFluidTank.class, TileEntityFluidTank.tileEntityId);
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
