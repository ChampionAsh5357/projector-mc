package net.ashwork.mc.projector;

import net.ashwork.mc.projector.api.loader.PlatformFactory;
import net.ashwork.mc.projector.api.util.PlatformHandler;

public interface ProjectorRef {
    
    String ID = "projector";
    PlatformFactory FACTORY = PlatformHandler.loadFirst(PlatformFactory.class);
}
