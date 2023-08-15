package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * This interface can be implemented in block classes which need to get the player when a block is
 * destroyed by a player. Implementing the interface will call the method here instead of the original
 * {@link net.minecraft.src.Block#onBlockDestroyedByPlayer}.
 */
public interface IOnBlockDestroyedByPlayer {
    void onBlockDestroyedByPlayer(EntityPlayer player, World world, Block block, TileEntity blockEntity, int x, int y, int z, int metadata);
}
