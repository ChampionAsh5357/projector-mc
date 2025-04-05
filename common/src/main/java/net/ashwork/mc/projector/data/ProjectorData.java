package net.ashwork.mc.projector.data;

import net.ashwork.mc.projector.ProjectorRef;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.data.generator.GeneratorFactory;
import net.ashwork.mc.projector.api.loader.PlatformFactory;
import net.ashwork.mc.projector.data.client.ProjectorAssetCodecs;
import net.ashwork.mc.projector.data.client.ProjectorModels;
import net.ashwork.mc.projector.data.client.ProjectorTranslations;
import net.ashwork.mc.projector.data.server.ProjectorRecipes;

public interface ProjectorData {

    DataModLoaderPlatform PLATFORM = PlatformFactory.INSTANCE.data(ProjectorRef.ID);

    static void init() {
        PLATFORM.globalPack(ProjectorData::generateGlobalPack);
    }

    private static void generateGlobalPack(GeneratorFactory factory) {
        factory.translations(ProjectorTranslations::buildEnglishTranslations);
        factory.itemModels(ProjectorModels::buildItemModels);
        factory.equipment(ProjectorAssetCodecs::buildEquipment);

        factory.recipes(ProjectorRecipes::new);
    }
}
