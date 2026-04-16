package com.zzyyio.catcat.item;

import com.zzyyio.catcat.CatCat;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.gui.tab.Tab;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class ModItems {
    public static final Item test = registerItem("test", new Item(new Item.Settings()));


    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(itemGroup -> itemGroup.add(ModItems.test));

    }

    public static void setAttributes(){
        // Add the suspicious substance to the composting registry with a 30% chance of increasing the composter's level.
        CompostingChanceRegistry.INSTANCE.add(ModItems.test, 0.0f);
        // Add the suspicious substance to the flammable block registry with a burn time of 30 seconds.
        // Remember, Minecraft deals with logical based-time using ticks.
        // 20 ticks = 1 second.
        FuelRegistry.INSTANCE.add(ModItems.test, 100 * 20);
    }

    public static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM,
                new Identifier(CatCat.MOD_ID, id),
                item);
//    }
//    public static Item register(String id, Item item) {
//        return register(new Identifier(CatCat.MOD_ID,id), item);
//    }
//
//    public static Item register(Identifier id, Item item) {
//        return register(RegistryKey.of(Registries.ITEM.getKey(), id), item);
//    }
//
//    public static Item register(RegistryKey<Item> key, Item item) {
//        if (item instanceof BlockItem) {
//            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
//        }
//
//        return Registry.register(Registries.ITEM, key, item);
//    }

    }
}
