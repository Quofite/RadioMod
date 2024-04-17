package org.barbaris.radiomod.datagen;

import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.PointOfInterestTypeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.concurrent.CompletableFuture;

public class PoiTagProvider extends TagProvider<PointOfInterestType> {

    public PoiTagProvider(DataOutput out, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(out, RegistryKeys.POINT_OF_INTEREST_TYPE, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE).addOptional(new Identifier("radiomod", "voltmeterpoi"));
    }
}
