package com.zzyyio.catcat.block;

import com.zzyyio.catcat.CatCat;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block CONDENSED_DIRT = register(
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS).hardness(2.0f).requiresTool()),
            "condensed_dirt",
            true
    );
    public static final Block CONDENSED_OAK_LOG = register(
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD)),
            "condensed_oak_log",
            true
    );

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        // Register the block and its item.
        Identifier id = new Identifier(CatCat.MOD_ID,name);
        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize(){

    }
}
