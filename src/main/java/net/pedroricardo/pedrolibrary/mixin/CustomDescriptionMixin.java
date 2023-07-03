package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.ICustomDescription;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.minecraft.src.GuiContainer.formatDescription;

@Mixin(value = GuiContainer.class, remap = false)
public class CustomDescriptionMixin {
    @Mixin(value = GuiScreen.class, remap = false)
    private interface MinecraftAccessor {
        @Accessor("mc")
        Minecraft mc();
    }

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(C)Ljava/lang/StringBuilder;", ordinal = 2, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public void drawScreen(int x, int y, float renderPartialTicks, CallbackInfo ci, int centerX, int centerY, Slot slot,
                           InventoryPlayer inventoryplayer, StringTranslate trans, StringBuilder text,
                           boolean multiLine, boolean control, boolean shift, boolean showDescription,
                           boolean isCrafting, String itemName, String itemNick, boolean debug) {
        if (slot.getStack().getItem() instanceof ICustomDescription) {
            text.append("\n").append(formatDescription(((ICustomDescription) slot.getStack().getItem()).getDescription(((MinecraftAccessor)((GuiContainer)(Object)this)).mc().thePlayer, slot), 16));
        } else {
            text.append('\n').append(formatDescription(trans.translateKey(slot.getStack().getItemName() + ".desc"), 16));
        }
    }

    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(C)Ljava/lang/StringBuilder;", ordinal = 2))
    public StringBuilder append1(StringBuilder stringBuilder, char c) {
        return new StringBuilder("");
    }

    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;", ordinal = 4))
    public StringBuilder append2(StringBuilder stringBuilder, String str) {
        return new StringBuilder("");
    }
}