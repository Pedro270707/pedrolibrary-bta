package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IItemWithModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderPlayer.class, remap = false)
public class ItemInPlayerMixin {
    @Inject(method = "renderSpecials", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/ModelRenderer;postRender(F)V", ordinal = 1, shift = At.Shift.AFTER))
    public void renderSpecials(EntityPlayer entityplayer, float f, CallbackInfo ci) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (itemstack.getItem() instanceof IItemWithModelRenderer) {
            ((IItemWithModelRenderer)itemstack.getItem()).getRenderer().applyPlayerModelTransformations();
        }
    }
}
