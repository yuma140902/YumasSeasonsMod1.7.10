package yuma140902.yumas_seasons_mod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;
import yuma140902.yumas_seasons_mod.IRegisterable;
import yuma140902.yumas_seasons_mod.MyFluids;
import yuma140902.yumas_seasons_mod.util.NameUtil;

public class BlockLiquidAppleJuice extends BlockFluidClassic implements IRegisterable {
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFlow;
	@SideOnly(Side.CLIENT)
	private IIcon iconStill;
	
	public BlockLiquidAppleJuice() {
		super(MyFluids.appleJuice, Material.water);
	}
	
	@Override
	public void register() {
		setBlockName(NameUtil.getDomainedUnlocalizedName("liquid_apple_juice"));
		setBlockTextureName(NameUtil.getDomainedTextureName("liquid_apple_juice"));
		GameRegistry.registerBlock(this, "liquidAppleJuice");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		this.iconFlow = register.registerIcon(this.getTextureName() + "_flow");
		this.iconStill = register.registerIcon(this.getTextureName() + "_still");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta == 0) return iconStill;
		return iconFlow;
	}
}
