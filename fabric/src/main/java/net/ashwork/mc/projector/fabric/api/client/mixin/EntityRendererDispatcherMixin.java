package net.ashwork.mc.projector.fabric.api.client.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.ashwork.mc.projector.api.client.loader.ClientPlatformFactory;
import net.ashwork.mc.projector.fabric.api.client.model.FabricEntityModelManager;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRendererDispatcherMixin {

    @Inject(at = @At("TAIL"), method = "onResourceManagerReload")
    private void resourceReload(ResourceManager manager, CallbackInfo ci, @Local EntityRendererProvider.Context context) {
        ClientPlatformFactory.INSTANCE.forEachClient(platform -> ((FabricEntityModelManager) platform.entityModels()).resolveModels(context.getModelSet()));
    }
}
