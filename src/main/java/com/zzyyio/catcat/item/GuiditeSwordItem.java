package com.zzyyio.catcat.item;

import com.llamalad7.mixinextras.lib.apache.commons.ObjectUtils;
import com.zzyyio.catcat.material.GuiditeMaterial;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.Nullables;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import static net.minecraft.world.World.ExplosionSourceType.TNT;


public class GuiditeSwordItem extends SwordItem {
    public GuiditeSwordItem(Settings settings){
        super(GuiditeMaterial.guidite,1000, 0.5F,settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Ensure we don't spawn the lightning only on the client.
        // This is to prevent desync.
        if (world.isClient()) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        HitResult hit = user.raycast(500.0D, 0.0F, false);
        ItemStack stack = user.getStackInHand(hand);

        if (hit.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(stack);
        }

        // 减少 10 点耐久
        stack.damage(5,user,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

        SpawnLightning(3,hit,world);
        world.createExplosion(null,hit.getPos().getX(),hit.getPos().getY(),hit.getPos().getZ(),10.0f,TNT);
        // Nothing has changed to the item stack,
        // so we just return it how it was.

        return TypedActionResult.success(stack);
    }



    private void SpawnLightning(int frequency, HitResult hit, World world){
        for(int i=0;i<frequency;i++){
            LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightningBolt.setPos(hit.getPos().getX(),hit.getPos().getY(),hit.getPos().getZ());
            world.spawnEntity(lightningBolt);
        }
    }

}
