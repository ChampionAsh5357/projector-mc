package net.ashwork.mc.projector.api.client;

import net.ashwork.mc.projector.api.client.model.EntityModelManager;
import net.ashwork.mc.projector.api.loader.ModIdentifier;

public interface ClientModLoaderPlatform extends ModIdentifier {

    EntityModelManager entityModels();
}
