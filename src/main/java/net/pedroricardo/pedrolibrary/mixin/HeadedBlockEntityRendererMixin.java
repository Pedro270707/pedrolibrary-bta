package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ItemSign;
import net.minecraft.src.ItemStack;
import net.pedroricardo.headed.block.model.HeadedSkullModel;
import net.pedroricardo.pedrolibrary.interfaces.IBlockEntityRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ItemSign.class, remap = false)
public class HeadedBlockEntityRendererMixin implements IBlockEntityRenderer {
    @Override
    public int getTextureToRender(ItemStack itemStack) {
        return Minecraft.getMinecraft().renderEngine.getTexture("/mob/zombie.png");
    }

    @Override
    public void render(ItemStack itemStack, float x, float y, float z, float scaleX, float scaleY, float scaleZ) {
        HeadedSkullModel model = new HeadedSkullModel();
        model.render();
    }

    @Override
    public void applyBipedModelTransformations() {
        GL11.glTranslatef(-0.25F, -0.25F, 0.125F);
    }

    @Override
    public void applyFirstPersonModelTransformations() {
        GL11.glScalef(-1.25F, -1.25F, 1.25F);
        GL11.glTranslatef(-0.375F, -0.625F, 0.0F);
    }

    @Override
    public void applyGuiModelTransformations() {
        GL11.glScalef(1.25F, 1.25F, 1.25F);
    }
}
