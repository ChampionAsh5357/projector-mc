package net.ashwork.mc.projector.api.loader;

import net.minecraft.resources.ResourceLocation;

public interface ModIdentifier {

    String modId();

    default ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(this.modId(), path);
    }
}
