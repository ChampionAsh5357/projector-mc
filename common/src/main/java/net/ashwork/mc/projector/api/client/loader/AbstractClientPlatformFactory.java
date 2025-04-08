package net.ashwork.mc.projector.api.client.loader;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.minecraft.util.context.ContextKey;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractClientPlatformFactory implements ClientPlatformFactory {

    protected final Map<String, ClientModLoaderPlatform> clients;
    protected final Map<ContextKey<?>, Object> store;

    protected AbstractClientPlatformFactory() {
        this.clients = new ConcurrentHashMap<>();
        this.store = new HashMap<>();
    }

    protected abstract ClientModLoaderPlatform createClientPlatform(String modId);

    @Override
    public ClientModLoaderPlatform client(String modId) {
        return this.clients.computeIfAbsent(modId, this::createClientPlatform);
    }

    @Override
    public void forEachClient(Consumer<ClientModLoaderPlatform> action) {
        this.clients.values().forEach(action);
    }

    @Nullable
    @Override
    public <T> T findFirstNotNull(Function<ClientModLoaderPlatform, T> action) {
        for (var client : this.clients.values()) {
            var result = action.apply(client);
            if (result != null) return result;
        }

        return null;
    }

    @Override
    public <T> void capture(ContextKey<T> key, T value) {
        this.store.put(key, value);
    }

    @Override
    public <T> void release(ContextKey<T> key) {
        this.store.remove(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(ContextKey<T> key) {
        return (T) this.store.get(key);
    }
}
