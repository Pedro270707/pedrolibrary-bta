package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.*;
import net.pedroricardo.headed.Headed;
import net.pedroricardo.pedrolibrary.interfaces.IBlockEntityRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderPlayer.class, remap = false)
public class ItemInPlayerMixin {
    @Inject(method = "renderSpecials", at = @At(value = "INVOKE", target = "net/minecraft/src/InventoryPlayer.getCurrentItem"))
    public void renderSpecials(EntityPlayer entityplayer, float f, CallbackInfo ci) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() instanceof IBlockEntityRenderer) {
            ((IBlockEntityRenderer)itemstack.getItem()).applyPlayerModelTransformations();
        }
    }
}
