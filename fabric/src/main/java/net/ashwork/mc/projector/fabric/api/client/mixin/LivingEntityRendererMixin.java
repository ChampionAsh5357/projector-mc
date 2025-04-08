package net.ashwork.mc.projector.fabric.api.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.projector.api.client.loader.ClientGlobalKeys;
import net.ashwork.mc.projector.api.client.loader.ClientPlatformFactory;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {

    @Shadow
    protected M model;

    @Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V")
    private void capture(S livingEntityRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        ClientPlatformFactory.INSTANCE.capture(ClientGlobalKeys.ENTITY_STATE, livingEntityRenderState);
        ClientPlatformFactory.INSTANCE.capture(ClientGlobalKeys.PARENT_MODEL, this.model);
    }

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V")
    private void release(S livingEntityRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        ClientPlatformFactory.INSTANCE.release(ClientGlobalKeys.ENTITY_STATE);
        ClientPlatformFactory.INSTANCE.release(ClientGlobalKeys.PARENT_MODEL);
    }
}
