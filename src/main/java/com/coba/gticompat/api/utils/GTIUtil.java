package com.coba.gticompat.api.utils;

import com.coba.gticompat.Tags;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class GTIUtil {

    public static @NotNull ResourceLocation gtiId(@NotNull String path) {
        return new ResourceLocation(Tags.MODID, path);
    }

    private GTIUtil() {}
}
