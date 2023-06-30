package net.pedroricardo.pedrolibrary;

import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByExplosion;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByPlayer;

import java.util.Set;

public class TestBlock extends Block implements IOnBlockDestroyedByPlayer, IOnBlockDestroyedByExplosion {
    public TestBlock(int i, Material material) {
        super(i, material);
    }

    @Override
    public void onBlockDestroyedByPlayer(EntityPlayer player, World world, Block block, TileEntity blockEntity, int x, int y, int z, int metadata) {
        if (player.getGamemode().dropBlockOnBreak) {
            world.dropItem(x, y, z, new ItemStack(Item.diamond));
        }
    }

    @Override
    public void onBlockDestroyedByExplosion(Entity exploder, World world, Block block, TileEntity blockEntity,
                                            double explosionX, double explosionY,
                                            double explosionZ, float explosionSize,
                                            Set<ChunkPosition> destroyedBlockPositions, int x, int y, int z) {
        if (exploder instanceof EntityCreeper) {
            world.dropItem(x, y, z, new ItemStack(Block.tnt));
        }
    }
}
