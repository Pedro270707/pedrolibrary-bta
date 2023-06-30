package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerController.class, remap = false)
public class OnBlockDestroyedByPlayerMixin {
    @Mixin(value = PlayerController.class, remap = false)
    private interface PlayerControllerAccessors {
        @Accessor("mc")
        Minecraft mc();

        @Invoker("setMineBlock")
        void invokeSetMineBlock(Integer x, Integer y, Integer z);
    }

    @Inject(method = "destroyBlock", at = @At("HEAD"), cancellable = true)
    public void destroyBlock(int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        World world = ((PlayerControllerAccessors)((PlayerController)(Object)this)).mc().theWorld;
        Block block = Block.getBlock(world.getBlockId(x, y, z));
        TileEntity blockEntity = world.getBlockTileEntity(x, y, z);
        if (block instanceof IOnBlockDestroyedByPlayer) {
            ((PlayerControllerAccessors)((PlayerController)(Object)this)).invokeSetMineBlock((Integer)null, (Integer)null, (Integer)null);
            world.playSoundEffect(2001, x, y, z, block.blockID);
            int meta = world.getBlockMetadata(x, y, z);
            boolean removed = world.setBlockWithNotify(x, y, z, 0);
            if (removed) {
                ((IOnBlockDestroyedByPlayer)block).onBlockDestroyedByPlayer(((PlayerControllerAccessors)((PlayerController)(Object)this)).mc().thePlayer, world, block, blockEntity, x, y, z, meta);
            }
            cir.setReturnValue(removed);
        }
    }
}