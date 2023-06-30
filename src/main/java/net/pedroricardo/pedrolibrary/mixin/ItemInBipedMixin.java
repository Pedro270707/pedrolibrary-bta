package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderBiped.class, remap = false)
public class ItemInBipedMixin {

    @Mixin(value = RenderBiped.class, remap = false)
    private interface RenderBipedAccessor {
        @Accessor("modelBipedMain")
        ModelBiped modelBipedMain();
    }

    @Mixin(value = Render.class, remap = false)
    private interface RenderAccessor {
        @Accessor("renderManager")
        RenderManager renderManager();
    }



    @Inject(method = "renderEquippedItems", at = @At(value = "INVOKE", target = "org/lwjgl/opengl/GL11.glPushMatrix()V", ordinal = 0, shift = At.Shift.AFTER))
    public void renderEquippedItems(EntityLiving entityliving, float f, CallbackInfo ci) {
        ItemStack itemstack = entityliving.getHeldItem();
        if (itemstack.getItem() instanceof IBlockEntityRenderer) {
            ((IBlockEntityRenderer)itemstack.getItem()).applyBipedModelTransformations();
        }
    }
}
