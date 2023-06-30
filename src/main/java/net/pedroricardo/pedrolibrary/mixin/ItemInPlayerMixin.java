package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderPlayer.class, remap = false)
public class ItemInPlayerMixin {
    @Inject(method = "renderSpecials", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/InventoryPlayer;getCurrentItem()Lnet/minecraft/src/ItemStack;"))
    public void renderSpecials(EntityPlayer entityplayer, float f, CallbackInfo ci) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() instanceof IBlockEntityRenderer) {
            ((IBlockEntityRenderer)itemstack.getItem()).applyPlayerModelTransformations();
        }
    }
}
