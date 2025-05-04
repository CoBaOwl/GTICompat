package com.coba.gticompat.items;

import gregtech.api.unification.material.Material;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING;

public class Materials {
    public static Material UraniumFuel;
    public static Material MoxFuel;
    public static void registryMaterials() {
        UraniumFuel = new Material.Builder(32001, new ResourceLocation("gticompat", "uranium_fuel"))
                .dust()
                .color(0x52950F)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Uranium238, 18, Uranium235, 1)
                .build();
        MoxFuel = new Material.Builder(32002, new ResourceLocation("gticompat", "mox_fuel"))
                .dust()
                .color(0x7BB541)
                .flags(DECOMPOSITION_BY_CENTRIFUGING)
                .components(Uranium238, 18, Plutonium239, 1)
                .build();
    }
}
