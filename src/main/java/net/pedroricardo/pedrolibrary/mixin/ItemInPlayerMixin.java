package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.pedroricardo.pedrolibrary.registry.ItemRendererRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerRenderer.class, remap = false)
public class ItemInPlayerMixin {
    @Inject(method = "renderSpecials", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/Cube;postRender(F)V", ordinal = 1, shift = At.Shift.AFTER))
    public void renderSpecials(EntityPlayer entityplayer, float f, CallbackInfo ci) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (ItemRendererRegistry.getItems().containsKey(itemstack.getItem())) {
            ItemRendererRegistry.getItems().get(itemstack.getItem()).applyPlayerModelTransformations();
        }
    }
}
