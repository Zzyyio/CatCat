package com.zzyyio.catcat.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PrismarineLampBlock extends Block {

    public PrismarineLampBlock(Settings settings) {
        super(settings);
        // Set the default state of the block to be deactivated.
        setDefaultState(getDefaultState().with(STRENGTH, 0));

    }
    public static final IntProperty STRENGTH = IntProperty.of("strength",0,15);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STRENGTH);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.canModifyBlocks()) {
            // Skip if the player isn't allowed to modify the world.
            return ActionResult.PASS;
        } else {
            // Get the current value of the "activated" property
            int strength = state.get(STRENGTH);

            // Flip the value of activated and save the new blockstate.
            if(strength==15){
                world.setBlockState(pos, state.with(STRENGTH, 0));
            }
            else{
                world.setBlockState(pos, state.with(STRENGTH, strength+5));
            }
            // Play a click sound to emphasise the interaction.
            world.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return ActionResult.SUCCESS;
        }
    }
    public static int getLuminance(BlockState currentBlockState) {
        // Get the value of the "activated" property.
        int strength = currentBlockState.get(STRENGTH);//PrismarineLampBlock.

        // Return a light level if activated = true
        return strength;
    }
}