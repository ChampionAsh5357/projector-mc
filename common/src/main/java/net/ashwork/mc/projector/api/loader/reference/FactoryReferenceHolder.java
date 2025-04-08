package net.ashwork.mc.projector.api.loader.reference;

import net.ashwork.mc.projector.api.util.BiContextKey;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface FactoryReferenceHolder {

    <T, R> void register(BiContextKey<T, R> key, BiFunction<String, T, R> value);

    default <T, R> void register(BiContextKey<T, R> key, Function<T, R> value) {
        this.register(key, (modId, input) -> value.apply(input));
    }

    @Nullable
    <T, R> BiFunction<String, T, R> getGlobal(BiContextKey<T, R> key);
}
