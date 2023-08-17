package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.World;

/**
 * This interface can be implemented in block classes which need to get the player when a block is
 * destroyed by a player. Implementing the interface will call the method here instead of the original
 * {@link net.minecraft.core.block.Block#onBlockDestroyedByPlayer}.
 */
public interface IOnBlockDestroyedByPlayer {
    void onBlockDestroyedByPlayer(World world, Block block, int x, int y, int z, int metadata, TileEntity blockEntity, EntityPlayer player, Item item);
}
