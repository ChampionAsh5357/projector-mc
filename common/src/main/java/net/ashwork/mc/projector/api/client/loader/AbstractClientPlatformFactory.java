package net.ashwork.mc.projector.api.client.loader;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractClientPlatformFactory implements ClientPlatformFactory {

    protected final Map<String, ClientModLoaderPlatform> clients;

    protected AbstractClientPlatformFactory() {
        this.clients = new ConcurrentHashMap<>();
    }

    protected abstract ClientModLoaderPlatform createClientPlatform(String modId);

    @Override
    public ClientModLoaderPlatform client(String modId) {
        return this.clients.computeIfAbsent(modId, this::createClientPlatform);
    }
}
