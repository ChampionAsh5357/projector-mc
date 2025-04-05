package net.ashwork.mc.projector.api.client.loader;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.ashwork.mc.projector.api.util.PlatformHandler;
import net.ashwork.mc.projector.api.util.Store;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ClientPlatformFactory extends Store {

    ClientPlatformFactory INSTANCE = PlatformHandler.loadFirst(ClientPlatformFactory.class);

    ClientModLoaderPlatform client(String modId);

    void forEachClient(Consumer<ClientModLoaderPlatform> action);

    @Nullable
    <T> T findFirstNotNull(Function<ClientModLoaderPlatform, T> action);
}
