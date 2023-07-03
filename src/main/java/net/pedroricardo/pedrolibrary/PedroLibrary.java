package net.pedroricardo.pedrolibrary;

import net.fabricmc.api.ModInitializer;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockHelper;


public class PedroLibrary implements ModInitializer {
    public static final String MOD_ID = "pedrolibrary";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Block TEST_BLOCK = BlockHelper.createBlock(MOD_ID, new TestBlock(900, Material.rock), "testBlock", "test_block.png", Block.soundStoneFootstep, 1.0F, 1.0F, 0.0F);

    @Override
    public void onInitialize() {
        LOGGER.info("PedroLibrary initialized.");
    }
}
