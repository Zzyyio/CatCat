package com.zzyyio.catcat.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Comparator;

public class GoldenKnightSwordItem extends SwordItem {
    public GoldenKnightSwordItem(Settings settings){
        super(ToolMaterials.IRON,20,0.3f,settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        if (world.isClient()) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
        int timer = 0;
        while(timer<=200){
            LivingEntity nearestEntity = world.getEntitiesByClass(LivingEntity.class, user.getBoundingBox().expand(1000), LivingEntity::canHit)
                    .stream()
                    .filter(e -> e != user).min(Comparator.comparingDouble(e -> e.distanceTo(user)))
                    .orElse(null);
            System.out.println(nearestEntity.getPos());
            user.move(MovementType.SELF,nearestEntity.getPos());

            timer++;
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
