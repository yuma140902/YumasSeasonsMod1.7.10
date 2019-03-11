package yuma140902.yumas_seasons_mod.client.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import yuma140902.yumas_seasons_mod.tileentities.TileEntityFluidTank;

public class TileEntityFluidTankRenderer extends TileEntitySpecialRenderer {
	
	public static TileEntityFluidTankRenderer renderer;
	
	public void
			renderTileEntityCupAt(TileEntityFluidTank tileentity, double par2, double par4, double par6, double par8) {
		this.setRotation(tileentity, (float) par2, (float) par4, (float) par6);
	}
	
	public void setTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcher) {
		super.func_147497_a(rendererDispatcher);
		renderer = this;
	}
	
	public void setRotation(TileEntityFluidTank tileentity, float par1, float par2, float par3) {
		// テッセレータを使って、一枚の平面テクスチャとして表示させる
		
		Tessellator tessellator = Tessellator.instance;
		
		if (tileentity.getFluidIcon() != null) {
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 2.0F);
			
			GL11.glTranslatef((float) par1, (float) par2 + 0.5F, (float) par3);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			
			IIcon icon = tileentity.getFluidIcon();
			float minU = icon.getMinU();
			float maxU = icon.getMaxU();
			float minV = icon.getMinV();
			float maxV = icon.getMaxV();
			
			this.bindTexture(TextureMap.locationBlocksTexture);
			
			float f = 0.0625F; //タンクの縁の幅
			double tank_height = 0.3D + f + 0.4D;
			double height = 0.3D + f - (tileentity.getAmount() * tank_height / TileEntityFluidTank.MAX_AMOUNT_MB);
			
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(0.0D + f, height, -1.0D + f, (double) maxU, (double) minV);
			tessellator.addVertexWithUV(1.0D - f, height, -1.0D + f, (double) minU, (double) minV);
			tessellator.addVertexWithUV(1.0D - f, height, 0.0D - f, (double) minU, (double) maxV);
			tessellator.addVertexWithUV(0.0D + f, height, 0.0D - f, (double) maxU, (double) maxV);
			tessellator.draw();
			
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			// GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityCupAt((TileEntityFluidTank)tileentity, par2, par4, par6, par8);
	}
}
