package net.ashwork.mc.projector.api.registry.impl;

import com.mojang.datafixers.util.Either;
import net.ashwork.mc.projector.api.registry.ObjectReference;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class HolderDelegateObjectReference<REGISTRY, IMPL extends REGISTRY> implements ObjectReference<REGISTRY, IMPL> {

    protected final Holder<REGISTRY> delegate;

    public HolderDelegateObjectReference(Holder<REGISTRY> delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public IMPL get() {
        return (IMPL) this.value();
    }

    @Override
    public REGISTRY value() {
        return this.delegate.value();
    }

    @Override
    public boolean isBound() {
        return this.delegate.isBound();
    }

    @Override
    public boolean is(ResourceLocation resourceLocation) {
        return this.delegate.is(resourceLocation);
    }

    @Override
    public boolean is(ResourceKey<REGISTRY> resourceKey) {
        return this.delegate.is(resourceKey);
    }

    @Override
    public boolean is(Predicate<ResourceKey<REGISTRY>> predicate) {
        return this.delegate.is(predicate);
    }

    @Override
    public boolean is(TagKey<REGISTRY> tagKey) {
        return this.delegate.is(tagKey);
    }

    @Override
    public boolean is(Holder<REGISTRY> holder) {
        return this.delegate.is(holder);
    }

    @Override
    public Stream<TagKey<REGISTRY>> tags() {
        return this.delegate.tags();
    }

    @Override
    public Either<ResourceKey<REGISTRY>, REGISTRY> unwrap() {
        return this.delegate.unwrap();
    }

    @Override
    public Optional<ResourceKey<REGISTRY>> unwrapKey() {
        return this.delegate.unwrapKey();
    }

    @Override
    public Kind kind() {
        return this.delegate.kind();
    }

    @Override
    public boolean canSerializeIn(HolderOwner<REGISTRY> holderOwner) {
        return this.delegate.canSerializeIn(holderOwner);
    }
}
