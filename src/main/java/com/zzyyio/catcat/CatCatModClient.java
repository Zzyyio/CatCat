package com.zzyyio.catcat;

import com.zzyyio.catcat.particle.ModParticles;
import net.fabricmc.api.ClientModInitializer;

public class CatCatModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModParticles.clientSideRegister();
    }
}
