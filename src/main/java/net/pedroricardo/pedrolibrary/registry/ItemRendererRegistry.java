package net.pedroricardo.pedrolibrary.registry;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.item.Item;
import net.pedroricardo.pedrolibrary.ItemWithModelRenderer;

import java.util.HashMap;
import java.util.Map;

public class ItemRendererRegistry {
    private static final Map<Item, ItemWithModelRenderer> items = new HashMap<>();

    public static void register(Item item, ItemWithModelRenderer renderer) {
        items.put(item, renderer);
    }

    public static ImmutableMap<Item, ItemWithModelRenderer> getItems() {
        return ImmutableMap.copyOf(items);
    }
}
