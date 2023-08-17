package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.ChunkPosition;

import java.util.Set;

/**
 * This interface can be implemented in block classes which need to get the exploder entity when a block is
 * destroyed by an explosion. Implementing the interface will call the method here instead of the original
 * {@link net.minecraft.core.block.Block#onBlockDestroyedByExplosion}.
 */
public interface IOnBlockDestroyedByExplosion {
    void onBlockDestroyedByExplosion(Entity exploder, World world, Block block, TileEntity blockEntity, double explosionX, double explosionY, double explosionZ, float explosionSize, Set<ChunkPosition> destroyedBlockPositions, int x, int y, int z);
}
