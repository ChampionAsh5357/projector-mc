package net.ashwork.mc.projector.api.util;

import java.util.ServiceLoader;

public interface PlatformHandler {

    static <T> T loadFirst(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No available service found for " + clazz));
    }
}
