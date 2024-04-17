package org.barbaris.radiomod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.barbaris.radiomod.mix.Utils;
import org.barbaris.radiomod.interfaces.ImplementedInventory;
import org.barbaris.radiomod.Radiomod;

public class CircuitBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(54, ItemStack.EMPTY);

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

        if(stacks.get(0).isOf(Radiomod.LED) &&
                stacks.get(1).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(2).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(3).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(4).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(5).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(6).isOf(Radiomod.LED) &&
                stacks.get(7).isOf(Items.AIR) &&
                stacks.get(8).isOf(Items.AIR) &&
                stacks.get(9).isOf(Radiomod.RESISTOR) &&
                stacks.get(10).isOf(Items.AIR) &&
                stacks.get(11).isOf(Radiomod.RESISTOR) &&
                stacks.get(12).isOf(Items.AIR) &&
                stacks.get(13).isOf(Radiomod.RESISTOR) &&
                stacks.get(14).isOf(Items.AIR) &&
                stacks.get(15).isOf(Radiomod.RESISTOR) &&
                stacks.get(16).isOf(Items.AIR) &&
                stacks.get(17).isOf(Items.AIR) &&
                stacks.get(18).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(19).isOf(Radiomod.CAPACITOR) &&
                stacks.get(20).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(21).isOf(Items.AIR) &&
                stacks.get(22).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(23).isOf(Radiomod.CAPACITOR) &&
                stacks.get(24).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(25).isOf(Items.AIR) &&
                stacks.get(26).isOf(Items.AIR) &&
                stacks.get(27).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(28).isOf(Items.AIR) &&
                stacks.get(29).isOf(Items.AIR) &&
                stacks.get(30).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(31).isOf(Items.AIR) &&
                stacks.get(32).isOf(Items.AIR) &&
                stacks.get(33).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(34).isOf(Items.AIR) &&
                stacks.get(35).isOf(Items.AIR) &&
                stacks.get(36).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(37).isOf(Radiomod.TRANSISTOR) &&
                stacks.get(38).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(39).isOf(Items.AIR) &&
                stacks.get(40).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(41).isOf(Radiomod.TRANSISTOR) &&
                stacks.get(42).isOf(Radiomod.BARE_WIRE) &&
                stacks.get(43).isOf(Items.AIR) &&
                stacks.get(44).isOf(Items.AIR) &&
                stacks.get(45).isOf(Items.AIR) &&
                stacks.get(46).isOf(Items.AIR) &&
                stacks.get(47).isOf(Items.AIR) &&
                stacks.get(48).isOf(Items.AIR) &&
                stacks.get(49).isOf(Items.AIR) &&
                stacks.get(50).isOf(Items.AIR) &&
                stacks.get(51).isOf(Items.AIR) &&
                stacks.get(52).isOf(Items.AIR) &&
                stacks.get(53).isOf(Items.AIR)) {
            return Utils.FLASHING_LIGHT;
        } else if (stacks.get(0).isOf(Radiomod.RESISTOR) &&
                stacks.get(1).isOf(Radiomod.LED) &&
                stacks.get(2).isOf(Items.AIR) &&
                stacks.get(3).isOf(Items.AIR) &&
                stacks.get(4).isOf(Items.AIR) &&
                stacks.get(5).isOf(Items.AIR) &&
                stacks.get(6).isOf(Items.AIR) &&
                stacks.get(7).isOf(Items.AIR) &&
                stacks.get(8).isOf(Items.AIR) &&
                stacks.get(9).isOf(Items.AIR) &&
                stacks.get(10).isOf(Items.AIR) &&
                stacks.get(11).isOf(Items.AIR) &&
                stacks.get(12).isOf(Items.AIR) &&
                stacks.get(13).isOf(Items.AIR) &&
                stacks.get(14).isOf(Items.AIR) &&
                stacks.get(15).isOf(Items.AIR) &&
                stacks.get(16).isOf(Items.AIR) &&
                stacks.get(17).isOf(Items.AIR) &&
                stacks.get(18).isOf(Items.AIR) &&
                stacks.get(19).isOf(Items.AIR) &&
                stacks.get(20).isOf(Items.AIR) &&
                stacks.get(21).isOf(Items.AIR) &&
                stacks.get(22).isOf(Items.AIR) &&
                stacks.get(23).isOf(Items.AIR) &&
                stacks.get(24).isOf(Items.AIR) &&
                stacks.get(25).isOf(Items.AIR) &&
                stacks.get(26).isOf(Items.AIR) &&
                stacks.get(27).isOf(Items.AIR) &&
                stacks.get(28).isOf(Items.AIR) &&
                stacks.get(29).isOf(Items.AIR) &&
                stacks.get(30).isOf(Items.AIR) &&
                stacks.get(31).isOf(Items.AIR) &&
                stacks.get(32).isOf(Items.AIR) &&
                stacks.get(33).isOf(Items.AIR) &&
                stacks.get(34).isOf(Items.AIR) &&
                stacks.get(35).isOf(Items.AIR) &&
                stacks.get(36).isOf(Items.AIR) &&
                stacks.get(37).isOf(Items.AIR) &&
                stacks.get(38).isOf(Items.AIR) &&
                stacks.get(39).isOf(Items.AIR) &&
                stacks.get(40).isOf(Items.AIR) &&
                stacks.get(41).isOf(Items.AIR) &&
                stacks.get(42).isOf(Items.AIR) &&
                stacks.get(43).isOf(Items.AIR) &&
                stacks.get(44).isOf(Items.AIR) &&
                stacks.get(45).isOf(Items.AIR) &&
                stacks.get(46).isOf(Items.AIR) &&
                stacks.get(47).isOf(Items.AIR) &&
                stacks.get(48).isOf(Items.AIR) &&
                stacks.get(49).isOf(Items.AIR) &&
                stacks.get(50).isOf(Items.AIR) &&
                stacks.get(51).isOf(Items.AIR) &&
                stacks.get(52).isOf(Items.AIR) &&
                stacks.get(53).isOf(Items.AIR)) {
            return Utils.LIGHT;
        } else if(stacks.get(0).isOf(Radiomod.RESISTOR) &&
                stacks.get(1).isOf(Radiomod.CAPACITOR) &&
                stacks.get(2).isOf(Items.CLOCK) &&
                stacks.get(3).isOf(Items.AIR) &&
                stacks.get(4).isOf(Items.AIR) &&
                stacks.get(5).isOf(Items.AIR) &&
                stacks.get(6).isOf(Items.AIR) &&
                stacks.get(7).isOf(Items.AIR) &&
                stacks.get(8).isOf(Items.AIR) &&
                stacks.get(9).isOf(Items.AIR) &&
                stacks.get(10).isOf(Items.AIR) &&
                stacks.get(11).isOf(Items.AIR) &&
                stacks.get(12).isOf(Items.AIR) &&
                stacks.get(13).isOf(Items.AIR) &&
                stacks.get(14).isOf(Items.AIR) &&
                stacks.get(15).isOf(Items.AIR) &&
                stacks.get(16).isOf(Items.AIR) &&
                stacks.get(17).isOf(Items.AIR) &&
                stacks.get(18).isOf(Items.AIR) &&
                stacks.get(19).isOf(Items.AIR) &&
                stacks.get(20).isOf(Items.AIR) &&
                stacks.get(21).isOf(Items.AIR) &&
                stacks.get(22).isOf(Items.AIR) &&
                stacks.get(23).isOf(Items.AIR) &&
                stacks.get(24).isOf(Items.AIR) &&
                stacks.get(25).isOf(Items.AIR) &&
                stacks.get(26).isOf(Items.AIR) &&
                stacks.get(27).isOf(Items.AIR) &&
                stacks.get(28).isOf(Items.AIR) &&
                stacks.get(29).isOf(Items.AIR) &&
                stacks.get(30).isOf(Items.AIR) &&
                stacks.get(31).isOf(Items.AIR) &&
                stacks.get(32).isOf(Items.AIR) &&
                stacks.get(33).isOf(Items.AIR) &&
                stacks.get(34).isOf(Items.AIR) &&
                stacks.get(35).isOf(Items.AIR) &&
                stacks.get(36).isOf(Items.AIR) &&
                stacks.get(37).isOf(Items.AIR) &&
                stacks.get(38).isOf(Items.AIR) &&
                stacks.get(39).isOf(Items.AIR) &&
                stacks.get(40).isOf(Items.AIR) &&
                stacks.get(41).isOf(Items.AIR) &&
                stacks.get(42).isOf(Items.AIR) &&
                stacks.get(43).isOf(Items.AIR) &&
                stacks.get(44).isOf(Items.AIR) &&
                stacks.get(45).isOf(Items.AIR) &&
                stacks.get(46).isOf(Items.AIR) &&
                stacks.get(47).isOf(Items.AIR) &&
                stacks.get(48).isOf(Items.AIR) &&
                stacks.get(49).isOf(Items.AIR) &&
                stacks.get(50).isOf(Items.AIR) &&
                stacks.get(51).isOf(Items.AIR) &&
                stacks.get(52).isOf(Items.AIR) &&
                stacks.get(53).isOf(Items.AIR)) {
            return Utils.CLOCK;
        } else if(stacks.get(0).isOf(Radiomod.TRANSISTOR) &&
                stacks.get(1).isOf(Items.AIR) &&
                stacks.get(2).isOf(Items.AIR) &&
                stacks.get(3).isOf(Items.AIR) &&
                stacks.get(4).isOf(Items.AIR) &&
                stacks.get(5).isOf(Items.AIR) &&
                stacks.get(6).isOf(Items.AIR) &&
                stacks.get(7).isOf(Items.AIR) &&
                stacks.get(8).isOf(Items.AIR) &&
                stacks.get(9).isOf(Items.AIR) &&
                stacks.get(10).isOf(Items.AIR) &&
                stacks.get(11).isOf(Items.AIR) &&
                stacks.get(12).isOf(Items.AIR) &&
                stacks.get(13).isOf(Items.AIR) &&
                stacks.get(14).isOf(Items.AIR) &&
                stacks.get(15).isOf(Items.AIR) &&
                stacks.get(16).isOf(Items.AIR) &&
                stacks.get(17).isOf(Items.AIR) &&
                stacks.get(18).isOf(Items.AIR) &&
                stacks.get(19).isOf(Items.AIR) &&
                stacks.get(20).isOf(Items.AIR) &&
                stacks.get(21).isOf(Items.AIR) &&
                stacks.get(22).isOf(Items.AIR) &&
                stacks.get(23).isOf(Items.AIR) &&
                stacks.get(24).isOf(Items.AIR) &&
                stacks.get(25).isOf(Items.AIR) &&
                stacks.get(26).isOf(Items.AIR) &&
                stacks.get(27).isOf(Items.AIR) &&
                stacks.get(28).isOf(Items.AIR) &&
                stacks.get(29).isOf(Items.AIR) &&
                stacks.get(30).isOf(Items.AIR) &&
                stacks.get(31).isOf(Items.AIR) &&
                stacks.get(32).isOf(Items.AIR) &&
                stacks.get(33).isOf(Items.AIR) &&
                stacks.get(34).isOf(Items.AIR) &&
                stacks.get(35).isOf(Items.AIR) &&
                stacks.get(36).isOf(Items.AIR) &&
                stacks.get(37).isOf(Items.AIR) &&
                stacks.get(38).isOf(Items.AIR) &&
                stacks.get(39).isOf(Items.AIR) &&
                stacks.get(40).isOf(Items.AIR) &&
                stacks.get(41).isOf(Items.AIR) &&
                stacks.get(42).isOf(Items.AIR) &&
                stacks.get(43).isOf(Items.AIR) &&
                stacks.get(44).isOf(Items.AIR) &&
                stacks.get(45).isOf(Items.AIR) &&
                stacks.get(46).isOf(Items.AIR) &&
                stacks.get(47).isOf(Items.AIR) &&
                stacks.get(48).isOf(Items.AIR) &&
                stacks.get(49).isOf(Items.AIR) &&
                stacks.get(50).isOf(Items.AIR) &&
                stacks.get(51).isOf(Items.AIR) &&
                stacks.get(52).isOf(Items.AIR) &&
                stacks.get(53).isOf(Items.AIR)) {
            return Utils.AMPLIFIER;
        }

        return Utils.NO_SCHEME;
    }

}

