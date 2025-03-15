package net.ashwork.mc.projector.api.data.provider;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class ResourceKeyOutput<KEY, OBJECT> implements BiConsumer<ResourceKey<KEY>, OBJECT> {

    private final BiConsumer<ResourceKey<KEY>, OBJECT> registrar;
    private final ResourceKey<? extends Registry<KEY>> registryKey;
    private final String modId;

    public ResourceKeyOutput(BiConsumer<ResourceKey<KEY>, OBJECT> registrar, ResourceKey<? extends Registry<KEY>> registryKey, String modId) {
        this.registrar = registrar;
        this.registryKey = registryKey;
        this.modId = modId;
    }

    public void accept(String name, OBJECT value) {
        this.accept(ResourceLocation.fromNamespaceAndPath(this.modId, name), value);
    }

    public void accept(ResourceLocation key, OBJECT value) {
        this.accept(ResourceKey.create(this.registryKey, key), value);
    }

    @Override
    public void accept(ResourceKey<KEY> key, OBJECT object) {
        this.registrar.accept(key, object);
    }
}
