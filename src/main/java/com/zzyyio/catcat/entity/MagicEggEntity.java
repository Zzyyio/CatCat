package com.zzyyio.catcat.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class MagicEggEntity extends EggEntity {

    public MagicEggEntity(EntityType<? extends EggEntity> entityType, World world) {
        super(entityType, world);
    }

    public MagicEggEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public MagicEggEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected void onCollision(HitResult hitResult){
        getWorld().createExplosion(null,this.getX(),this.getY(),this.getZ(),10.0f, World.ExplosionSourceType.TNT);
        IronGolemEntity ironGolemEntity = EntityType.IRON_GOLEM.create(this.getWorld());
        if (ironGolemEntity != null) {
            ironGolemEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
            this.getWorld().spawnEntity(ironGolemEntity);
        }
        super.onCollision(hitResult);

    }
}
