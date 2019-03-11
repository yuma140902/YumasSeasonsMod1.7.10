package yuma140902.yumas_seasons_mod.event_handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.yumas_seasons_mod.MyFluids;
import yuma140902.yumas_seasons_mod.config.ModConfigCore;
import yuma140902.yumas_seasons_mod.util.Consts;
import yuma140902.yumas_seasons_mod.util.NameUtil;

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
	
	@SubscribeEvent
	public void preTextureStitchingEvent(TextureStitchEvent.Pre event) {
		registerFluidTextures(event);
	}
	
	private void registerFluidTextures(TextureStitchEvent.Pre event) {
		// 液体のテクスチャはterain.png (texture type 0) に登録する
		if(event.map.getTextureType() != 0) {
			return;
		}
		
		registerFluidTexture(MyFluids.appleJuice, event);
	}
	
	private void registerFluidTexture(Fluid fluid, TextureStitchEvent.Pre event) {
		if(fluid == null) return;
		
		if(fluid.getBlock() != null) {
			Block blockFluid = fluid.getBlock();
			
			fluid.setIcons(blockFluid.getBlockTextureFromSide(Consts.SIDE_TOP));
		}
		else {
			IIcon iconFluid = event.map.registerIcon(NameUtil.getDomainedTextureName(fluid.getName()));
			
			fluid.setIcons(iconFluid);
		}
	}
}
