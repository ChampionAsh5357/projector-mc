package net.ashwork.mc.projector.api.loader.reference;

import net.ashwork.mc.projector.api.util.BiContextKey;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class AbstractFactoryReferenceHolder implements FactoryReferenceHolder {

    protected final Map<BiContextKey<?, ?>, BiFunction<String, ?, ?>> references;

    public AbstractFactoryReferenceHolder() {
        this.references = new ConcurrentHashMap<>();
    }

    @Override
    public <T, R> void register(BiContextKey<T, R> key, BiFunction<String, T, R> value) {
        if (this.references.putIfAbsent(key, value) != null) {
            throw new IllegalArgumentException("Duplicate key for reference: " + key);
        }
    }

    @Override
    public @Nullable <T, R> BiFunction<String, T, R> getGlobal(BiContextKey<T, R> key) {
        @SuppressWarnings("unchecked")
        BiFunction<String, T, R> ref = (BiFunction<String, T, R>) this.references.get(key);
        return ref;
    }
}
