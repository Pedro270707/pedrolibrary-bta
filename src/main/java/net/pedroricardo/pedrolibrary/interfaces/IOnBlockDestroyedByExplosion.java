package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.src.*;

import java.util.Set;

public interface IOnBlockDestroyedByExplosion {
    void onBlockDestroyedByExplosion(Entity exploder, World world, Block block, TileEntity blockEntity, double explosionX, double explosionY, double explosionZ, float explosionSize, Set<ChunkPosition> destroyedBlockPositions, int x, int y, int z);
}
