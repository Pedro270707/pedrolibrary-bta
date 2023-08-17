package net.pedroricardo.pedrolibrary;

import net.minecraft.client.Minecraft;
import net.minecraft.core.item.ItemStack;

public abstract class ItemWithModelRenderer {
    public boolean renderWithCustomModel() {
        return true;
    }

    public boolean respect3DItemsOption() {
        return false;
    }

    public abstract int getTextureToRender(Minecraft mc, ItemStack itemStack);

    public abstract void render(ItemStack itemStack, float x, float y, float z, float scaleX, float scaleY, float scaleZ);

    public void applyFirstPersonModelTransformations() {
    }

    public void applyGuiModelTransformations() {
    }

    public void applyBipedModelTransformations() {
    }

    public void applyPlayerModelTransformations() {
        this.applyBipedModelTransformations();
    }

    public void applyDroppedItemTransformations() {
    }
}
