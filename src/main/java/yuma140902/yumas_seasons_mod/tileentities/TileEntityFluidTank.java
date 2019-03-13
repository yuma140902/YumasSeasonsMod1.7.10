package yuma140902.yumas_seasons_mod.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import yuma140902.yumas_seasons_mod.fluid.CustomFluidTank;
import yuma140902.yumas_seasons_mod.fluid.IFluidTankContainer;
import yuma140902.yumas_seasons_mod.util.NameUtil;

public class TileEntityFluidTank extends TileEntity implements IFluidTankContainer {
	public static final String tileEntityId = NameUtil.getDomainedUnlocalizedName("fluidTank");
	
	public static final int MAX_AMOUNT_MB = 1000;
	
	private static final String
		NBT_KEY_INNER_TANK = "InnerTank";
	
	public CustomFluidTank innerTank = new CustomFluidTank(MAX_AMOUNT_MB);
	
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		this.innerTank = new CustomFluidTank(MAX_AMOUNT_MB);
		if(tag.hasKey(NBT_KEY_INNER_TANK)) {
			this.innerTank.readFromNBT(tag.getCompoundTag(NBT_KEY_INNER_TANK));
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		NBTTagCompound tankTag = new NBTTagCompound();
		this.innerTank.writeToNBT(tankTag);
		tag.setTag(NBT_KEY_INNER_TANK, tankTag);
	}
	
	
	// =========== データ同期 ここから ================
	
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
		Fluid fluid = this.getMainTank().getFluidType();
		return fluid != null ? fluid.getIcon() : null;
	}
	
	
	public int getAmount() {
		FluidStack fluid = this.getMainTank().getFluid();
		return fluid != null ? fluid.amount : 0;
	}
	
	// ============ 描画 ここまで ===============
	
	
	// ============== IFluidTank ここから =============
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(resource == null) {
			return null;
		}
		if(getMainTank().getFluidType() == resource.getFluid()) {
			return getMainTank().drain(resource.amount, doDrain);
		}
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return getMainTank().drain(maxDrain, doDrain);
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null || resource.getFluid() == null) {
			return 0;
		}
		
		FluidStack currentFluid = getMainTank().getFluid();
		FluidStack resourceBak = resource.copy();
		if(currentFluid != null && currentFluid.amount > 0 && !currentFluid.isFluidEqual(resourceBak)) {
			return 0;
		}
		
		int usedAmount = getMainTank().fill(resourceBak, doFill);
		resourceBak.amount -= usedAmount;
		
		return usedAmount;
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid != null && getMainTank().isEmpty(); // !getMainTank().isFull() では?
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {getMainTank().getInfo()};
	}
	
	@Override
	public CustomFluidTank getMainTank() {
		return innerTank;
	}
	
	// =============== IFluidTank ここまで =====================
}
