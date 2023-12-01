package org.barbaris.radiomod.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import org.barbaris.radiomod.DamageTypes;
import org.barbaris.radiomod.Radiomod;
import org.barbaris.radiomod.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CircuitBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty POWER = Properties.POWER;
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final IntProperty LIGHT_LEVEL = Properties.LEVEL_15;

    public CircuitBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)
                .with(POWER, 0)
                .with(POWERED, false)
                .with(LIT, false)
                .with(LIGHT_LEVEL, 0));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos position, BlockState state) {
        return new CircuitBlockEntity(position, state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(POWER);
        builder.add(POWERED);
        builder.add(LIT);
        builder.add(LIGHT_LEVEL);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos position, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            if(!world.isReceivingRedstonePower(position)) {
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, position);

                if(screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            } else {
                // MinecraftClient client = MinecraftClient.getInstance();
                player.sendMessage(Text.translatable("item.radiomod.circuit_block.under_voltage"));
                world.playSound(null, position, Radiomod.VOLTAGE_DAMAGE_SOUND_EVENT, SoundCategory.BLOCKS, 0.1f, 1f);
                player.damage(DamageTypes.of(world, DamageTypes.VOLTAGE_DAMAGE_TYPE), 10.0f);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos position, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity entity = world.getBlockEntity(position);

            if(entity instanceof CircuitBlockEntity) {
                ItemScatterer.spawn(world, position, (Inventory) entity);
                world.updateComparators(position, this);
            }

            super.onStateReplaced(state, world, position, newState, moved);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        if(world.isReceivingRedstonePower(pos)) {
            BlockEntity entity = world.getBlockEntity(pos);
            if(entity instanceof CircuitBlockEntity) {
                int scheme = ((CircuitBlockEntity) entity).getScheme();

                switch (scheme) {
                    case Utils.LIGHT:
                        world.setBlockState(pos, state.cycle(LIT).with(POWERED, true).with(LIT, true).with(LIGHT_LEVEL, 15));
                        break;
                    case Utils.FLASHING_LIGHT:
                        world.scheduleBlockTick(pos, this, 20);
                        break;
                }

            }
        } else {
            world.setBlockState(pos, state.with(POWERED, false).with(LIT, false).with(LIGHT_LEVEL, 0));
        }
    }

    // TODO: write scheme to some non-private field, so we can change logic of scheduledTick() method
    // TODO 2: DEFENETLY this bullshit needs a refactor but screw it
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);

        if(state.get(LIT)) {
            world.setBlockState(pos, state.cycle(LIT).with(POWERED, true).with(LIT, false).with(LIGHT_LEVEL, 0));
        } else {
            world.setBlockState(pos, state.cycle(LIT).with(POWERED, true).with(LIT, true).with(LIGHT_LEVEL, 15));
        }

        BlockEntity entity = world.getBlockEntity(pos);
        if(entity instanceof CircuitBlockEntity && world.isReceivingRedstonePower(pos)) {
            if(((CircuitBlockEntity) entity).getScheme() == Utils.FLASHING_LIGHT) {
                world.scheduleBlockTick(pos, this, 20);
            }
        } else if (!world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(LIT).with(POWERED, true).with(LIT, false).with(LIGHT_LEVEL, 0));
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos position) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(position));
    }


}

















