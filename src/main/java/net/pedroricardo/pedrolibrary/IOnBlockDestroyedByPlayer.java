package net.pedroricardo.pedrolibrary;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public interface IOnBlockDestroyedByPlayer {
    void onBlockDestroyedByPlayerBetter(EntityPlayer player, World world, int x, int y, int z, int metadata);
}
