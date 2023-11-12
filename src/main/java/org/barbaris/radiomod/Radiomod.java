package org.barbaris.radiomod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Radiomod implements ModInitializer {

    // -------------- ITEMS ----------
    public static final Item CAPACITOR = new Capacitor(new FabricItemSettings());
    public static final Item TRANSISTOR = new Transistor(new FabricItemSettings());
    public static final Item DIODE = new Diode(new FabricItemSettings());

    // -------------- BLOCKS ---------


    // -------------- MIX ------------
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(CAPACITOR))
            .displayName(Text.translatable("itemGroup.radiomod.group"))
            .entries((context, entries) -> {
                entries.add(CAPACITOR);
                entries.add(TRANSISTOR);
                entries.add(DIODE);
            }).build();

    // -------------- INITIALIZER ----
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("radiomod", "capacitor"), CAPACITOR);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "transistor"), TRANSISTOR);
        Registry.register(Registries.ITEM, new Identifier("radiomod", "diode"), DIODE);
        Registry.register(Registries.ITEM_GROUP, new Identifier("radiomod", "group"), ITEM_GROUP);
    }
}
