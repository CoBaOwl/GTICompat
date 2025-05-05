package com.coba.gticompat.items;

import gregtech.api.unification.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.recipes.ModHandler.removeRecipeByOutput;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class Items {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final ItemRadioactive THORIUM_ROD_DEP = new ItemRadioactive("thorium_rod_dep", 1);
    public static final ItemRadioactive THORIUM_ROD_DUAL_DEP = new ItemRadioactive("thorium_rod_dual_dep", 1);
    public static final ItemRadioactive THORIUM_ROD_QUAD_DEP = new ItemRadioactive("thorium_rod_quad_dep", 1);
    public static final ItemReactorThorium THORIUM_ROD = new ItemReactorThorium("thorium_rod", 1, 50000, 0.4F, 0, 0.25F, false, THORIUM_ROD_DEP.getDefaultInstance());
    public static final ItemReactorThorium THORIUM_ROD_DUAL = new ItemReactorThorium("thorium_rod_dual", 2, 50000, 0.4F, 0, 0.25F, false, THORIUM_ROD_DUAL_DEP.getDefaultInstance());
    public static final ItemReactorThorium THORIUM_ROD_QUAD = new ItemReactorThorium("thorium_rod_quad", 4, 50000, 0.4F, 0, 0.25F, false, THORIUM_ROD_QUAD_DEP.getDefaultInstance());
    public static final ItemRadioactive URANIUM_ROD_DEP = new ItemRadioactive("uranium_rod_dep", 1);
    public static final ItemRadioactive URANIUM_ROD_DUAL_DEP = new ItemRadioactive("uranium_rod_dual_dep", 1);
    public static final ItemRadioactive URANIUM_ROD_QUAD_DEP = new ItemRadioactive("uranium_rod_quad_dep", 1);
    public static final ItemReactorThorium URANIUM_ROD = new ItemReactorThorium("uranium_rod", 1, 20000, 2F, 1, 1F, false, URANIUM_ROD_DEP.getDefaultInstance());
    public static final ItemReactorThorium URANIUM_ROD_DUAL = new ItemReactorThorium("uranium_rod_dual", 2, 20000, 2F, 1, 1F, false, URANIUM_ROD_DUAL_DEP.getDefaultInstance());
    public static final ItemReactorThorium URANIUM_ROD_QUAD = new ItemReactorThorium("uranium_rod_quad", 4, 20000, 2F, 1, 1F, false, URANIUM_ROD_QUAD_DEP.getDefaultInstance());
    public static final ItemRadioactive MOX_ROD_DEP = new ItemRadioactive("mox_rod_dep", 1);
    public static final ItemRadioactive MOX_ROD_DUAL_DEP = new ItemRadioactive("mox_rod_dual_dep", 1);
    public static final ItemRadioactive MOX_ROD_QUAD_DEP = new ItemRadioactive("mox_rod_quad_dep", 1);
    public static final ItemReactorThorium MOX_ROD = new ItemReactorThorium("mox_rod", 1, 10000, 2F, 1, 1F, true, MOX_ROD_DEP.getDefaultInstance());
    public static final ItemReactorThorium MOX_ROD_DUAL = new ItemReactorThorium("mox_rod_dual", 2, 10000, 2F, 1, 1F, true, MOX_ROD_DUAL_DEP.getDefaultInstance());
    public static final ItemReactorThorium MOX_ROD_QUAD = new ItemReactorThorium("mox_rod_quad", 4, 10000, 2F, 1, 1F, true, MOX_ROD_QUAD_DEP.getDefaultInstance());
    public static final ItemRadioactive NAQUADAH_ROD_DEP = new ItemRadioactive("naquadah_rod_dep", 1);
    public static final ItemRadioactive NAQUADAH_ROD_DUAL_DEP = new ItemRadioactive("naquadah_rod_dual_dep", 1);
    public static final ItemRadioactive NAQUADAH_ROD_QUAD_DEP = new ItemRadioactive("naquadah_rod_quad_dep", 1);
    public static final ItemReactorThorium NAQUADAH_ROD = new ItemReactorThorium("naquadah_rod", 1, 100000, 2F, 1, 1F, true, NAQUADAH_ROD_DEP.getDefaultInstance());
    public static final ItemReactorThorium NAQUADAH_ROD_DUAL = new ItemReactorThorium("naquadah_rod_dual", 2, 100000, 2F, 1, 1F, true, NAQUADAH_ROD_DUAL_DEP.getDefaultInstance());
    public static final ItemReactorThorium NAQUADAH_ROD_QUAD = new ItemReactorThorium("naquadah_rod_quad", 4, 100000, 2F, 1, 1F, true, NAQUADAH_ROD_QUAD_DEP.getDefaultInstance());
    public static final ItemRadioactive ENRICHED_THORIUM_FUEL = new ItemRadioactive("enriched_thorium_fuel", 1);
    public static final ItemRadioactive NAQUADAH_FUEL = new ItemRadioactive("naquadah_fuel", 1);

    public static void initItems() {

    }
}
