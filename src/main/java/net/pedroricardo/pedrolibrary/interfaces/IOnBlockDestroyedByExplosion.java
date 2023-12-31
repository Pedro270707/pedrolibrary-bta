package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.src.*;

import java.util.Set;

/**
 * This interface can be implemented in block classes which need to get the exploder entity when a block is
 * destroyed by an explosion. Implementing the interface will call the method here instead of the original
 * {@link net.minecraft.src.Block#onBlockDestroyedByExplosion}.
 */
public interface IOnBlockDestroyedByExplosion {
    void onBlockDestroyedByExplosion(Entity exploder, World world, Block block, TileEntity blockEntity, double explosionX, double explosionY, double explosionZ, float explosionSize, Set<ChunkPosition> destroyedBlockPositions, int x, int y, int z);
}
