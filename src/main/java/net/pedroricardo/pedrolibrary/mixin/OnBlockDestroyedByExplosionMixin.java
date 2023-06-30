package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.Block;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.Explosion;
import net.minecraft.src.World;
import net.pedroricardo.pedrolibrary.interfaces.IOnBlockDestroyedByExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = Explosion.class, remap = false)
public class OnBlockDestroyedByExplosionMixin {
    @Mixin(value = Explosion.class, remap = false)
    private interface ExplosionAccessor {
        @Accessor("worldObj")
        World worldObj();
    }

    @Inject(method = "doExplosionB", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Block;onBlockDestroyedByExplosion(Lnet/minecraft/src/World;III)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void doExplosionB(boolean particles, CallbackInfo ci, List<ChunkPosition> list, int i, ChunkPosition chunkposition, int j, int k, int l, int id) {
        Explosion thisObj = ((Explosion)(Object)this);
        if (Block.blocksList[id] instanceof IOnBlockDestroyedByExplosion) {
            ((IOnBlockDestroyedByExplosion)Block.blocksList[id]).onBlockDestroyedByExplosion(thisObj.exploder, ((ExplosionAccessor)thisObj).worldObj(),
                    thisObj.explosionX, thisObj.explosionY, thisObj.explosionZ, thisObj.explosionSize, thisObj.destroyedBlockPositions, j, k, l);
        } else {
            Block.blocksList[id].onBlockDestroyedByExplosion(((ExplosionAccessor)thisObj).worldObj(), j, k, l);
        }
    }

    @Redirect(method = "doExplosionB", at = @At(value = "INVOKE", target = "net/minecraft/src/Block.onBlockDestroyedByExplosion(Lnet/minecraft/src/World;III)V"))
    public void doExplosionB(Block block, World world, int j, int k, int l) {
    }
}