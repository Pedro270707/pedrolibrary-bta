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
    private interface ItemRendererAccessor {
        @Accessor("itemToRender")
        ItemStack itemToRender();

        @Accessor("prevEquippedProgress")
        float prevEquippedProgress();

        @Accessor("equippedProgress")
        float equippedProgress();

        @Accessor("mc")
        Minecraft mc();
    }

    @Inject(method = "renderItem(Lnet/minecraft/src/Entity;Lnet/minecraft/src/ItemStack;Z)V", at = @At("HEAD"), cancellable = true)
    public void renderItem(Entity entity, ItemStack itemstack, boolean handheldtransform, CallbackInfo ci) {
        if (itemstack.getItem() instanceof IBlockEntityRenderer) {
            IBlockEntityRenderer item = (IBlockEntityRenderer)itemstack.getItem();
            if (item.renderWithCustomModel() && (!item.respect3DItemsOption() || Minecraft.getMinecraft().gameSettings.items3D.value)) {
                GL11.glPushMatrix();
                GL11.glScalef(-1.0F, -1.0F, 1.0F);
                GL11.glTranslatef(-0.53125F, -1.0F, 0.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(item.getTextureToRender(itemstack));
                item.render(itemstack, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
                ci.cancel();
            }
        }
    }

    @Inject(method = "renderItemInFirstPerson", at = @At(value = "HEAD"), remap = false, cancellable = true)
    public void renderItemInFirstPerson(float f, CallbackInfo ci) {
        ItemRenderer thisObj = (ItemRenderer)(Object)this;
        float f1 = ((ItemRendererAccessor)thisObj).prevEquippedProgress() + (((ItemRendererAccessor)thisObj).equippedProgress() - ((ItemRendererAccessor)thisObj).prevEquippedProgress()) * f;
        EntityPlayerSP entityplayersp = ((ItemRendererAccessor)thisObj).mc().thePlayer;
        ItemStack itemStack = ((ItemRendererAccessor)((ItemRenderer)(Object)this)).itemToRender();
        if (itemStack != null && itemStack.getItem() instanceof IBlockEntityRenderer) {
            if (!((IBlockEntityRenderer)itemStack.getItem()).respect3DItemsOption() || Minecraft.getMinecraft().gameSettings.items3D.value) {
                GL11.glPushMatrix();
                float f9 = entityplayersp.getSwingProgress(f);
                float f13 = MathHelper.sin(f9 * 3.141593f);
                float f17 = MathHelper.sin(MathHelper.sqrt_float(f9) * 3.141593f);
                GL11.glTranslatef(-f17 * 0.4f, MathHelper.sin(MathHelper.sqrt_float(f9) * 3.141593f * 2.0f) * 0.2f,
                        -f13 * 0.2f);
                GL11.glTranslatef(0.0F, -(1.0f - f1) * 0.6f, 0.0F);
                GL11.glEnable(32826);
                f9 = entityplayersp.getSwingProgress(f);
                f13 = MathHelper.sin(f9 * f9 * 3.141593f);
                f17 = MathHelper.sin(MathHelper.sqrt_float(f9) * 3.141593f);
                GL11.glRotatef(-f13 * 20.0f, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(-f17 * 20.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(-f17 * 80.0f, 1.0f, 0.0f, 0.0f);
                IBlockEntityRenderer item = ((IBlockEntityRenderer) itemStack.getItem());
                Minecraft.getMinecraft().renderEngine.bindTexture(item.getTextureToRender(itemStack));
                float f3 = ((ItemRendererAccessor) thisObj).mc().theWorld.getLightBrightness(
                        MathHelper.floor_double(entityplayersp.posX), MathHelper.floor_double(entityplayersp.posY),
                        MathHelper.floor_double(entityplayersp.posZ));
                if (((ItemRendererAccessor) thisObj).mc().fullbright) {
                    f3 = 1.0F;
                }
                int i = Item.itemsList[itemStack.itemID].getColorFromDamage(itemStack.getMetadata());
                float red = (float) (i >> 16 & 255) / 255.0F;
                float green = (float) (i >> 8 & 255) / 255.0F;
                float blue = (float) (i & 255) / 255.0F;
                GL11.glColor4f(f3 * red, f3 * green, f3 * blue, 1.0F);
                item.applyFirstPersonModelTransformations();
                item.render(itemStack, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
                ci.cancel();
            }
        }
    }
}
