package com.zzyyio.catcat.item;

import com.zzyyio.catcat.material.GuiditeMaterial;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GoldenKnightSwordItem extends SwordItem {
    private static final int MAX_IDLE_TICKS = 20 * 10;
    private static final int ATTACK_COOLDOWN_TICKS = 8;
    private static final double SEARCH_RADIUS = 8.0D;
    private static final double FORWARD_DASH_SPEED = 1.0D;
    private static final double TARGET_DASH_SPEED = 1.5D;
    private static final double ATTACK_RANGE = 2.8D;

    private static final Map<UUID, DashState> ACTIVE_DASHES = new HashMap<>();
    private static boolean tickRegistered = false;

    public GoldenKnightSwordItem(Settings settings) {
        super(GuiditeMaterial.guidite, 8, -2.0F, settings);
    }

    public static void initialize() {
        if (tickRegistered) {
            return;
        }

        tickRegistered = true;
        ServerTickEvents.END_WORLD_TICK.register(GoldenKnightSwordItem::onEndWorldTick);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (world.isClient()) {
            return TypedActionResult.success(stack);
        }

        ACTIVE_DASHES.put(user.getUuid(), new DashState(world.getTime()));
        user.getItemCooldownManager().set(this, 20);
        return TypedActionResult.success(stack);
    }

    private static void onEndWorldTick(ServerWorld world) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            DashState state = ACTIVE_DASHES.get(player.getUuid());
            if (state == null) {
                continue;
            }

            if (!player.isAlive()) {
                ACTIVE_DASHES.remove(player.getUuid());
                continue;
            }

            long now = world.getTime();
            LivingEntity target = findNearestTarget(player, state);

            if (target != null) {
                state.lastTargetFoundTick = now;
                player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, target.getEyePos());

                Vec3d towardsTarget = target.getPos().subtract(player.getPos()).normalize();
                player.setVelocity(towardsTarget.multiply(TARGET_DASH_SPEED));
                player.velocityModified = true;

                if (player.squaredDistanceTo(target) <= ATTACK_RANGE * ATTACK_RANGE
                        && now - state.lastAttackTick >= ATTACK_COOLDOWN_TICKS) {
                    player.swingHand(Hand.MAIN_HAND, true);
                    player.attack(target);
                    state.lastAttackTick = now;
                    state.hitTargets.add(target.getUuid());
                }
                continue;
            }

            Vec3d look = player.getRotationVector();
            Vec3d horizontalForward = new Vec3d(look.x, 0.0D, look.z);
            if (horizontalForward.lengthSquared() > 0.0D) {
                horizontalForward = horizontalForward.normalize();
            }
            Vec3d velocity = horizontalForward.multiply(FORWARD_DASH_SPEED);
            player.setVelocity(velocity.x, player.getVelocity().y, velocity.z);
            player.velocityModified = true;

            if (now - state.lastTargetFoundTick >= MAX_IDLE_TICKS) {
                ACTIVE_DASHES.remove(player.getUuid());
            }
        }
    }

    private static LivingEntity findNearestTarget(ServerPlayerEntity player, DashState state) {
        BoxSearchResult result = new BoxSearchResult();

        player.getWorld().getEntitiesByClass(
                LivingEntity.class,
                player.getBoundingBox().expand(SEARCH_RADIUS),
                candidate -> candidate.isAlive()
                        && candidate != player
                        && !state.hitTargets.contains(candidate.getUuid())
        ).forEach(candidate -> {
            double distance = candidate.squaredDistanceTo(player);
            if (distance < result.bestDistance) {
                result.bestDistance = distance;
                result.target = candidate;
            }
        });

        return result.target;
    }

    private static class DashState {
        private final Set<UUID> hitTargets = new HashSet<>();
        private long lastTargetFoundTick;
        private long lastAttackTick;

        private DashState(long startTick) {
            this.lastTargetFoundTick = startTick;
            this.lastAttackTick = startTick - ATTACK_COOLDOWN_TICKS;
        }
    }

    private static class BoxSearchResult {
        private LivingEntity target;
        private double bestDistance = Double.MAX_VALUE;
    }
}
