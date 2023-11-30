package org.barbaris.radiomod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.barbaris.radiomod.Utils;
import org.barbaris.radiomod.interfaces.ImplementedInventory;
import org.barbaris.radiomod.Radiomod;

public class CircuitBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public CircuitBlockEntity(BlockPos position, BlockState state) {
        super(Radiomod.CIRCUIT_BLOCK_ENTITY, position, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public ScreenHandler createMenu(int syncID, PlayerInventory playerInventory, PlayerEntity player) {
        return new CircuitBlockScreenHandler(syncID, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }

    public int getScheme() {
        DefaultedList<ItemStack> stacks = getItems();

        if(stacks.get(0).isOf(Radiomod.RESISTOR) &&
                stacks.get(1).isOf(Radiomod.RESISTOR) &&
                stacks.get(2).isOf(Radiomod.RESISTOR) &&
                stacks.get(3).isOf(Radiomod.CAPACITOR) &&
                stacks.get(4).isOf(Radiomod.RESISTOR) &&
                stacks.get(5).isOf(Radiomod.CAPACITOR) &&
                stacks.get(6).isOf(Radiomod.TRANSISTOR) &&
                stacks.get(7).isOf(Radiomod.LED) &&
                stacks.get(8).isOf(Radiomod.TRANSISTOR)) {
            return Utils.FLASHING_LIGHT;
        } else if (stacks.get(0).isOf(Radiomod.RESISTOR) &&
                stacks.get(1).isOf(Radiomod.LED)) {
            return Utils.LIGHT;
        }

        return Utils.NO_SCHEME;
    }

}

