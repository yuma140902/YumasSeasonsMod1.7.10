package yuma140902.yumas_seasons_mod.fluid;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FillFluidContainerHandler {
	private FillFluidContainerHandler() {}
	
	/**
	 * 液体コンテナアイテムと内部にタンクを持つブロックとのやり取りの処理。
	 * 満たされた液体コンテナを持っていればタンクへの搬入を試み、
	 * 空の液体コンテナを持っていればタンクからの搬出を試みる。
	 * 素手ならタンクの内容をチャットに表示する。
	 * Block#onBlockActivated()から呼び出されることを想定してる
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param player
	 * @return
	 */
	public static boolean fillFluidContainerOnBlockActivated(World world, int x, int y, int z, EntityPlayer player) {
		/* 手持ちアイテム */
		ItemStack itemstackHeld = player.getHeldItem();
		/* このブロックのTileEntity */
		TileEntity tileentity = world.getTileEntity(x, y, z);
		
		if(tileentity == null || !(tileentity instanceof IFluidTank)) {
			return true;
		}
		
		IFluidTank fluidTank = (IFluidTank) tileentity;
		
		// TileEntityの液体タンクに入っている液体を取得
		FluidStack fluid = fluidTank.getInnerTank().getFluid();
		
		if (itemstackHeld == null)// 素手
		{
			String msg;
			
			if (fluid != null && fluid.getFluid() != null) {
				msg = "Fluid: " + StatCollector.translateToLocal(fluid.getFluid().getUnlocalizedName(fluid)) + " " + fluid.amount + "mB";
			}
			else {
				msg = "No fluid in the tank";
			}
			
			if (!world.isRemote)
				player.addChatMessage(new ChatComponentText(msg));
			
			return true;
		}
		else {
			// このメソッドにより、手持ちのアイテムが液体容器に登録されたアイテムかどうか、及び入っている液体を取得する。
			FluidStack fluidHeld = FluidContainerRegistry.getFluidForFilledItem(itemstackHeld);
			
			// 満たされた液体コンテナを持っている場合
			if (fluidHeld != null && fluidHeld.getFluid() != null) {
				/*
				 * fillメソッドの第二引数にfalseを入れた場合、実際に液体をタンクに入れるのではなく、
				 * タンクに投入可能な液体の量をシュミレートして値を返す。
				 */
				int put = fluidTank.fill(ForgeDirection.UNKNOWN, fluidHeld, false);
				
				// 全量投入可能なときのみ
				if (put == fluidHeld.amount) {
					// 今度は液体を液体タンクに入れるので、第二引数はtrueにする。
					fluidTank.fill(ForgeDirection.UNKNOWN, fluidHeld, true);
					
					// 液体容器を空にして、空容器を得る。
					ItemStack emptyContainer = FluidContainerRegistry.drainFluidContainer(itemstackHeld);
					if (emptyContainer != null && !player.capabilities.isCreativeMode) {
						if (!player.inventory.addItemStackToInventory(emptyContainer.copy())) {
							player.entityDropItem(emptyContainer.copy(), 1);
						}
					}
					
					// プレイヤーの手持ちアイテムを減らす処理
					if (!player.capabilities.isCreativeMode && itemstackHeld.stackSize-- <= 0) {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
					}
					
					// 更新を伝える処理
					// TileEntityを更新した場合、このように更新処理を挟まないと見た目に反映しない。
					fluidTank.markDirty();
					player.inventory.markDirty();
					world.markBlockForUpdate(x, y, z);
					
					// 効果音の発生
					world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
					
					return true;
				}
			}
			else {
				// 液体タンクに何かしら入っている時
				if (fluid != null && fluid.getFluid() != null) {
					
					ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(fluid, itemstackHeld);
					
					int amountToDrain = FluidContainerRegistry.getContainerCapacity(filledContainer);
					if (fluid.amount < amountToDrain)
						return true;
					
					if (filledContainer != null) {
						/*
						 * タンクの液体の減少処理
						 * タンク容量 > amountToDrain であることを事前にチェック済みのためにここではシュミレート無しとしたが、
						 * fillの場合と同様に、シュミレートで投入可能量を確かめでから行っても良いと思う。
						 */
						
						fluidTank.drain(ForgeDirection.UNKNOWN, amountToDrain, true);
						
						// プレイヤーに、先に取得した「液体で満たされた容器アイテム」を与える処理
						if(!player.capabilities.isCreativeMode) {
							if (!player.inventory.addItemStackToInventory(filledContainer.copy())) {
								player.entityDropItem(filledContainer.copy(), 1);
							}
						}
						
						// プレイヤーの手持ちアイテムを減らす処理
						if (!player.capabilities.isCreativeMode && itemstackHeld.stackSize-- <= 0) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
						}
						
						// 更新を伝える処理
						// TileEntityを更新した場合、このように更新処理を挟まないと見た目に反映しない。
						fluidTank.markDirty();
						player.inventory.markDirty();
						world.markBlockForUpdate(x, y, z);
						
						// 効果音の発生
						world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
					}
					
					return true;
				}
				else {
					// アイテムが液体入り容器でなく、かつタンクが空だった場合は何もしない
					return true;
				}
			}
		}
		
		return true;
	}
}
