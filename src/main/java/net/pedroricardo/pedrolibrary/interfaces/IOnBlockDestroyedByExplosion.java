package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.src.ChunkPosition;
import net.minecraft.src.Entity;
import net.minecraft.src.World;

import java.util.Set;

public interface IOnBlockDestroyedByExplosion {
    void onBlockDestroyedByExplosion(Entity exploder, World world, double explosionX, double explosionY, double explosionZ, float explosionSize, Set<ChunkPosition> destroyedBlockPositions, int x, int y, int z);
}
