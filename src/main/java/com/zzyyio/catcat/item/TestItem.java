package com.zzyyio.catcat.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.config.builder.api.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Level;

import static net.minecraft.entity.effect.StatusEffects.HEALTH_BOOST;
import static net.minecraft.util.Formatting.GOLD;

public class TestItem extends Item {
    public TestItem(Settings settings) {
        super(settings.food(new FoodComponent.Builder()
                .hunger(10)
                .saturationModifier(1.0f)
                .snack()
                .statusEffect(new StatusEffectInstance(HEALTH_BOOST,200),1.0f)
                .build()));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        // 添加提示文字
        tooltip.add(Text.translatable("Leon is qu")
                .setStyle(Style.EMPTY.withColor(GOLD)));
    }
}
