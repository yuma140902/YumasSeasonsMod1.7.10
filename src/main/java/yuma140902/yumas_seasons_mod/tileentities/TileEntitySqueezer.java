package yuma140902.yumas_seasons_mod.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import yuma140902.yumas_seasons_mod.fluid.CustomFluidTank;
import yuma140902.yumas_seasons_mod.fluid.IFluidTankContainer;
import yuma140902.yumas_seasons_mod.recipes.Recipes;
import yuma140902.yumas_seasons_mod.util.NameUtil;

public class TileEntitySqueezer extends TileEntity implements IInventory, IFluidTankContainer {
	public static final String tileEntityId = NameUtil.getDomainedUnlocalizedName("squeezer");
	
	public static final int MAX_AMOUNT_MB = 1000;
	public static final int MAX_PROC_TICK = 200;
	
	private static final int INVENTORY_SIZE = 1;
	
	private static final String INVENTORY_NAME = NameUtil.getDomainedUnlocalizedName("squeezer");
	
	private static final String
		NBT_KEY_OUTPUT_TANK = "OutputTank",
		NBT_KEY_INTPUT_SLOT = "InputSlot",
		NBT_KEY_PROC_TICK = "ProcTick",
		NBT_KEY_STATUS = "Status";
	
	private static final int
		STATUS_IDLING = 0,
		STATUS_PROCESSING = 1;
	
	public CustomFluidTank outputTank = new CustomFluidTank(MAX_AMOUNT_MB);
	public ItemStack inputSlot;
	public int procTick = 0;
	private int status = STATUS_IDLING;
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		this.outputTank = new CustomFluidTank(MAX_AMOUNT_MB);
		if(tag.hasKey(NBT_KEY_OUTPUT_TANK)) {
			this.outputTank.readFromNBT(tag.getCompoundTag(NBT_KEY_OUTPUT_TANK));
		}
		
		if(tag.hasKey(NBT_KEY_INTPUT_SLOT)) {
			this.inputSlot = ItemStack.loadItemStackFromNBT(tag.getCompoundTag(NBT_KEY_INTPUT_SLOT));
		}
		
		if(tag.hasKey(NBT_KEY_PROC_TICK)) {
			this.procTick = tag.getInteger(NBT_KEY_PROC_TICK);
		}
		
		if(tag.hasKey(NBT_KEY_STATUS)) {
			this.status = tag.getInteger(NBT_KEY_STATUS);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		NBTTagCompound tankTag = new NBTTagCompound();
		this.outputTank.writeToNBT(tankTag);
		tag.setTag(NBT_KEY_OUTPUT_TANK, tankTag);
		
		if(this.inputSlot != null) {
			NBTTagCompound slotTag = new NBTTagCompound();
			this.inputSlot.writeToNBT(slotTag);
			tag.setTag(NBT_KEY_INTPUT_SLOT, slotTag);
		}
		
		tag.setInteger(NBT_KEY_PROC_TICK, this.procTick);
		
		tag.setInteger(NBT_KEY_STATUS, this.status);
	}
	
	
	// ============ レシピ処理 ここから ===============
	
	@Override
	public void updateEntity() {
		if(status == STATUS_IDLING) {
			startProcessingIfNecessary();
		}
		else if(status == STATUS_PROCESSING) {
			processTick();
		}
		else {
			status = STATUS_IDLING;
		}
	}
	
	private void startProcessingIfNecessary() {
		if(inputSlot == null) {
			return;
		}
		
		FluidStack product = Recipes.squeezer.getProduct(inputSlot);
		if(product == null) {
			return;
		}
		
		int usedAmount = this.fill(ForgeDirection.UNKNOWN, product, false);
		// 全量を内部タンクに投入可能なとき
		if(usedAmount == product.amount) {
			status = STATUS_PROCESSING;
		}
	}
	
	/**
	 * 処理を進めます
	 */
	private void processTick() {
		if(this.inputSlot == null) {
			procTick = 0;
			status = STATUS_IDLING;
			return;
		}
		
		if(procTick >= MAX_PROC_TICK) {
			this.fill(ForgeDirection.UNKNOWN, Recipes.squeezer.getProduct(inputSlot), true);
			procTick = 0;
			--this.inputSlot.stackSize;
			
			if(this.inputSlot.stackSize <= 0) {
				this.inputSlot = null;
				status = STATUS_IDLING;
				return;
			}
		}
		++procTick;
	}
	
	// ============ レシピ処理 ここまで ===============
	
	
	//=========== データ同期 ここから ================
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}
	
	// ============= データ同期 ここまで =================
	
	
	// ============ 描画 ここから ===================
	
	@SideOnly(Side.CLIENT)
	public IIcon getFluidIcon() {
		Fluid fluid = this.outputTank.getFluidType();
		return fluid != null ? fluid.getIcon() : null;
	}
	
	
	public int getAmount() {
		FluidStack fluid = this.outputTank.getFluid();
		return fluid != null ? fluid.amount : 0;
	}
	
	// ============ 描画 ここまで ===============
	
	
	// ============== IFluidTank ここから =============
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(resource == null) {
			return null;
		}
		if(outputTank.getFluidType() == resource.getFluid()) {
			return outputTank.drain(resource.amount, doDrain);
		}
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.outputTank.drain(maxDrain, doDrain);
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null || resource.getFluid() == null) {
			return 0;
		}
		
		FluidStack currentFluid = this.outputTank.getFluid();
		FluidStack resourceCpy = resource.copy();
		if(currentFluid != null && currentFluid.amount > 0 && !currentFluid.isFluidEqual(resourceCpy)) {
			return 0;
		}
		
		int usedAmount = this.outputTank.fill(resourceCpy, doFill);
		resourceCpy.amount -= usedAmount;
		
		return usedAmount;
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid != null && this.outputTank.isEmpty(); // !this.innerTank.isFull() では?
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {this.outputTank.getInfo()};
	}
	
	@Override
	public FluidTank getMainTank() {
		return this.outputTank;
	}
	
	// =============== IFluidTank ここまで =================
	
	
	// =============== IInventory ここから =================
	
	@Override
	public int getSizeInventory() {
		return INVENTORY_SIZE;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		if(0 <= slot && slot < INVENTORY_SIZE) {
			return inputSlot;
		}
		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if(inputSlot == null) return null;
		
		if(amount >= inputSlot.stackSize) {
			ItemStack tmp = inputSlot;
			inputSlot = null;
			this.markDirty();
			return tmp;
		}
		
		ItemStack itemstack = inputSlot.splitStack(amount);
		if(inputSlot.stackSize == 0) {
			inputSlot = null;
		}
		this.markDirty();
		return itemstack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		inputSlot = itemstack;
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		this.markDirty();
	}
	
	@Override
	public String getInventoryName() {
		return INVENTORY_NAME;
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void openInventory() {}
	
	@Override
	public void closeInventory() {}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return true;
	}
	
	// =============== IInventory ここまで =================
	
}
