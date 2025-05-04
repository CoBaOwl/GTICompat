package com.coba.gticompat.items;

import com.coba.gticompat.api.utils.GTIUtil;
import gregtech.api.unification.OreDictUnifier;
import ic2.core.item.type.CraftingItemType;
import ic2.core.item.type.NuclearResourceType;
import ic2.core.ref.ItemName;

import static com.coba.gticompat.items.Materials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class RecepiesHandler {
    public static void initRecipes() {
        //Empty fuel rod
        EXTRUDER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(ingot, StainlessSteel, 4)
                .notConsumable(SHAPE_EXTRUDER_CELL.getStackForm())
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .buildAndRegister();
        //Uranium process
        MIXER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(dust, Uranium238, 18)
                .input(dust, Uranium235, 1)
                .output(dust, UraniumFuel, 19)
                .buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(dust, UraniumFuel, 6)
                .outputs(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.uranium, 1))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(ItemName.crafting.getItemStack(CraftingItemType.fuel_rod).getItem(), 1, 9)
                .input(ItemName.nuclear.getItemStack(NuclearResourceType.uranium).getItem(), 1, 0)
                .outputs(Items.URANIUM_ROD.getDefaultInstance())
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.URANIUM_ROD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Uranium238, 4))
                .outputs(OreDictUnifier.get(dust, Uranium235, 1))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .chancedOutput(OreDictUnifier.get(dust, Plutonium239, 1), 2000, 0)
                .buildAndRegister();
        //MOX process
        MIXER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(dust, Uranium238, 18)
                .input(dust, Plutonium239, 1)
                .output(dust, MoxFuel, 19)
                .buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(dust, MoxFuel, 6)
                .outputs(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.mox, 1))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .input(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.mox, 1).getItem(), 1, 4)
                .outputs(Items.MOX_ROD.getDefaultInstance())
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.MOX_ROD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Uranium238, 4))
                .outputs(OreDictUnifier.get(dust, Plutonium239, 1))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .chancedOutput(OreDictUnifier.get(dust, Uranium235, 1), 2000, 0)
                .buildAndRegister();
        //Thorium process
        COMPRESSOR_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(dust, Thorium, 6)
                .outputs(Items.ENRICHED_THORIUM_FUEL.getDefaultInstance())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .input(Items.ENRICHED_THORIUM_FUEL)
                .outputs(Items.THORIUM_ROD.getDefaultInstance())
                .buildAndRegister();
//        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
//                .input(Items.THORIUM_ROD_DEP.getDefaultInstance().getItem())
//                .outputs(OreDictUnifier.get(dust, Uranium238, 4))
//                .chancedOutput(OreDictUnifier.get(dust, Plutonium239, 2), 95,2)
//                .chancedOutput(OreDictUnifier.get(dust, Uranium235, 1), 20,2)
//                .buildAndRegister();
    }
}
