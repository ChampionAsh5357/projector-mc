package net.ashwork.mc.projector.data;

import net.ashwork.mc.projector.ProjectorRef;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.data.generator.GeneratorFactory;
import net.ashwork.mc.projector.data.client.ProjectorTranslations;

public interface ProjectorData {

    DataModLoaderPlatform PLATFORM = ProjectorRef.FACTORY.data(ProjectorRef.ID);

    static void init() {
        PLATFORM.globalPack(ProjectorData::generateGlobalPack);
    }

    private static void generateGlobalPack(GeneratorFactory factory) {
        factory.translations(ProjectorTranslations::buildEnglishTranslations);
    }
}
