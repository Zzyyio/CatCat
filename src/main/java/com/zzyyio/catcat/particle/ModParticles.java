package com.zzyyio.catcat.particle;

import com.zzyyio.catcat.CatCat;
import com.zzyyio.catcat.effect.ModEffects;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    // This DefaultParticleType gets called when you want to use your particle in code.
    public static final DefaultParticleType SPARKLE_PARTICLE = register(new Identifier("catcat","sparkle_particle"),FabricParticleTypes.simple());

    public static void initialize(){

    }

    public static void clientSideRegister(){
        // For this example, we will use the end rod particle behaviour.
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPARKLE_PARTICLE, EndRodParticle.Factory::new);
    }

    // Register our custom particle type in the mod initializer.
    private static DefaultParticleType register(Identifier identifier, DefaultParticleType particle){
        return Registry.register(Registries.PARTICLE_TYPE, identifier, particle);
    }
}

