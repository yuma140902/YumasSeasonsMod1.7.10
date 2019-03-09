package yuma140902.yumas_seasons_mod.event_handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.yumas_seasons_mod.config.ModConfigCore;

public class ClientEventHandler {
private ClientEventHandler() {}
	
	public static final ClientEventHandler INSTANCE = new ClientEventHandler();
	
	@SubscribeEvent
	public void onTooltipShown(ItemTooltipEvent event) {
		if(ModConfigCore.debugMode) {
			showOreDictionary(event);
		}
	}
	
	private static void showOreDictionary(ItemTooltipEvent event) {
		ItemStack itemstack = event.itemStack;
		int[] oreIDs = OreDictionary.getOreIDs(itemstack);
		
		for(int i = 0; i < oreIDs.length; ++i) {
			event.toolTip.add(OreDictionary.getOreName(oreIDs[i]));
		}
	}
}
