package com.zzyyio.catcat.material;

import com.zzyyio.catcat.item.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class GuiditeMaterial implements ToolMaterial {

    public static final GuiditeMaterial guidite = new GuiditeMaterial();

    @Override
    public int getDurability() {
        return 100;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 100.0f;
    }

    @Override
    public float getAttackDamage() {
        return 1000000;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 100;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.test);
    }
}
