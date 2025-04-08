package net.ashwork.mc.projector.api.loader.reference;

import net.ashwork.mc.projector.api.loader.ModIdentifier;
import net.ashwork.mc.projector.api.util.BiContextKey;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public interface PlatformReferenceHolder extends FactoryReferenceHolder, ModIdentifier {

    @Nullable
    default <T, R> Function<T, R> getLocal(BiContextKey<T, R> key) {
        var ref = this.getGlobal(key);
        return ref != null ? input -> ref.apply(this.modId(), input) : null;
    }
}
