package com.zzyyio.catcat;

import com.zzyyio.catcat.block.ModBlocks;
import com.zzyyio.catcat.effect.ModEffects;
import com.zzyyio.catcat.init.ModProperties;
import com.zzyyio.catcat.item.ModItems;
import com.zzyyio.catcat.potion.ModPotions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.EffectCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatCat implements ModInitializer {
	public static final String MOD_ID = "catcat";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

        ModItems.initialize();
        ModBlocks.initialize();
        ModEffects.initialize();
        ModPotions.initialize();
        ModProperties.register();
		LOGGER.info("Hello Fabric world!");
	}
}