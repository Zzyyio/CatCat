package com.zzyyio.catcat.potion;

import com.zzyyio.catcat.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final Potion TATER_POTION =
            Registry.register(
                    Registries.POTION,
                    new Identifier("catcat","tater_potion"),
                    new Potion(
                            "tater_potion",
                            new StatusEffectInstance(ModEffects.TATER_EFFECT, 9600)
                    )
            );
    public static void initialize(){
        //药水在注册时会自动添加到食物与饮品栏
        BrewingRecipeRegistry.registerPotionRecipe(Potions.WATER, Items.POTATO, TATER_POTION);

    }
}
