package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.client.render.DownloadedTexture;
import net.minecraft.client.render.RenderEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = RenderEngine.class, remap = false)
public interface DownloadedTextureMapAccessor {
    @Accessor("downloadedTextures")
    Map<String, DownloadedTexture> downloadedTextures();
}
