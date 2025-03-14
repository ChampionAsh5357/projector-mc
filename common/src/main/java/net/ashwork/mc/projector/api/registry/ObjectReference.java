package net.ashwork.mc.projector.api.registry;

import net.minecraft.core.Holder;

import java.util.function.Supplier;

public interface ObjectReference<REGISTRY, IMPL extends REGISTRY> extends Holder<REGISTRY>, Supplier<IMPL> {
}
