package org.barbaris.radiomod.villagers;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.barbaris.radiomod.Radiomod;

public class EngineerVillager {

    public static final RegistryKey<PointOfInterestType> VOLTMETER_POI_KEY = poiKey("voltmeterpoi");
    public static final PointOfInterestType VOLTMETER_POI = registerPoi("voltmeterpoi", Radiomod.VOLTMETER);

    public static final VillagerProfession ENGINEER = registerProfession("engineer", VOLTMETER_POI_KEY);

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier("radiomod", name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD));
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier("radiomod", name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier("radiomod", name));
    }

    public static void registerVillager() {
        Radiomod.LOGGER.info("reg villager");
    }
}
