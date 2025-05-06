package com.coba.gticompat.items;

import com.coba.gticompat.api.utils.GTIUtil;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.metatileentities.MetaTileEntities;
import ic2.core.item.type.CraftingItemType;
import ic2.core.item.type.IngotResourceType;
import ic2.core.item.type.NuclearResourceType;
import ic2.core.ref.BlockName;
import ic2.core.ref.ItemName;
import ic2.core.ref.TeBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

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
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(Items.URANIUM_ROD, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(Items.URANIUM_ROD_DUAL.getDefaultInstance())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(Items.URANIUM_ROD_DUAL, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(Items.URANIUM_ROD_QUAD.getDefaultInstance())
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.URANIUM_ROD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Uranium238, 3))
                .outputs(OreDictUnifier.get(dust, Plutonium239, 1))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .chancedOutput(OreDictUnifier.get(dust, Uranium235, 1), 2000, 0)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.URANIUM_ROD_DUAL_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Uranium238, 6))
                .outputs(OreDictUnifier.get(dust, Plutonium239, 2))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 2).getItem(), 2, 9)
                .chancedOutput(OreDictUnifier.get(dust, Uranium235, 2), 2000, 0)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.URANIUM_ROD_QUAD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Uranium238, 12))
                .outputs(OreDictUnifier.get(dust, Plutonium239, 4))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 4).getItem(), 4, 9)
                .chancedOutput(OreDictUnifier.get(dust, Uranium235, 4), 2000, 0)
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
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(Items.MOX_ROD, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(Items.MOX_ROD_DUAL.getDefaultInstance())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(Items.MOX_ROD_DUAL, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(Items.MOX_ROD_QUAD.getDefaultInstance())
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.MOX_ROD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Uranium238, 3))
                .outputs(OreDictUnifier.get(dust, Uranium235, 1))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .chancedOutput(OreDictUnifier.get(dust, Plutonium239, 1), 2000, 0)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.MOX_ROD_DUAL_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Uranium238, 6))
                .outputs(OreDictUnifier.get(dust, Uranium235, 2))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 2).getItem(), 2, 9)
                .chancedOutput(OreDictUnifier.get(dust, Plutonium239, 2), 2000, 0)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.MOX_ROD_QUAD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Uranium238, 12))
                .outputs(OreDictUnifier.get(dust, Uranium235, 4))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 4).getItem(), 4, 9)
                .chancedOutput(OreDictUnifier.get(dust, Plutonium239, 4), 2000, 0)
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
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(Items.THORIUM_ROD, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(Items.THORIUM_ROD_DUAL.getDefaultInstance())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(Items.THORIUM_ROD_DUAL, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(Items.THORIUM_ROD_QUAD.getDefaultInstance())
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.THORIUM_ROD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Thorium, 3))
                .outputs(OreDictUnifier.get(dust, Uranium235, 1))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .chancedOutput(OreDictUnifier.get(dust, Uranium235, 1), 2000,2)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.THORIUM_ROD_DUAL_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Thorium, 6))
                .outputs(OreDictUnifier.get(dust, Uranium235, 2))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 2).getItem(), 2, 9)
                .chancedOutput(OreDictUnifier.get(dust, Uranium235, 2), 2000,2)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.THORIUM_ROD_QUAD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Thorium, 12))
                .outputs(OreDictUnifier.get(dust, Uranium235, 4))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 4).getItem(), 4, 9)
                .chancedOutput(OreDictUnifier.get(dust, Uranium235, 4), 2000,2)
                .buildAndRegister();
        //Naquadah process
        COMPRESSOR_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(dust, Naquadah, 6)
                .outputs(Items.NAQUADAH_FUEL.getDefaultInstance())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .input(Items.NAQUADAH_FUEL)
                .outputs(Items.NAQUADAH_ROD.getDefaultInstance())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(Items.NAQUADAH_ROD, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(Items.NAQUADAH_ROD_DUAL.getDefaultInstance())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(Items.NAQUADAH_ROD_DUAL, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(Items.NAQUADAH_ROD_QUAD.getDefaultInstance())
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.NAQUADAH_ROD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Naquadah, 3))
                .outputs(OreDictUnifier.get(dust, Naquadria, 1))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 1).getItem(), 1, 9)
                .chancedOutput(OreDictUnifier.get(dust, Naquadria, 1), 2000,2)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.NAQUADAH_ROD_DUAL_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Naquadah, 6))
                .outputs(OreDictUnifier.get(dust, Naquadria, 2))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 2).getItem(), 2, 9)
                .chancedOutput(OreDictUnifier.get(dust, Naquadria, 2), 2000,2)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(Items.NAQUADAH_ROD_QUAD_DEP.getDefaultInstance().getItem())
                .outputs(OreDictUnifier.get(dust, Naquadah, 12))
                .outputs(OreDictUnifier.get(dust, Naquadria, 4))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.fuel_rod, 4).getItem(), 4, 9)
                .chancedOutput(OreDictUnifier.get(dust, Naquadria, 4), 2000,2)
                .buildAndRegister();
        //Nuclear reactor
        ModHandler.addShapedRecipe(true, "ic2_nuclear_reactor",  GTIUtil.getIC2ItemStack(BlockName.te, TeBlock.nuclear_reactor, 1), "PPP", "CHC", "PPP",
                'P', new UnificationEntry(plateDouble, Lead),
                'H', MetaTileEntities.HULL[GTValues.HV].getStackForm(),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.HV)
        );
        ModHandler.addShapedRecipe(true, "ic2_reactor_chamber", GTIUtil.getIC2ItemStack(BlockName.te, TeBlock.reactor_chamber, 1), "PPP", "PHP", "PPP",
                'P', new UnificationEntry(plateDouble, Lead),
                'H', MetaTileEntities.HULL[GTValues.EV].getStackForm()
        );
        ModHandler.addShapedRecipe(true, "ic2_heat_vent", GTIUtil.getIC2ItemStack(ItemName.heat_vent, 1), "BPB", "PMP", "BPB",
                'B', new ItemStack(Blocks.IRON_BARS, 1),
                'P', new UnificationEntry(plate, Iron),
                'M', ELECTRIC_MOTOR_LV
        );
        ModHandler.addShapedRecipe(true, "ic2_heat_exchanger", GTIUtil.getIC2ItemStack(ItemName.heat_exchanger, 1), "PCP", "IPI", "PIP",
                'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.LV),
                'P', new UnificationEntry(plate, Copper),
                'I', new UnificationEntry(plate, Iron)
        );
        ModHandler.addShapedRecipe(true, "ic2_advanced_heat_exchanger", GTIUtil.getIC2ItemStack(ItemName.advanced_heat_exchanger, 1), "LCL", "EPE", "LCL",
                'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.LV),
                'P', new UnificationEntry(plate, Copper),
                'E', GTIUtil.getIC2ItemStack(ItemName.advanced_heat_exchanger, 1),
                'L', new UnificationEntry(plate, Lapis)
        );
        BENDER_RECIPES.recipeBuilder().duration(80).EUt(120)
                .input(GTIUtil.getIC2ItemStack(ItemName.ingot, IngotResourceType.alloy, 1).getItem(), 1,0)
                .notConsumable(new IntCircuitIngredient(1))
                .output(GTIUtil.getIC2ItemStack(ItemName.crafting, CraftingItemType.alloy, 1).getItem(), 1, 3)
                .buildAndRegister();

    }
}
