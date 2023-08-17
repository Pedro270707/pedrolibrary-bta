package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.world.Explosion;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.ChunkPosition;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = Explosion.class, remap = false)
public class OnBlockDestroyedByExplosionMixin {
    @Shadow protected World worldObj;

    @Mixin(value = Explosion.class, remap = false)
    private interface ExplosionAccessor {
        @Accessor("worldObj")
        World worldObj();
    }

    @Inject(method = "doExplosionB", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/block/Block;onBlockDestroyedByExplosion(Lnet/minecraft/core/world/World;III)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void doExplosionB(boolean particles, CallbackInfo ci, List<ChunkPosition> list, int i, ChunkPosition chunkposition, int j, int k, int l, int id) {
        Explosion thisObj = ((Explosion)(Object)this);
        TileEntity blockEntity = worldObj.getBlockTileEntity(j, k, l);
        worldObj.setBlockWithNotify(j, k, l, 0);
        if (Block.blocksList[id] instanceof IOnBlockDestroyedByExplosion) {
            ((IOnBlockDestroyedByExplosion)Block.blocksList[id]).onBlockDestroyedByExplosion(thisObj.exploder, ((ExplosionAccessor)thisObj).worldObj(),
                    Block.blocksList[id], blockEntity, thisObj.explosionX, thisObj.explosionY, thisObj.explosionZ, thisObj.explosionSize, thisObj.destroyedBlockPositions, j, k, l);
        } else {
            Block.blocksList[id].onBlockDestroyedByExplosion(((ExplosionAccessor)thisObj).worldObj(), j, k, l);
        }
    }

    @Redirect(method = "doExplosionB", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/block/Block;onBlockDestroyedByExplosion(Lnet/minecraft/core/world/World;III)V"))
    public void doExplosionB(Block block, World world, int j, int k, int l) {
    }
    @Redirect(method = "doExplosionB", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockWithNotify(IIII)Z"))
    public boolean doExplosionB(World world, int x, int y, int z, int id) {
        return false;
    }

}