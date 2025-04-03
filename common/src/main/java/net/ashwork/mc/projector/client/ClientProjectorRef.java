package net.ashwork.mc.projector.client;

import net.ashwork.mc.projector.api.client.loader.ClientPlatformFactory;
import net.ashwork.mc.projector.api.util.PlatformHandler;

public interface ClientProjectorRef {

    ClientPlatformFactory FACTORY = PlatformHandler.loadFirst(ClientPlatformFactory.class);
}
