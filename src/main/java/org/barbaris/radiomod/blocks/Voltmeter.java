package org.barbaris.radiomod.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.barbaris.radiomod.Radiomod;
import org.barbaris.radiomod.mix.Utils;

import java.util.ArrayList;
import java.util.List;

public class Voltmeter extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty POWER = Properties.POWER;

    public Voltmeter(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(POWER, 0));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
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
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos position, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            BlockPos neighbourPos1 = position.add(1, 0, 0);     // x + 1, z --- EAST
            BlockPos neighbourPos2 = position.add(0, 0, 1);     // x, z + 1 --- SOUTH
            BlockPos neighbourPos3 = position.add(-1, 0, 0);    // x - 1, z --- WEST
            BlockPos neighbourPos4 = position.add(0, 0, -1);    // x, z - 1 --- NORTH
            BlockState pos1 = world.getBlockState(neighbourPos1);
            BlockState pos2 = world.getBlockState(neighbourPos2);
            BlockState pos3 = world.getBlockState(neighbourPos3);
            BlockState pos4 = world.getBlockState(neighbourPos4);

            world.playSound(null, position, Radiomod.VOLTMETER_SOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);

            boolean isPowered = world.isReceivingRedstonePower(position);
            MinecraftClient client = MinecraftClient.getInstance();

            if(isPowered) {
                List<Integer> powers = new ArrayList<>();

                if(
                    Utils.isRedstoneSource(pos1) ||
                    Utils.isRedstoneSource(pos2) ||
                    Utils.isRedstoneSource(pos3) ||
                    Utils.isRedstoneSource(pos4)
                ) {
                    client.player.sendMessage(Text.of("15"));
                    return ActionResult.SUCCESS;
                }

                if(pos1.getBlock() instanceof RedstoneWireBlock || pos1.getBlock() instanceof DaylightDetectorBlock) {
                    powers.add(pos1.get(Properties.POWER));
                }
                if(pos2.getBlock() instanceof RedstoneWireBlock || pos2.getBlock() instanceof DaylightDetectorBlock) {
                    powers.add(pos2.get(Properties.POWER));
                }
                if(pos3.getBlock() instanceof RedstoneWireBlock || pos3.getBlock() instanceof DaylightDetectorBlock) {
                    powers.add(pos3.get(Properties.POWER));
                }
                if(pos4.getBlock() instanceof RedstoneWireBlock || pos4.getBlock() instanceof DaylightDetectorBlock) {
                    powers.add(pos4.get(Properties.POWER));
                }

                client.player.sendMessage(Text.of(String.valueOf(Utils.max(powers))));
            } else {
                client.player.sendMessage(Text.translatable("item.radiomod.voltmeter.nosignal"));
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
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
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos position) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(position));
    }


}
