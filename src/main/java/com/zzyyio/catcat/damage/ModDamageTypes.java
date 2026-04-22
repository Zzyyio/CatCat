package com.zzyyio.catcat.damage;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageTypes {

    public static final RegistryKey<DamageType> TATER_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("catcat", "tater"));


    public static void initialize(){

    }
}
