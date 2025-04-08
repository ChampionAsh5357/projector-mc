package net.ashwork.mc.projector.api.loader;

import net.ashwork.mc.projector.api.loader.reference.FactoryReferenceHolder;
import net.ashwork.mc.projector.api.loader.reference.PlatformReferenceHolder;
import net.ashwork.mc.projector.api.util.BiContextKey;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public abstract class AbstractModLoaderPlatform implements ModIdentifier, PlatformReferenceHolder {

    private final FactoryReferenceHolder factory;
    private final String modId;

    protected AbstractModLoaderPlatform(FactoryReferenceHolder factory, String modId) {
        this.factory = factory;
        this.modId = modId;
    }

    @Override
    public String modId() {
        return this.modId;
    }

    @Override
    public @Nullable <T, R> BiFunction<String, T, R> getGlobal(BiContextKey<T, R> key) {
        return this.factory.getGlobal(key);
    }

    @Override
    public <T, R> void register(BiContextKey<T, R> key, BiFunction<String, T, R> value) {
        this.factory.register(key, value);
    }
}
