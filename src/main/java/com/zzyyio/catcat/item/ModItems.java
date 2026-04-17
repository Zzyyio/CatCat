package com.zzyyio.catcat.item;

import com.zzyyio.catcat.CatCat;
import com.zzyyio.catcat.material.GuiditeMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class ModItems {
    public static final Item test = registerItem("test", new TestItem(new Item.Settings()));
    public static final Item guidite_sword = registerItem("guidite_sword",new SwordItem(GuiditeMaterial.guidite, 200, 0.5F, new FabricItemSettings()));
    public static final Item guidite_pickaxe = registerItem("guidite_pickaxe",new PickaxeItem(GuiditeMaterial.guidite,200,0.1f,new FabricItemSettings()));
    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(itemGroup -> itemGroup.add(ModItems.test));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(itemGroup -> itemGroup.add(ModItems.guidite_sword));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(itemGroup -> itemGroup.add(ModItems.guidite_pickaxe));

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
