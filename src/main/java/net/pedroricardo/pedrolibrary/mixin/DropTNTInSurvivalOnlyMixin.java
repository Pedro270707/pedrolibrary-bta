package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockTNT.class)
public class DropTNTInSurvivalOnlyMixin implements IOnBlockDestroyedByPlayer {
    @Override
    public void onBlockDestroyedByPlayer(EntityPlayer player, World world, int x, int y, int z, int metadata) {
        if (player.getGamemode().dropBlockOnBreak) {
            world.dropItem(x, y, z, new ItemStack(Block.tnt));
        }
    }
}
