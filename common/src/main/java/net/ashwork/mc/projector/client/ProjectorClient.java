package net.ashwork.mc.projector.client;

import net.ashwork.mc.projector.ProjectorRef;
import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;

public interface ProjectorClient {

    ClientModLoaderPlatform PLATFORM = ClientProjectorRef.FACTORY.client(ProjectorRef.ID);

    static void init() {}
}
