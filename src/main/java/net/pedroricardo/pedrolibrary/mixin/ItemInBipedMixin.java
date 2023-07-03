package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IItemWithModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = RenderBiped.class, remap = false)
public class ItemInBipedMixin {
    @Inject(method = "renderEquippedItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ModelRenderer;postRender(F)V", ordinal = 0, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public void renderEquippedItems(EntityLiving entityliving, float f, CallbackInfo ci, ItemStack itemStack) {
        if (itemStack.getItem() instanceof IItemWithModelRenderer) {
            ((IItemWithModelRenderer)itemStack.getItem()).getRenderer().applyBipedModelTransformations();
        }
    }
}
