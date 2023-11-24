package org.barbaris.radiomod.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.barbaris.radiomod.Utils;

import java.util.ArrayList;
import java.util.List;

public class Ampermetr extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public Ampermetr(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
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
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos position, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            BlockPos neighbourPos1 = position.add(1, 0, 0);     // x + 1, z
            BlockPos neighbourPos2 = position.add(0, 0, 1);     // x, z + 1
            BlockPos neighbourPos3 = position.add(-1, 0, 0);    // x - 1, z
            BlockPos neighbourPos4 = position.add(0, 0, -1);    // x, z - 1

            boolean isPowered = world.isReceivingRedstonePower(position);
            MinecraftClient client = MinecraftClient.getInstance();

            if(isPowered) {
                List<Integer> powers = new ArrayList<>();

                if(world.getBlockState(neighbourPos1).getBlock() instanceof RedstoneWireBlock) {
                    powers.add(world.getBlockState(neighbourPos1).get(Properties.POWER));
                }
                if(world.getBlockState(neighbourPos2).getBlock() instanceof RedstoneWireBlock) {
                    powers.add(world.getBlockState(neighbourPos2).get(Properties.POWER));
                }
                if(world.getBlockState(neighbourPos3).getBlock() instanceof RedstoneWireBlock) {
                    powers.add(world.getBlockState(neighbourPos3).get(Properties.POWER));
                }
                if(world.getBlockState(neighbourPos4).getBlock() instanceof RedstoneWireBlock) {
                    powers.add(world.getBlockState(neighbourPos4).get(Properties.POWER));
                }

                client.player.sendMessage(Text.of(String.valueOf(Utils.max(powers))));
            } else {
                client.player.sendMessage(Text.translatable("item.radiomod.ampermetr.nosignal"));
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
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos position) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(position));
    }
}
