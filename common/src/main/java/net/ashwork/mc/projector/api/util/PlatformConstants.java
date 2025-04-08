package net.ashwork.mc.projector.api.util;

import net.minecraft.resources.ResourceLocation;

public interface PlatformConstants {

    String GLOBAL = "g";

    static ResourceLocation global(String name) {
        return ResourceLocation.fromNamespaceAndPath(GLOBAL, name);
    }
}
