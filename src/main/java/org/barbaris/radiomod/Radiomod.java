package org.barbaris.radiomod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.barbaris.radiomod.blocks.Ampermetr;
import org.barbaris.radiomod.blocks.CircuitBlock;
import org.barbaris.radiomod.blocks.CircuitBlockEntity;
import org.barbaris.radiomod.blocks.CircuitBlockScreenHandler;
import org.barbaris.radiomod.items.*;

public class Radiomod implements ModInitializer {

    // -------------- ITEMS ----------
    public static final Item CAPACITOR = new Capacitor(new FabricItemSettings());
    public static final Item TRANSISTOR = new Transistor(new FabricItemSettings());
    public static final Item DIODE = new Diode(new FabricItemSettings());
    public static final Item RESISTOR = new Resistor(new FabricItemSettings());
    public static final Item WIRE = new Wire(new FabricItemSettings());
    public static final Item COIL = new Coil(new FabricItemSettings());
    public static final Item BARE_WIRE = new BareWire(new FabricItemSettings());
    public static final Item TRANSFORMER = new Transformer(new FabricItemSettings());
    public static final Item TUBE = new Tube(new FabricItemSettings());
    public static final Item PCB = new Pcb(new FabricItemSettings());

    // -------------- BLOCKS ---------
    public static final Identifier CIRCUIT_ID = new Identifier("radiomod", "circuit_block");
    public static final Block CIRCUIT_BLOCK = Registry.register(Registries.BLOCK, CIRCUIT_ID, new CircuitBlock(FabricBlockSettings.create().strength(1.0f).requiresTool()));
    public static final BlockItem CIRCUIT_BLOCK_ITEM = Registry.register(Registries.ITEM, CIRCUIT_ID, new BlockItem(CIRCUIT_BLOCK, new Item.Settings()));
    public static final BlockEntityType<CircuitBlockEntity> CIRCUIT_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, CIRCUIT_ID, FabricBlockEntityTypeBuilder.create(CircuitBlockEntity::new, CIRCUIT_BLOCK).build(null));
    public static final ScreenHandlerType<CircuitBlockScreenHandler> CIRCUIT_BLOCK_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(CIRCUIT_ID, CircuitBlockScreenHandler::new);

    public static final Block AMPERMETR = Registry.register(Registries.BLOCK, new Identifier("radiomod", "ampermetr"), new Ampermetr(FabricBlockSettings.create().strength(1.0f).requiresTool()));
    public static final BlockItem AMPERMETR_ITEM = Registry.register(Registries.ITEM, new Identifier("radiomod", "ampermetr"), new BlockItem(AMPERMETR, new Item.Settings()));

    // -------------- MIX ------------
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(CAPACITOR))
            .displayName(Text.translatable("itemGroup.radiomod.group"))
            .entries((context, entries) -> {
                entries.add(CAPACITOR);
                entries.add(TRANSISTOR);
                entries.add(DIODE);
                entries.add(RESISTOR);
                entries.add(BARE_WIRE);
                entries.add(WIRE);
                entries.add(COIL);
                entries.add(TRANSFORMER);
                entries.add(PCB);
                entries.add(TUBE);

                entries.add(AMPERMETR_ITEM);
                entries.add(CIRCUIT_BLOCK_ITEM);
            }).build();

    // -------------- INITIALIZER ----
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("radiomod", "capacitor"), CAPACITOR);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "transistor"), TRANSISTOR);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "diode"), DIODE);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "coil"), COIL);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "resistor"), RESISTOR);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "wire"), WIRE);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "bare_wire"), BARE_WIRE);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "transformer"), TRANSFORMER);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "pcb"), PCB);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "tube"), TUBE);

        Registry.register(Registries.ITEM_GROUP, new Identifier("radiomod", "group"), ITEM_GROUP);
    }
}
