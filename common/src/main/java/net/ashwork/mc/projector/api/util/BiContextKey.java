package net.ashwork.mc.projector.api.util;

import net.minecraft.resources.ResourceLocation;

public record BiContextKey<T, R>(ResourceLocation name) {

    public static <T, R> BiContextKey<T, R> global(final String name) {
        return new BiContextKey<>(PlatformConstants.global(name));
    }
}
