package com.zzyyio.catcat.item;

import com.mojang.authlib.yggdrasil.response.User;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
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
                .statusEffect(new StatusEffectInstance(HEALTH_BOOST, 200), 1.0f)
                .build()));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        // 添加提示文字
        tooltip.add(Text.translatable("Leon is qu")
                .setStyle(Style.EMPTY.withColor(GOLD)));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // As stated above, don't use the playSound() method on the client side
        // ... it wont work!
        if (!entity.getEntityWorld().isClient()) {
            // Play the sound as if it was coming from the entity.
            entity.playSound(SoundEvents.ENTITY_PILLAGER_AMBIENT, 2f, 0.7f);
        }

        return super.useOnEntity(stack, user, entity, hand);
    }

}