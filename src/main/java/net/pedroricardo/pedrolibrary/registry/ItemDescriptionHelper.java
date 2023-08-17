package net.pedroricardo.pedrolibrary.registry;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.item.Item;
import net.pedroricardo.pedrolibrary.interfaces.IDescriptionLambda;

import java.util.HashMap;
import java.util.Map;

public class ItemDescriptionHelper {
    protected static final Map<Item, IDescriptionLambda> items = new HashMap<>();

    public static void register(Item item, IDescriptionLambda description) {
        items.put(item, description);
    }

    public static ImmutableMap<Item, IDescriptionLambda> getItems() {
        return ImmutableMap.copyOf(items);
    }
}
