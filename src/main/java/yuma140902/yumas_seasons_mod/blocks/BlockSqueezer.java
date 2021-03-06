package yuma140902.yumas_seasons_mod.blocks;

import java.util.EnumSet;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import yuma140902.yumas_seasons_mod.IHasRecipe;
import yuma140902.yumas_seasons_mod.IRegisterable;
import yuma140902.yumas_seasons_mod.ModYumasSeasonsMod;
import yuma140902.yumas_seasons_mod.client.texture.CommonTextures;
import yuma140902.yumas_seasons_mod.fluid.FillFluidContainerHandler;
import yuma140902.yumas_seasons_mod.fluid.FillFluidContainerHandler.Operation;
import yuma140902.yumas_seasons_mod.gui.ModGuiHandler;
import yuma140902.yumas_seasons_mod.tileentities.TileEntitySqueezer;
import yuma140902.yumas_seasons_mod.util.Consts;
import yuma140902.yumas_seasons_mod.util.NameUtil;

public class BlockSqueezer extends BlockContainer implements IRegisterable, IHasRecipe {
	public BlockSqueezer() {
		super(Material.rock);
		setStepSound(soundTypeStone);
		setCreativeTab(ModYumasSeasonsMod.MOD_CREATIVETAB);
	}
	
	private static final int
		META_SOUTH = 0,
		META_WEST = 1,
		META_NORTH = 2,
		META_EAST = 3;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon iconSide;
	
	@Override
	public void register() {
		setBlockName(NameUtil.getDomainedUnlocalizedName("squeezer"));
		setBlockTextureName(NameUtil.getDomainedTextureName("squeezer"));
		GameRegistry.registerBlock(this, "squeezer");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(this, 
				"#_#",
				"#C#",
				"###",
				'#', "cobblestone",
				'_', "slabWood",
				'C', Items.string
				));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		iconTop = register.registerIcon(getTextureName() + "_top");
		iconBottom = register.registerIcon(CommonTextures.commonMachineBottom);
		iconFront = register.registerIcon(getTextureName() + "_front");
		iconSide = register.registerIcon(getTextureName() + "_side");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == Consts.SIDE_TOP) {
			return iconTop;
		}
		else if(side == Consts.SIDE_BOTTOM) {
			return iconBottom;
		}
		else if((meta == META_NORTH && side == Consts.SIDE_NORTH)
				|| (meta == META_WEST && side == Consts.SIDE_WEST)
				|| (meta == META_SOUTH && side == Consts.SIDE_SOUTH)
				|| (meta == META_EAST && side == Consts.SIDE_EAST)) {
			return iconFront;
		}
		return iconSide;
	}
	
	@Override
	public boolean onBlockActivated(	World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		boolean shouldOpenGui = !FillFluidContainerHandler.fillFluidContainerOnBlockActivated(EnumSet.of(Operation.SHOW, Operation.DRAIN), world, x, y, z, player);
		
		if(shouldOpenGui) {
			player.openGui(ModYumasSeasonsMod.INSTANCE, ModGuiHandler.SQUEEZER, world, x, y, z);
		}
		
		return true;
	}
	
	/*
	 * メタデータの仕様
	 * 南向き: 0
	 * 西向き: 1
	 * 北向き: 2
	 * 東向き: 3
	 */
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
		int meta = MathHelper.floor_double((double)(entity.rotationYawHead * 4.0F / 360.0F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntitySqueezer tileentity = (TileEntitySqueezer) world.getTileEntity(x, y, z);
		ItemStack itemstack = (tileentity.inputSlot);
		if(itemstack != null && !world.isRemote) {
			Random rand = world.rand;
			float xDiff = rand.nextFloat() * 0.6F + 0.1F;
			float yDiff = rand.nextFloat() * 0.6F + 0.1F;
			float zDiff = rand.nextFloat() * 0.6F + 0.1F;
			
			EntityItem entityItem = new EntityItem(world, x + xDiff, y + yDiff, z + zDiff, itemstack.copy());
			world.spawnEntityInWorld(entityItem);
		}
		
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntitySqueezer();
	}
}
