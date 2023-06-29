package net.pedroricardo.pedrolibrary;

import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByPlayer;

public class TestBlock extends Block implements IOnBlockDestroyedByPlayer {
    public TestBlock(int i, Material material) {
        super(i, material);
    }

    @Override
    public void onBlockDestroyedByPlayer(EntityPlayer player, World world, int x, int y, int z, int metadata) {
        if (player.getGamemode().dropBlockOnBreak) {
            world.dropItem(x, y, z, new ItemStack(Item.diamond));
        }
    }
}
