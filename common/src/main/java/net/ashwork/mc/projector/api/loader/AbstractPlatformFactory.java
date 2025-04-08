package net.ashwork.mc.projector.api.loader;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.reference.AbstractFactoryReferenceHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractPlatformFactory extends AbstractFactoryReferenceHolder implements PlatformFactory {

    protected final Map<String, ModLoaderPlatform> platforms;
    protected final Map<String, DataModLoaderPlatform> data;

    protected AbstractPlatformFactory() {
        this.platforms = new ConcurrentHashMap<>();
        this.data = new ConcurrentHashMap<>();
    }

    protected abstract ModLoaderPlatform createPlatform(String modId);

    protected abstract DataModLoaderPlatform createDataPlatform(String modId);

    @Override
    public ModLoaderPlatform common(String modId) {
        return this.platforms.computeIfAbsent(modId, this::createPlatform);
    }

    @Override
    public DataModLoaderPlatform data(String modId) {
        return this.data.computeIfAbsent(modId, this::createDataPlatform);
    }
}
