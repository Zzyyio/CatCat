package com.zzyyio.catcat.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect TATER_EFFECT = new TaterEffect();


    public static void initialize() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier("catcat","tater_effect"), TATER_EFFECT);
        }
}
