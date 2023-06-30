package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public interface IOnBlockDestroyedByPlayer {
    void onBlockDestroyedByPlayer(EntityPlayer player, World world, Block block, TileEntity blockEntity, int x, int y, int z, int metadata);
}
