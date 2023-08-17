package net.pedroricardo.pedrolibrary;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.item.Item;
import net.pedroricardo.pedrolibrary.registry.ItemDescriptionHelper;
import net.pedroricardo.pedrolibrary.registry.ItemRendererRegistry;
import net.pedroricardo.pedrolibrary.registry.impl.ItemRendererImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;


public class PedroLibrary implements ModInitializer {
    public static final String MOD_ID = "pedrolibrary";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
//    public static final Block TEST_BLOCK = new BlockBuilder(MOD_ID).setTextures("test_block.png").setBlockSound(BlockSounds.STONE).setHardness(1.0f).setResistance(1.0f).build(new TestBlock("test_block", Block.highestBlockId + 1, Material.stone));

    @Override
    public void onInitialize() {
        /*
        ItemDescriptionHelper.register(TEST_BLOCK.asItem(),
                (player, stack, slot, translate) -> slot.id % 2 == 0 ?
                        translate.translateKey("tile.pedrolibrary.test_block.desc1") :
                        translate.translateKey("tile.pedrolibrary.test_block.desc2"));
        ItemRendererRegistry.register(Item.diamond, new ItemRendererImpl());
        */
        LOGGER.info("PedroLibrary initialized.");
    }
}
