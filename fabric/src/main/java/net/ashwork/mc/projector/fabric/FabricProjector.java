package net.ashwork.mc.projector.fabric;

import net.ashwork.mc.projector.Projector;
import net.fabricmc.api.ModInitializer;

public class FabricProjector implements ModInitializer {

    @Override
    public void onInitialize() {
        Projector.init();
    }
}
