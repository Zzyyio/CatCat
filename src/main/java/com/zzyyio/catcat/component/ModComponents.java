package com.zzyyio.catcat.component;

import com.zzyyio.catcat.CatCat;

public class ModComponents {
//    public static final VertexFormatElement.ComponentType<Integer> CLICK_COUNT_COMPONENT = Registry.register(
//            BuiltInRegistries.DATA_COMPONENT_TYPE,
//            ResourceLocation.fromNamespaceAndPath(FabricDocsReference.MOD_ID, "click_count"),
//            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
//    );

    protected static void initialize() {
        System.out.println("Registering {} components failed since you didn't write this"+CatCat.MOD_ID);
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }

}
