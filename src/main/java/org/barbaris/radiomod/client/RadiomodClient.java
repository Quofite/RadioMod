package org.barbaris.radiomod.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.barbaris.radiomod.Radiomod;
import org.barbaris.radiomod.blocks.CircuitBlockScreen;

public class RadiomodClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        HandledScreens.register(Radiomod.CIRCUIT_BLOCK_SCREEN_HANDLER, CircuitBlockScreen::new);
    }
}
