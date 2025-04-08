package net.ashwork.mc.projector.api.util;

import net.minecraft.util.context.ContextKey;
import org.jetbrains.annotations.Nullable;

public interface CaptureHolder {

    <T> void capture(ContextKey<T> key, T value);

    <T> void release(ContextKey<T> key);

    @Nullable
    <T> T get(ContextKey<T> key);
}
