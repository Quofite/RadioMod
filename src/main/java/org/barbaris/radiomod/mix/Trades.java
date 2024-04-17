package org.barbaris.radiomod.mix;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import org.barbaris.radiomod.Radiomod;
import org.barbaris.radiomod.villagers.EngineerVillager;

public class Trades {

    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(EngineerVillager.ENGINEER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(Radiomod.BARE_WIRE, 8),
                            12, 1, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(Radiomod.RESISTOR, 3),
                            12, 1, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(Radiomod.CAPACITOR, 3),
                            12, 1, 0.075f));
        });

        TradeOfferHelper.registerVillagerOffers(EngineerVillager.ENGINEER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 15),
                            new ItemStack(Radiomod.TRANSFORMER, 1),
                            6, 6, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(Radiomod.WIRE, 5),
                            12, 6, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(Radiomod.COIL, 4),
                            12, 6, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(Radiomod.LED, 5),
                            12, 6, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(Radiomod.DIODE, 5),
                            12, 6, 0.075f));
        });

        TradeOfferHelper.registerVillagerOffers(EngineerVillager.ENGINEER, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 15),
                            new ItemStack(Radiomod.CIRCUIT_BLOCK_ITEM, 1),
                            2, 10, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 15),
                            new ItemStack(Radiomod.VOLTMETER_ITEM, 1),
                            2, 10, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(Radiomod.IC, 6),
                            6, 10, 0.075f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(Radiomod.TRANSISTOR, 6),
                            6, 10, 0.075f));
        });
    }
}
