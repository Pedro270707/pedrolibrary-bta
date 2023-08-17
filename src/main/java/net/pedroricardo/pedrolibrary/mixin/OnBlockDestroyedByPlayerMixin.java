package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.controller.PlayerController;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
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
    public void destroyBlock(int x, int y, int z, Side side, EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        World world = ((PlayerControllerAccessors)((PlayerController)(Object)this)).mc().theWorld;
        Block block = Block.getBlock(world.getBlockId(x, y, z));
        TileEntity blockEntity = world.getBlockTileEntity(x, y, z);
        if (block instanceof IOnBlockDestroyedByPlayer) {
            ((PlayerControllerAccessors)((PlayerController)(Object)this)).invokeSetMineBlock((Integer)null, (Integer)null, (Integer)null);
            Item heldItem = null;
            ItemStack heldItemStack = player.getCurrentEquippedItem();
            if (heldItemStack != null) {
                heldItem = heldItemStack.getItem();
            }

            world.playSoundEffect(2001, x, y, z, block.id);
            int meta = world.getBlockMetadata(x, y, z);
            boolean removed = world.setBlockWithNotify(x, y, z, 0);
            if (removed) {
                ((IOnBlockDestroyedByPlayer)block).onBlockDestroyedByPlayer(world, block, x, y, z, meta, blockEntity, player, heldItem);
            }
            cir.setReturnValue(removed);
        }
    }
}