package yuma140902.yumas_seasons_mod.core.gui.invnetory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import yuma140902.yumas_seasons_mod.core.tileentities.TileEntitySqueezer;

public class SqueezerContainer extends Container {
	
	private TileEntitySqueezer tileentity;
	
	public SqueezerContainer(EntityPlayer player, TileEntitySqueezer tileentity) {
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 50, 52));
		
		for (int iy = 0; iy < 3; iy++) {
			for (int ix = 0; ix < 9; ix++) {
				this.addSlotToContainer(new Slot(player.inventory, ix + (iy * 9) + 9, 8 + (ix * 18), 113 + (iy * 18)));
			}
		}
		for (int ix = 0; ix < 9; ix++) {
			this.addSlotToContainer(new Slot(player.inventory, ix, 8 + (ix * 18), 171));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileentity.isUseableByPlayer(player);
	}
	
	private static final int
		SQUEEZER_SLOT = 0,
		INV_START = 1,
		INV_END = 27,
		HOTBAR_START = 28,
		HOTBAR_END = 36;
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
		Slot slot = (Slot) inventorySlots.get(slotNum);
		
		if(slot == null || !slot.getHasStack()) {
			return null;
		}
		
		ItemStack itemstack = slot.getStack();
		ItemStack itemstackBak = itemstack.copy();
		
		if(slotNum == SQUEEZER_SLOT) {
			if(!this.mergeItemStack(itemstack, INV_START, HOTBAR_END + 1, true)) {
				return null;
			}
		}else {
			if(!this.mergeItemStack(itemstack, SQUEEZER_SLOT, SQUEEZER_SLOT + 1, false)) {
				return null;
			}
		}
		
		if(itemstack.stackSize == 0) {
			slot.putStack(null);
		}
		else {
			slot.onSlotChanged();
		}
		
		if(itemstack.stackSize == itemstackBak.stackSize) {
			return null;
		}
		slot.onPickupFromSlot(player, itemstack);
		
		return itemstackBak;
	}
	
}
