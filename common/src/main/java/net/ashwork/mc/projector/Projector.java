package net.ashwork.mc.projector;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.PlatformFactory;
import net.ashwork.mc.projector.init.ProjectorRegistrars;

public interface Projector {

    ModLoaderPlatform PLATFORM = PlatformFactory.INSTANCE.common(ProjectorRef.ID);

    static void init() {
        ProjectorRegistrars.init();
    }
}
