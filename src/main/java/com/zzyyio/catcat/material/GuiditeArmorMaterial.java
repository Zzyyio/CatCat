package com.zzyyio.catcat.material;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class GuiditeArmorMaterial implements ArmorMaterial {
    public static final GuiditeArmorMaterial guidite = new GuiditeArmorMaterial();
    @Override
    public int getDurability(ArmorItem.Type type) {
        int DURABILITY_MULTIPLIER = 120;
        return switch (type) {
            case BOOTS -> 13 * DURABILITY_MULTIPLIER;
            case LEGGINGS -> 15 * DURABILITY_MULTIPLIER;
            case CHESTPLATE -> 16 * DURABILITY_MULTIPLIER;
            case HELMET -> 11 * DURABILITY_MULTIPLIER;
            default -> 0;
        };
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        // Protection values for all the slots.
        // For reference, diamond uses 3 for boots, 6 for leggings, 8 for chestplate, and 3 for helmet,
        // whilst leather uses 1, 2, 3 and 1 respectively.
        return switch (type) {
            case BOOTS, HELMET -> 30;
            case LEGGINGS -> 60;
            case CHESTPLATE -> 80;
            default -> 0;
        };
    }

    @Override
    public int getEnchantability() {
        return 100;
    }

    @Override
    public SoundEvent getEquipSound() {
        // Example for Iron Armor
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "catcat:guidite";
    }

    @Override
    public float getToughness() {
        return 5.0f;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.9f;
    }
}
