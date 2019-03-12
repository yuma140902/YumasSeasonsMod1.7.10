package yuma140902.yumas_seasons_mod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import yuma140902.yumas_seasons_mod.IRegisterable;
import yuma140902.yumas_seasons_mod.ModYumasSeasonsMod;
import yuma140902.yumas_seasons_mod.MyBlocks;
import yuma140902.yumas_seasons_mod.MyFluids;
import yuma140902.yumas_seasons_mod.fluid.FillBucketHandler;
import yuma140902.yumas_seasons_mod.util.NameUtil;

public class ItemBucketAppleJuice extends ItemBucket implements IRegisterable, IFluidContainerItem {
	public ItemBucketAppleJuice() {
		super(MyBlocks.liquidAppleJuice);
		setCreativeTab(ModYumasSeasonsMod.MOD_CREATIVETAB);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(NameUtil.getDomainedUnlocalizedName("bucket_apple_juice"));
		this.setTextureName(NameUtil.getDomainedTextureName("bucket_apple_juice"));
		GameRegistry.registerItem(this, "bucket_apple_juice");
		FluidContainerRegistry.registerFluidContainer(new FluidStack(MyFluids.appleJuice, 1000), new ItemStack(this), new ItemStack(Items.bucket));
		FillBucketHandler.INSTANCE.registerCustomBucket(MyBlocks.liquidAppleJuice, this);
	}

	@Override
	public FluidStack getFluid(ItemStack container) {
		return new FluidStack(MyFluids.appleJuice, 1000);
	}

	@Override
	public int getCapacity(ItemStack container) {
		return 1000;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill) {
		if(resource != null && resource.getFluid() == MyFluids.appleJuice) {
			if(resource.amount >= 1000) return 1000;
			else return 0;
		}
		return 0;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
		if(maxDrain < 1000) return null;
		return new FluidStack(MyFluids.appleJuice, 1000);
	}
}
