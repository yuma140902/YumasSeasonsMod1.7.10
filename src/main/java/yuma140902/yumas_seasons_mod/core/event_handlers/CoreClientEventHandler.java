package yuma140902.yumas_seasons_mod.core.event_handlers;

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
import yuma140902.yumas_seasons_mod.core.CoreFluids;
import yuma140902.yumas_seasons_mod.lib.config.YSConfigCore;
import yuma140902.yumas_seasons_mod.lib.util.NameUtil;
import yuma140902.yumas_seasons_mod.lib.util.YSConstants;

public class CoreClientEventHandler {
private CoreClientEventHandler() {}
	
	public static final CoreClientEventHandler INSTANCE = new CoreClientEventHandler();
	
	@SubscribeEvent
	public void onTooltipShown(ItemTooltipEvent event) {
		if(YSConfigCore.debugMode) {
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
		
		registerFluidTexture(CoreFluids.appleJuice, event);
	}
	
	private void registerFluidTexture(Fluid fluid, TextureStitchEvent.Pre event) {
		if(fluid == null) return;
		
		if(fluid.getBlock() != null) {
			Block blockFluid = fluid.getBlock();
			
			fluid.setIcons(blockFluid.getBlockTextureFromSide(YSConstants.SIDE_TOP));
		}
		else {
			IIcon iconFluid = event.map.registerIcon(NameUtil.getDomainedTextureName(fluid.getName()));
			
			fluid.setIcons(iconFluid);
		}
	}
}
