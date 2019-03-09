package yuma140902.yumas_seasons_mod.event_handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
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
			showFoodHealAmount(event);
		}
	}
	
	private static void showOreDictionary(ItemTooltipEvent event) {
		ItemStack itemstack = event.itemStack;
		int[] oreIDs = OreDictionary.getOreIDs(itemstack);
		
		for(int i = 0; i < oreIDs.length; ++i) {
			event.toolTip.add(OreDictionary.getOreName(oreIDs[i]));
		}
	}
	
	private static void showFoodHealAmount(ItemTooltipEvent event) {
		ItemStack itemstack = event.itemStack;
		Item item = itemstack.getItem();
		
		if(item instanceof ItemFood) {
			ItemFood food = (ItemFood)item;
			int healAmount = food.func_150905_g(itemstack);
			float saturationModifier = food.func_150906_h(itemstack);
			
			event.toolTip.add(healAmount + "(+* " + saturationModifier + ")");
		}
	}
}
