package yuma140902.yumas_seasons_mod.core.gui.invnetory;

import java.util.Arrays;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import yuma140902.yumas_seasons_mod.YumasSeasonsMod;
import yuma140902.yumas_seasons_mod.core.tileentities.TileEntitySqueezer;

public class SqueezerGuiContainer extends GuiContainer {
	
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(YumasSeasonsMod.MOD_TEXTURE_DOMAIN, "textures/gui/squeezer.png");
	
	private TileEntitySqueezer tileentity;
	
	public SqueezerGuiContainer(EntityPlayer player, TileEntitySqueezer tilentity) {
		super(new SqueezerContainer(player, tilentity));
		this.tileentity = tilentity;
		this.xSize = 176;
		this.ySize = 195;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(StatCollector.translateToLocal(tileentity.getInventoryName()), 8, 5, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, 101, 4210752);
		
		fontRendererObj.drawString("procTick: " + tileentity.procTick, 0, 0, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float tick, int mouseX, int mouseY) {
		this.mc.renderEngine.bindTexture(GUI_TEXTURE);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
		
		int max_width = 22;
		int width = 1 + tileentity.procTick * max_width / TileEntitySqueezer.MAX_PROC_TICK;
		this.drawTexturedModalRect(this.guiLeft + 77, this.guiTop + 50, xSize, 0, width, 17);
		
		IIcon fluidIcon = tileentity.getFluidIcon();
		if(fluidIcon != null) {
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 2.0F);
			
			this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			for(int i = 0; i < 5; ++i) {
				this.drawTexturedModelRectFromIcon(this.guiLeft + 112, this.guiTop + 84 - i*16, fluidIcon, 16, 16);
			}
			this.drawTexturedModelRectFromIcon(this.guiLeft + 112, this.guiTop + 15, fluidIcon, 16, 5);
			
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			
			
			int max_height = 85;
			int height = max_height - (tileentity.getAmount() * max_height / TileEntitySqueezer.MAX_AMOUNT_MB);
			
			this.mc.renderEngine.bindTexture(GUI_TEXTURE);
			this.drawTexturedModalRect(this.guiLeft + 112, this.guiTop + 15, 112, 15, 16, height);
			
			if(this.guiLeft + 112 <= mouseX && mouseX <= this.guiLeft + 128 && this.guiTop + 15 + height <= mouseY && mouseY <= this.guiTop + 100) {
				@SuppressWarnings("rawtypes")
				List list = Arrays.asList(
						StatCollector.translateToLocal(tileentity.outputTank.getFluidName()), 
						tileentity.getAmount() + "mB / " + TileEntitySqueezer.MAX_AMOUNT_MB + "mB"
						);
				this.drawHoveringText(list, mouseX, mouseY, fontRendererObj);
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
