package yuma140902.yumas_seasons_mod.core.client.renderer;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import yuma140902.yumas_seasons_mod.YumasSeasonsMod;
import yuma140902.yumas_seasons_mod.core.CoreBlocks;
import yuma140902.yumas_seasons_mod.lib.util.YSConstants;

// タンクの中身はTileEntityFluidTankRendererで描画している
public class RenderBlockTank implements ISimpleBlockRenderingHandler {
	private IIcon iconTop;
	private IIcon iconSide;
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		
		if(iconTop == null) {
			iconTop = CoreBlocks.tank.getIcon(YSConstants.SIDE_TOP, metadata);
		}
		if(iconSide == null) {
			iconSide = CoreBlocks.tank.getIcon(YSConstants.SIDE_NORTH, metadata);
		}
		
		if(modelId == this.getRenderId()) {
			//底
			renderInvCuboid(renderer, block,  0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 2.0F/16.0F, 16.0F/16.0F,  iconTop);
			
			//壁面
			renderInvCuboid(renderer, block,  0.0F/16.0F, 2.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 1.0F/16.0F,  iconSide);
			renderInvCuboid(renderer, block,  0.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F,  iconSide);
			renderInvCuboid(renderer, block,  0.0F/16.0F, 2.0F/16.0F, 1.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 15.0F/16.0F,  iconSide);
			renderInvCuboid(renderer, block,  15.0F/16.0F, 2.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 15.0F/16.0F,  iconSide);
		}
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (modelId == this.getRenderId())
		{
			if(iconTop == null) {
				iconTop = CoreBlocks.tank.getIcon(YSConstants.SIDE_TOP, 0);
			}
			if(iconSide == null) {
				iconSide = CoreBlocks.tank.getIcon(YSConstants.SIDE_NORTH, 0);
			}
			
			renderer.setOverrideBlockTexture(iconTop);
			block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 2.0F/16.0F, 16.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			renderer.setOverrideBlockTexture(iconSide);
			block.setBlockBounds(0.0F/16.0F, 2.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 1.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(iconSide);
			block.setBlockBounds(0.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(iconSide);
			block.setBlockBounds(0.0F/16.0F, 2.0F/16.0F, 1.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 15.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(iconSide);
			block.setBlockBounds(15.0F/16.0F, 2.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 15.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			
			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
	
	@Override
	public int getRenderId() {
		return YumasSeasonsMod.ysCore.renderIdTank;
	}
	
	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, IIcon icon) {
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setRenderBoundsFromBlock(block);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}
}
