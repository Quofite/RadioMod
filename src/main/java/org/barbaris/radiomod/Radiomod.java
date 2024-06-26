package org.barbaris.radiomod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.barbaris.radiomod.blocks.CircuitBlock;
import org.barbaris.radiomod.blocks.CircuitBlockEntity;
import org.barbaris.radiomod.blocks.CircuitBlockScreenHandler;
import org.barbaris.radiomod.blocks.Voltmeter;
import org.barbaris.radiomod.items.*;
import org.barbaris.radiomod.mix.Trades;
import org.barbaris.radiomod.villagers.EngineerVillager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Radiomod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("radiomod");

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
    public static final Item LED = new Led(new FabricItemSettings());
    public static final Item IC = new IC(new FabricItemSettings());

    // -------------- BLOCKS ---------
    public static final Identifier CIRCUIT_ID = new Identifier("radiomod", "circuit_block");
    public static final Block CIRCUIT_BLOCK = Registry.register(Registries.BLOCK, CIRCUIT_ID, new CircuitBlock(FabricBlockSettings.create().strength(1.0f).requiresTool().luminance(Blocks.createLightLevelFromLitBlockState(15))));
    public static final BlockItem CIRCUIT_BLOCK_ITEM = Registry.register(Registries.ITEM, CIRCUIT_ID, new BlockItem(CIRCUIT_BLOCK, new Item.Settings()));
    public static final BlockEntityType<CircuitBlockEntity> CIRCUIT_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, CIRCUIT_ID, FabricBlockEntityTypeBuilder.create(CircuitBlockEntity::new, CIRCUIT_BLOCK).build(null));
    public static final ScreenHandlerType<CircuitBlockScreenHandler> CIRCUIT_BLOCK_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(CIRCUIT_ID, CircuitBlockScreenHandler::new);

    public static final Identifier VOLTMETER_ID = new Identifier("radiomod", "voltmeter");
    public static final Block VOLTMETER = Registry.register(Registries.BLOCK, VOLTMETER_ID, new Voltmeter(FabricBlockSettings.create().strength(1.0f).requiresTool()));
    public static final BlockItem VOLTMETER_ITEM = Registry.register(Registries.ITEM, VOLTMETER_ID, new BlockItem(VOLTMETER, new Item.Settings()));

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
                entries.add(LED);
                entries.add(IC);

                entries.add(VOLTMETER_ITEM);
                entries.add(CIRCUIT_BLOCK_ITEM);
            }).build();

    // --- sounds ---

    public static final Identifier VOLTMETER_SOUND_ID = new Identifier("radiomod", "voltmeter_use");
    public static SoundEvent VOLTMETER_SOUND_EVENT = SoundEvent.of(VOLTMETER_SOUND_ID);
    public static Identifier VOLTAGE_DAMAGE_ID = new Identifier("radiomod", "voltage_damage_sound");
    public static SoundEvent VOLTAGE_DAMAGE_SOUND_EVENT = SoundEvent.of(VOLTAGE_DAMAGE_ID);


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
        Registry.register(Registries.ITEM, new Identifier("radiomod", "led"), LED);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "ic"), IC);

        Registry.register(Registries.SOUND_EVENT, VOLTMETER_SOUND_ID, VOLTMETER_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, VOLTAGE_DAMAGE_ID, VOLTAGE_DAMAGE_SOUND_EVENT);
        Registry.register(Registries.ITEM_GROUP, new Identifier("radiomod", "group"), ITEM_GROUP);

        EngineerVillager.registerVillager();
        Trades.registerCustomTrades();
    }
}
