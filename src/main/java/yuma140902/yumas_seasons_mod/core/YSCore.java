package yuma140902.yumas_seasons_mod.core;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import yuma140902.yumas_seasons_mod.IModPartition;
import yuma140902.yumas_seasons_mod.core.gui.CoreGuiHandler;
import yuma140902.yumas_seasons_mod.core.recipes.CoreRecipes;

public final class YSCore implements IModPartition{
	private YSCore() {}
	
	public static final String
		CORE_PROXY_CLIENT = "yuma140902.yumas_seasons_mod.proxy.core.CoreClientProxy",
		CORE_PROXY_COMMON = "yuma140902.yumas_seasons_mod.proxy.core.CoreCommonProxy";
	
	public static final YSCore instance = new YSCore();
	
	public int renderIdTank;

	@SidedProxy(clientSide = CORE_PROXY_CLIENT, serverSide = CORE_PROXY_COMMON)
	public static yuma140902.yumas_seasons_mod.core.proxy.CoreCommonProxy proxy;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		CoreFluids.register();
		CoreBlocks.register();
		CoreItems.register();
		
		proxy.registerVanillaOreDictinaries();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		CoreRecipes.register();
		
		renderIdTank = proxy.getNextRenderId();
		
		proxy.registerEntities();
		proxy.registerRenderers();
		proxy.registerTileEntities();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, CoreGuiHandler.INSTANCE);
		
		CoreWorldGenerators.register();
		
		proxy.registerEventHandlers();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
