package com.zzyyio.catcat.block;

import com.zzyyio.catcat.damage.ModDamageTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TaterBlock extends Block {
    public TaterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity) {
            DamageSource damageSource = world.getDamageSources().create(ModDamageTypes.TATER_DAMAGE);
            entity.damage(damageSource, 1.0f);
        }
    }
}