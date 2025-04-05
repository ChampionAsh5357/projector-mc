package net.ashwork.mc.projector.api.util;

import net.minecraft.util.context.ContextKey;
import org.jetbrains.annotations.Nullable;

public interface Store {

    <T> void store(ContextKey<T> key, T value);

    <T> void release(ContextKey<T> key);

    @Nullable
    <T> T get(ContextKey<T> key);
}
