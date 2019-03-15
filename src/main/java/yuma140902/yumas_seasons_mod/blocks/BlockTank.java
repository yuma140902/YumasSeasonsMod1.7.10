package yuma140902.yumas_seasons_mod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import yuma140902.yumas_seasons_mod.IHasRecipe;
import yuma140902.yumas_seasons_mod.IRegisterable;
import yuma140902.yumas_seasons_mod.ModYumasSeasonsMod;
import yuma140902.yumas_seasons_mod.client.texture.CommonTextures;
import yuma140902.yumas_seasons_mod.fluid.FillFluidContainerHandler;
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
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconSide;
	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;
	
	@Override
	public boolean onBlockActivated(	World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return FillFluidContainerHandler.fillFluidContainerOnBlockActivated(world, x, y, z, player);
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		iconTop = register.registerIcon(CommonTextures.commonMachineTop);
		iconSide = register.registerIcon(CommonTextures.commonMachineSide);
		iconBottom = register.registerIcon(CommonTextures.commonMachineBottom);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == Consts.SIDE_TOP) {
			return iconTop;
		}
		else if(side == Consts.SIDE_BOTTOM) {
			return iconBottom;
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
