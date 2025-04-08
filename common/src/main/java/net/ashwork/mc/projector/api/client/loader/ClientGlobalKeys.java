package net.ashwork.mc.projector.api.client.loader;

import net.ashwork.mc.projector.api.util.PlatformConstants;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.util.context.ContextKey;

public interface ClientGlobalKeys {

    ContextKey<Model> PARENT_MODEL = key("parent_model");
    ContextKey<EntityRenderState> ENTITY_STATE = key("entity_state");

    private static <T> ContextKey<T> key(String name) {
        return new ContextKey<>(PlatformConstants.global(name));
    }
}
