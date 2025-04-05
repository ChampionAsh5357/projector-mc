package net.ashwork.mc.projector.neoforge.api.client.mixin;

import net.ashwork.mc.projector.api.client.model.geom.ModelPartExtension;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelPart.class)
public class ModelPartMixin implements ModelPartExtension {

    @Unique
    private float[] bounds;
    @Unique
    private float[] boundsWithChildren;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void computeBounds(List<ModelPart.Cube> cubes, Map<String, ModelPart> children, CallbackInfo ci) {
        this.bounds = new float[] { Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE };
        for (ModelPart.Cube cube : cubes) {
            if (cube.minX < this.bounds[0]) this.bounds[0] = cube.minX;
            if (cube.minY < this.bounds[1]) this.bounds[1] = cube.minY;
            if (cube.minZ < this.bounds[2]) this.bounds[2] = cube.minZ;
            if (cube.maxX > this.bounds[3]) this.bounds[3] = cube.maxX;
            if (cube.maxY > this.bounds[4]) this.bounds[4] = cube.maxY;
            if (cube.maxZ > this.bounds[5]) this.bounds[5] = cube.maxZ;
        }
        this.boundsWithChildren = this.bounds.clone();
        for (ModelPart part : children.values()) {
            float[] partBounds = ((ModelPartExtension) (Object) part).getBounds();
            if (partBounds[0] < this.boundsWithChildren[0]) this.boundsWithChildren[0] = partBounds[0];
            if (partBounds[1] < this.boundsWithChildren[1]) this.boundsWithChildren[1] = partBounds[1];
            if (partBounds[2] < this.boundsWithChildren[2]) this.boundsWithChildren[2] = partBounds[2];
            if (partBounds[3] > this.boundsWithChildren[3]) this.boundsWithChildren[3] = partBounds[3];
            if (partBounds[4] > this.boundsWithChildren[4]) this.boundsWithChildren[4] = partBounds[4];
            if (partBounds[5] > this.boundsWithChildren[5]) this.boundsWithChildren[5] = partBounds[5];
        }
    }

    @Override
    public float[] getBounds() {
        return this.bounds;
    }

    @Override
    public float[] getBoundsWithChildren() {
        return this.boundsWithChildren;
    }
}
