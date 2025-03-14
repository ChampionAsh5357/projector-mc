package net.ashwork.mc.projector;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.init.ProjectorRegistrars;

public interface Projector {

    ModLoaderPlatform PLATFORM = ProjectorRef.FACTORY.common(ProjectorRef.ID);

    static void init() {
        ProjectorRegistrars.init();
    }
}
