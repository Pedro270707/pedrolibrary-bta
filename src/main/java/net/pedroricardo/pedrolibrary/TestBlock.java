package net.pedroricardo.pedrolibrary;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.EntityCreeper;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.ChunkPosition;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByExplosion;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByPlayer;

import java.util.Set;

public class TestBlock extends Block implements IOnBlockDestroyedByPlayer, IOnBlockDestroyedByExplosion {
    public TestBlock(String stringID, int i, Material material) {
        super(stringID, i, material);
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, Block block, int x, int y, int z, int metadata, TileEntity blockEntity, EntityPlayer player, Item item) {
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
