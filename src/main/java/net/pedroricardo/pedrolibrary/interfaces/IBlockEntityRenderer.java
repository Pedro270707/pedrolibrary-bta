package net.pedroricardo.pedrolibrary.interfaces;

import net.minecraft.src.ItemStack;

public interface IBlockEntityRenderer {
    default boolean renderWithCustomModel() {
        return true;
    }

    default boolean respect3DItemsOption() {
        return false;
    }

    int getTextureToRender(ItemStack itemStack);

    void render(ItemStack itemStack, float x, float y, float z, float scaleX, float scaleY, float scaleZ);

    default void applyFirstPersonModelTransformations() {
    }

    default void applyGuiModelTransformations() {
    }

    default void applyBipedModelTransformations() {
    }

    default void applyPlayerModelTransformations() {
        this.applyBipedModelTransformations();
    }

    default void applyDroppedItemTransformations() {
    }
}
