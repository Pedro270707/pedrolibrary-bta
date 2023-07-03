package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public interface ICustomDescription {
    String getDescription(EntityPlayer entityPlayer, Slot slot);
}
