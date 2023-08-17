package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.FontRenderer;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.pedroricardo.pedrolibrary.ItemWithModelRenderer;
import net.pedroricardo.pedrolibrary.registry.ItemRendererRegistry;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemEntityRenderer.class, remap = false)
public class ItemIntoGUIMixin {
    private ItemStack itemStackToRender;

    @Inject(method = "drawItemIntoGui(Lnet/minecraft/client/render/FontRenderer;Lnet/minecraft/client/render/RenderEngine;IIIIIFF)V", at = @At("HEAD"), cancellable = true)
    public void drawItemIntoGui(FontRenderer fontRenderer, RenderEngine renderEngine, int itemID, int j, int k, int l, int i1, float brightness, float alpha, CallbackInfo ci) {
        float f1;
        float f3;
        if (ItemRendererRegistry.getItems().containsKey(Item.itemsList[itemID])) {
            ItemWithModelRenderer renderer = ItemRendererRegistry.getItems().get(Item.itemsList[itemID]);
            if (renderer.renderWithCustomModel() && (!renderer.respect3DItemsOption() || Minecraft.getMinecraft((ItemEntityRenderer)(Object)this).gameSettings.items3D.value)) {
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                if (this.itemStackToRender != null) {
                    renderEngine.bindTexture(renderer.getTextureToRender(Minecraft.getMinecraft((ItemEntityRenderer)(Object)this), this.itemStackToRender));
                } else {
                    ItemStack itemStack = new ItemStack(Item.itemsList[itemID]);
                    itemStack.setMetadata(j);
                    renderEngine.bindTexture(renderer.getTextureToRender(Minecraft.getMinecraft((ItemEntityRenderer)(Object)this), itemStack));
                }
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(l - 2), (float)(i1 + 3), -3.0F);
                GL11.glScalef(10.0F, 10.0F, 10.0F);
                GL11.glTranslatef(1.0F, 0.0F, 1.5F);
                GL11.glScalef(1.0F, -1.0F, 1.0F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-225.0F, 0.0F, 1.0F, 0.0F);
                int l1 = Item.itemsList[itemID].getColorFromDamage(j);
                f1 = (float)(l1 >> 16 & 255) / 255.0F;
                f3 = (float)(l1 >> 8 & 255) / 255.0F;
                float f5 = (float)(l1 & 255) / 255.0F;
                if (((ItemEntityRenderer)(Object)this).field_27004_a) {
                    GL11.glColor4f(f1 * brightness, f3 * brightness, f5 * brightness, alpha);
                } else {
                    GL11.glColor4f(brightness, brightness, brightness, alpha);
                }

                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                renderer.applyGuiModelTransformations();
                ItemStack itemStackToRender;
                if (this.itemStackToRender != null) {
                    itemStackToRender = this.itemStackToRender;
                } else {
                    itemStackToRender = new ItemStack(Item.itemsList[itemID]);
                    itemStackToRender.setMetadata(j);
                }
                renderer.render(itemStackToRender, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
                GL11.glDisable(3042);
                ci.cancel();
            }
        }
    }

    @Inject(method = "renderItemIntoGUI(Lnet/minecraft/client/render/FontRenderer;Lnet/minecraft/client/render/RenderEngine;Lnet/minecraft/core/item/ItemStack;IIF)V", at = @At("HEAD"))
    public void renderItemIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack itemStack, int i, int j, float alpha, CallbackInfo ci) {
        if (itemStack != null && ItemRendererRegistry.getItems().containsKey(itemStack.getItem())) {
            this.itemStackToRender = itemStack;
        }
    }

    @Inject(method = "renderItemIntoGUI(Lnet/minecraft/client/render/FontRenderer;Lnet/minecraft/client/render/RenderEngine;Lnet/minecraft/core/item/ItemStack;IIFF)V", at = @At("HEAD"))
    public void renderItemIntoGUI(FontRenderer fontRenderer, RenderEngine renderEngine, ItemStack itemStack, int i, int j, float brightness, float alpha, CallbackInfo ci) {
        if (itemStack != null && ItemRendererRegistry.getItems().containsKey(itemStack.getItem())) {
            this.itemStackToRender = itemStack;
        }
    }
}
