package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.EntityItem;
import net.minecraft.src.RenderItem;
import net.pedroricardo.pedrolibrary.interfaces.IBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderItem.class, remap = false)
public class ItemDroppedMixin {
    @Inject(method = "doRenderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ItemRenderer;renderItem(Lnet/minecraft/src/Entity;Lnet/minecraft/src/ItemStack;Z)V"))
    public void doRenderItem(EntityItem entityitem, double d, double d1, double d2, float f, float f1, CallbackInfo ci) {
        if (entityitem.item.getItem() instanceof IBlockEntityRenderer) {
            ((IBlockEntityRenderer)entityitem.item.getItem()).applyDroppedItemTransformations();
        }
    }
}
