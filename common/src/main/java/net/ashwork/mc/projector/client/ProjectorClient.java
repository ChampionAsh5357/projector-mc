package net.ashwork.mc.projector.client;

import net.ashwork.mc.projector.ProjectorRef;
import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.ashwork.mc.projector.client.model.EntityModelEntries;

public interface ProjectorClient {

    ClientModLoaderPlatform PLATFORM = ClientProjectorRef.FACTORY.client(ProjectorRef.ID);

    static void init() {
        EntityModelEntries.register(PLATFORM.entityModels());
    }
}
