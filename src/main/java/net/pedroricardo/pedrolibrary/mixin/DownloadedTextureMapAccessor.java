package net.pedroricardo.pedrolibrary.mixin;

import net.minecraft.src.DownloadedTexture;
import net.minecraft.src.RenderEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = RenderEngine.class, remap = false)
public interface DownloadedTextureMapAccessor {
    @Accessor("downloadedTextures")
    Map<String, DownloadedTexture> downloadedTextures();
}
