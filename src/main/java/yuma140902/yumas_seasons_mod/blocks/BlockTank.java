package yuma140902.yumas_seasons_mod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import yuma140902.yumas_seasons_mod.IHasRecipe;
import yuma140902.yumas_seasons_mod.IRegisterable;
import yuma140902.yumas_seasons_mod.ModYumasSeasonsMod;
import yuma140902.yumas_seasons_mod.tileentities.TileEntityFluidTank;
import yuma140902.yumas_seasons_mod.util.Consts;
import yuma140902.yumas_seasons_mod.util.NameUtil;

public class BlockTank extends BlockContainer implements IRegisterable, IHasRecipe {
	public BlockTank() {
		super(Material.rock);
		setStepSound(soundTypeStone);
		setHardness(1.0F);
		setCreativeTab(ModYumasSeasonsMod.MOD_CREATIVETAB);
	}
	
	private IIcon iconTop;
	private IIcon iconSide;
	
	@Override
	public boolean onBlockActivated(
			World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		/* 手持ちアイテム */
		ItemStack itemstackHeld = player.getHeldItem();
		/* このブロックのTileEntity */
		TileEntityFluidTank tileentity = (TileEntityFluidTank) world.getTileEntity(x, y, z);
		
		if(tileentity == null) {
			return true;
		}
		
		// TileEntityの液体タンクに入っている液体を取得
		FluidStack fluid = tileentity.innerTank.getFluid();
		
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
				int put = tileentity.fill(ForgeDirection.UNKNOWN, fluidHeld, false);
				
				// 全量投入可能なときのみ
				if (put == fluidHeld.amount) {
					// 今度は液体を液体タンクに入れるので、第二引数はtrueにする。
					tileentity.fill(ForgeDirection.UNKNOWN, fluidHeld, true);
					
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
					tileentity.markDirty();
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
					System.out.println("amountToDrain : " + amountToDrain);
					if (fluid.amount < amountToDrain)
						return true;
					
					if (filledContainer != null) {
						/*
						 * タンクの液体の減少処理
						 * タンク容量 > amountToDrain であることを事前にチェック済みのためにここではシュミレート無しとしたが、
						 * fillの場合と同様に、シュミレートで投入可能量を確かめでから行っても良いと思う。
						 */
						
						tileentity.drain(ForgeDirection.UNKNOWN, amountToDrain, true);
						
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
						tileentity.markDirty();
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
	
	@Override
	public void register() {
		setBlockName(NameUtil.getDomainedUnlocalizedName("tank"));
		setBlockTextureName(NameUtil.getDomainedTextureName("tank"));
		GameRegistry.registerBlock(this, "tank");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(this,
				"# #",
				"#V#",
				"###",
				'#', "cobblestone",
				'V', Items.bucket
				));
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		iconTop = register.registerIcon(getTextureName() + "_top");
		iconSide = register.registerIcon(getTextureName() + "_side");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == Consts.SIDE_TOP || side == Consts.SIDE_BOTTOM) {
			return iconTop;
		}
		return iconSide;
	}
	
	// // 当たり判定を設定。大釜のように中にエンティティが入り込める。
	// @SuppressWarnings("rawtypes")
	// @Override
	// public void addCollisionBoxesToList(World world, int x, int y, int z,
	// AxisAlignedBB aabb, List list, Entity entity) {
	// this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	// super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
	// float f = 0.0675F;
	// this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
	// super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
	// this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
	// super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
	// this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	// super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
	// this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
	// super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
	// this.setBlockBoundsForItemRender();
	// }
	//
	// @Override
	// public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int
	// y, int z) {
	// return AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z,
	// (double)x + 1.0F, (double)y + 1.0F, (double)z + 1.0F);
	// }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return ModYumasSeasonsMod.INSTANCE.renderIdTank;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFluidTank();
	}
}
