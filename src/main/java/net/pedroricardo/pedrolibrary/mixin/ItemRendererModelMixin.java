package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IBlockEntityRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemRenderer.class, remap = false)
public class ItemRendererModelMixin {
    @Mixin(value = ItemRenderer.class, remap = false)
    private interface ItemToRenderAccessor {
        @Accessor("itemToRender")
        ItemStack itemToRender();
    }

    @Inject(method = "renderItem(Lnet/minecraft/src/Entity;Lnet/minecraft/src/ItemStack;Z)V", at = @At("HEAD"), cancellable = true)
    public void renderItem(Entity entity, ItemStack itemstack, boolean handheldtransform, CallbackInfo ci) {
        if (itemstack.getItem() instanceof IBlockEntityRenderer) {
            IBlockEntityRenderer item = (IBlockEntityRenderer)itemstack.getItem();
            if (item.renderWithCustomModel()) {
                GL11.glScalef(-1.0F, -1.0F, 1.0F);
                GL11.glTranslatef(-0.53125F, -1.0F, 0.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(item.getTextureToRender(itemstack));
                item.render(itemstack, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                ci.cancel();
            }
        }
    }

    @Inject(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glPushMatrix()V", ordinal = 0, shift = At.Shift.AFTER), remap = false)
    public void renderItemInFirstPerson(float f, CallbackInfo ci) {
        ItemStack itemStack = ((ItemToRenderAccessor)((ItemRenderer)(Object)this)).itemToRender();
        if (itemStack != null && itemStack.getItem() instanceof IBlockEntityRenderer) {
            ((IBlockEntityRenderer) itemStack.getItem()).applyFirstPersonModelTransformations();
        }
    }
}
