package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.slot.Slot;
import net.pedroricardo.pedrolibrary.registry.ItemDescriptionHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = GuiTooltip.class, remap = false)
public class CustomDescriptionMixin {
    @Mixin(value = GuiTooltip.class, remap = false)
    private interface MinecraftAccessor {
        @Accessor("mc")
        Minecraft mc();
    }

    @Inject(method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(C)Ljava/lang/StringBuilder;", ordinal = 2, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public void getTooltipText(ItemStack itemStack, boolean showDescription, Slot slot, CallbackInfoReturnable<String> cir, I18n trans, StringBuilder text) {
        if (ItemDescriptionHelper.getItems().containsKey(slot.getStack().getItem())) {
            text.append('\n').append(GuiTooltip.formatDescription(ItemDescriptionHelper.getItems().get(slot.getStack().getItem()).getDescription(((MinecraftAccessor)((GuiTooltip)(Object)this)).mc().thePlayer, itemStack, slot, trans), 16));
        } else {
            text.append('\n').append(GuiTooltip.formatDescription(trans.translateKey(slot.getStack().getItemName() + ".desc"), 16));
        }
    }

    @Redirect(method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(C)Ljava/lang/StringBuilder;", ordinal = 2))
    public StringBuilder append1(StringBuilder stringBuilder, char c) {
        return new StringBuilder("");
    }

    @Redirect(method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;", ordinal = 5))
    public StringBuilder append2(StringBuilder stringBuilder, String str) {
        return new StringBuilder("");
    }
}