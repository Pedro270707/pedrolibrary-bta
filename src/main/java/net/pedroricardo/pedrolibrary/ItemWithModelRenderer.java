package net.pedroricardo.pedrolibrary;

import net.minecraft.src.ItemStack;

public abstract class ItemWithModelRenderer {
    public boolean renderWithCustomModel() {
        return true;
    }

    public boolean respect3DItemsOption() {
        return false;
    }

    public abstract int getTextureToRender(ItemStack itemStack);

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
