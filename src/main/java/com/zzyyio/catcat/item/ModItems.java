package com.zzyyio.catcat.item;

import com.zzyyio.catcat.CatCat;
import com.zzyyio.catcat.block.ModBlocks;
import com.zzyyio.catcat.material.GuiditeArmorMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class ModItems {
    public static final Item test = registerItem("test", new TestItem(new Item.Settings()));
    public static final Item guidite_sword = registerItem("guidite_sword",new GuiditeSwordItem(new FabricItemSettings()));
    public static final Item GUIDITE_HELMET = registerItem("guidite_helmet",new ArmorItem(GuiditeArmorMaterial.guidite, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item GUIDITE_BOOTS = registerItem("guidite_boots",new ArmorItem(GuiditeArmorMaterial.guidite, ArmorItem.Type.BOOTS, new Item.Settings()));
    public static final Item GUIDITE_LEGGINGS = registerItem("guidite_leggings",new ArmorItem(GuiditeArmorMaterial.guidite, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item GUIDITE_CHESTPLATE = registerItem("guidite_chestplate",new ArmorItem(GuiditeArmorMaterial.guidite, ArmorItem.Type.CHESTPLATE, new Item.Settings()));


    public static final RegistryKey<ItemGroup> CUSTOM_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("custom_group"));
    public static final ItemGroup CUSTOM_GROUP_ENTRY = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.guidite_sword))
            .displayName(Text.translatable("itemgroup.catcat"))
            .entries(((displayContext, entries) ->{
                entries.add(test);
                entries.add(guidite_sword);
                entries.add(GUIDITE_HELMET);
                entries.add(GUIDITE_CHESTPLATE);
                entries.add(GUIDITE_LEGGINGS);
                entries.add(GUIDITE_BOOTS);
                    }))
            .build();
    public static final ItemGroup CUSTOM_GROUP =registerItemGroup(Registries.ITEM_GROUP, CUSTOM_GROUP_KEY, CUSTOM_GROUP_ENTRY);

    public static void initialize() {




        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(itemGroup -> itemGroup.add(ModItems.test));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(itemGroup -> itemGroup.add(ModItems.guidite_sword));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(itemGroup -> itemGroup.add(ModItems.GUIDITE_BOOTS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(itemGroup -> itemGroup.add(ModItems.GUIDITE_LEGGINGS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(itemGroup -> itemGroup.add(ModItems.GUIDITE_CHESTPLATE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(itemGroup -> itemGroup.add(ModItems.GUIDITE_HELMET));
        ItemGroupEvents.modifyEntriesEvent(ModItems.CUSTOM_GROUP_KEY)
                .register((itemGroup) -> {
                    itemGroup.add(ModBlocks.CONDENSED_DIRT.asItem());
                    itemGroup.add(ModBlocks.CONDENSED_OAK_LOG.asItem());
                });
    }


    public static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM,
                new Identifier(CatCat.MOD_ID, id),
                item);
    }
    public static ItemGroup registerItemGroup(Registry<ItemGroup> registry,RegistryKey<ItemGroup> key, ItemGroup group){
        return Registry.register(registry,key,group);
    }
}
