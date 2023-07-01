package net.pedroricardo.pedrolibrary;

import net.minecraft.client.Minecraft;
import net.minecraft.src.DownloadedTexture;
import net.minecraft.src.ImageParser;
import net.pedroricardo.pedrolibrary.mixin.DownloadedTextureMapAccessor;

public class DownloadableTextureHelper {
    public DownloadableTextureHelper() {
    }

    public int getDownloadableTexture(String url, String localTexture, ImageParser imageParser) {
        if (url == null) {
            if (localTexture != null) {
                return Minecraft.getMinecraft().renderEngine.getTexture(localTexture);
            }
            return 0;
        }
        DownloadedTexture texture = ((DownloadedTextureMapAccessor) Minecraft.getMinecraft().renderEngine).downloadedTextures().get(url);
        if (texture == null) {
            texture = new DownloadedTexture(url, imageParser);
            ((DownloadedTextureMapAccessor) Minecraft.getMinecraft().renderEngine).downloadedTextures().put(url, texture);
        }
        if (texture.textureId < 0 && texture.image != null) {
            texture.textureId = Minecraft.getMinecraft().renderEngine.allocateAndSetupTexture(texture.image);
        }
        if (texture.textureId > 0) {
            return texture.textureId;
        }
        if (localTexture != null) {
            return Minecraft.getMinecraft().renderEngine.getTexture(localTexture);
        }
        return 0;
    }
}
