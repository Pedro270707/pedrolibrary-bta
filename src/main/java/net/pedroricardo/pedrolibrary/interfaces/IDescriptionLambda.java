package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.slot.Slot;

public interface IDescriptionLambda {
    String getDescription(EntityPlayer entityPlayer, ItemStack itemStack, Slot slot, I18n translate);
}
