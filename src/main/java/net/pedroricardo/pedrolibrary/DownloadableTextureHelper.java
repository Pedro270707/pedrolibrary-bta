package net.pedroricardo.pedrolibrary;

import net.minecraft.src.DownloadedTexture;
import net.minecraft.src.ImageParser;
import net.minecraft.src.RenderEngine;
import net.pedroricardo.pedrolibrary.mixin.DownloadedTextureMapAccessor;

public interface DownloadableTextureHelper {

    default int getDownloadableTexture(String url, String localTexture, ImageParser imageParser) {
        if (url == null) {
            if (localTexture != null) {
                return ((RenderEngine)(Object)this).getTexture(localTexture);
            }
            return 0;
        }
        DownloadedTexture texture = ((DownloadedTextureMapAccessor)((RenderEngine)(Object)this)).downloadedTextures().get(url);
        if (texture == null) {
            texture = new DownloadedTexture(url, imageParser);
            ((DownloadedTextureMapAccessor)((RenderEngine)(Object)this)).downloadedTextures().put(url, texture);
        }
        if (texture.textureId < 0 && texture.image != null) {
            texture.textureId = ((RenderEngine)(Object)this).allocateAndSetupTexture(texture.image);
        }
        if (texture.textureId > 0) {
            return texture.textureId;
        }
        if (localTexture != null) {
            return ((RenderEngine)(Object)this).getTexture(localTexture);
        }
        return 0;
    }
}
