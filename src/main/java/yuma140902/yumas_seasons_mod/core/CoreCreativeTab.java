package yuma140902.yumas_seasons_mod.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yuma140902.yumas_seasons_mod.YumasSeasonsMod;

public class CoreCreativeTab extends CreativeTabs {
	
	public CoreCreativeTab() {
		super(YumasSeasonsMod.MOD_UNLOCALIZED_DOMAIN);
	}
	
	@SideOnly(Side.CLIENT)
	private static final ItemStack icon = new ItemStack(Blocks.log);
	
	@SideOnly(Side.CLIENT)
	@Override
	public Item getTabIconItem() {
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getIconItemStack() {
		return icon;
	}
	
	
}
