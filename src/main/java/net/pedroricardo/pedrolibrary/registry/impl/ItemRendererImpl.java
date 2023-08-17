package net.pedroricardo.pedrolibrary.registry.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Lighting;
import net.minecraft.client.render.model.ModelSign;
import net.minecraft.client.render.model.ModelSkeleton;
import net.minecraft.core.item.ItemStack;
import net.pedroricardo.pedrolibrary.ItemWithModelRenderer;

public class ItemRendererImpl extends ItemWithModelRenderer {
    private final ModelSkeleton skeletonModel = new ModelSkeleton();
    private final ModelSign signModel = new ModelSign();

    @Override
    public int getTextureToRender(Minecraft mc, ItemStack itemStack) {
        return itemStack.stackSize % 2 == 0 ? mc.renderEngine.getTexture("/mob/skeleton.png") : mc.renderEngine.getTexture("/item/sign.png");
    }

    @Override
    public void render(ItemStack itemStack, float x, float y, float z, float scaleX, float scaleY, float scaleZ) {
        if (itemStack.stackSize % 2 == 0) skeletonModel.render(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f);
        else signModel.func_887_a();
    }
}
