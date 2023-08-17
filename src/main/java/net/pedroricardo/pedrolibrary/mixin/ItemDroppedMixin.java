package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.core.entity.EntityItem;
import net.pedroricardo.pedrolibrary.registry.ItemRendererRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemEntityRenderer.class, remap = false)
public class ItemDroppedMixin {
    @Inject(method = "doRenderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/ItemRenderer;renderItem(Lnet/minecraft/core/entity/Entity;Lnet/minecraft/core/item/ItemStack;Z)V"))
    public void doRenderItem(EntityItem entityitem, double d, double d1, double d2, float f, float f1, CallbackInfo ci) {
        if (ItemRendererRegistry.getItems().containsKey(entityitem.item.getItem())) {
            ItemRendererRegistry.getItems().get(entityitem.item.getItem()).applyDroppedItemTransformations();
        }
    }
}
