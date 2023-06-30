package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.PedroLibrary;
import net.pedroricardo.pedrolibrary.interfaces.IBlockEntityRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderItem.class, remap = false)
public class ItemIntoGUIMixin {
    private ItemStack itemStackToRender;

    @Inject(method = "drawItemIntoGui(Lnet/minecraft/src/FontRenderer;Lnet/minecraft/src/RenderEngine;IIIIIFF)V", at = @At("HEAD"), cancellable = true)
    public void drawItemIntoGui(FontRenderer fontRenderer, RenderEngine renderEngine, int itemID, int j, int k, int l, int i1, float brightness, float alpha, CallbackInfo ci) {
        float f1;
        float f3;
        if (Item.itemsList[itemID] instanceof IBlockEntityRenderer) {
            IBlockEntityRenderer item = (IBlockEntityRenderer) Item.itemsList[itemID];
            if (item.renderWithCustomModel() && (!item.respect3DItemsOption() || Minecraft.getMinecraft().gameSettings.items3D.value)) {
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                if (this.itemStackToRender != null) {
                    renderEngine.bindTexture(item.getTextureToRender(this.itemStackToRender));
                } else {
                    ItemStack itemStack = new ItemStack(Item.itemsList[itemID]);
                    itemStack.setMetadata(j);
                    renderEngine.bindTexture(item.getTextureToRender(itemStack));
                }
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(l - 2), (float)(i1 + 3), -3.0F);
                GL11.glScalef(10.0F, 10.0F, 10.0F);
                GL11.glTranslatef(1.0F, 0.0F, 1.5F);
                GL11.glScalef(1.0F, -1.0F, 1.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                int l1 = Item.itemsList[itemID].getColorFromDamage(j);
                f1 = (float)(l1 >> 16 & 255) / 255.0F;
                f3 = (float)(l1 >> 8 & 255) / 255.0F;
                float f5 = (float)(l1 & 255) / 255.0F;
                if (((RenderItem)(Object)this).field_27004_a) {
                    GL11.glColor4f(f1 * brightness, f3 * brightness, f5 * brightness, alpha);
                } else {
                    GL11.glColor4f(brightness, brightness, brightness, alpha);
                }

                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                item.applyGuiModelTransformations();
                ItemStack itemStackToRender;
                if (this.itemStackToRender != null) {
                    itemStackToRender = this.itemStackToRender;
                } else {
                    itemStackToRender = new ItemStack(Item.itemsList[itemID]);
                    itemStackToRender.setMetadata(j);
                }
                item.render(itemStackToRender, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
                GL11.glDisable(3042);
                ci.cancel();
            }
        }
    }

    @Inject(method = "renderItemIntoGUI(Lnet/minecraft/src/FontRenderer;Lnet/minecraft/src/RenderEngine;Lnet/minecraft/src/ItemStack;IIF)V", at = @At("HEAD"))
    public void renderItemIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack itemStack, int i, int j, float alpha, CallbackInfo ci) {
        if (itemStack != null && itemStack.getItem() instanceof IBlockEntityRenderer) {
            this.itemStackToRender = itemStack;
        }
    }

    @Inject(method = "renderItemIntoGUI(Lnet/minecraft/src/FontRenderer;Lnet/minecraft/src/RenderEngine;Lnet/minecraft/src/ItemStack;IIFF)V", at = @At("HEAD"))
    public void renderItemIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack itemStack, int i, int j, float brightness, float alpha, CallbackInfo ci) {
        if (itemStack != null && itemStack.getItem() instanceof IBlockEntityRenderer) {
            this.itemStackToRender = itemStack;
        }
    }
}
