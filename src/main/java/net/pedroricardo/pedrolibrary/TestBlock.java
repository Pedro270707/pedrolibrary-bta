package net.pedroricardo.pedrolibrary;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class TestBlock extends Block implements IOnBlockDestroyedByPlayer {
    public TestBlock(int i, Material material) {
        super(i, material);
    }

    @Override
    public void onBlockDestroyedByPlayerBetter(EntityPlayer player, World world, int x, int y, int z, int metadata) {
        player.addChatMessage("Hello, " + player.getDisplayName());
    }
}
