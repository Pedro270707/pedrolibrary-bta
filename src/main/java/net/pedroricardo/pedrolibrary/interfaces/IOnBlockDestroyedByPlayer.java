package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public interface IOnBlockDestroyedByPlayer {
    void onBlockDestroyedByPlayer(EntityPlayer player, World world, int x, int y, int z, int metadata);
}
