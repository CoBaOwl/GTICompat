package com.coba.gticompat.api.utils;

import com.cleanroommc.groovyscript.compat.mods.appliedenergistics2.AppliedEnergistics2;
import com.cleanroommc.groovyscript.compat.mods.ic2.IC2;

import gregtech.api.recipes.ModHandler;
import gregtech.common.items.MetaItems;
import ic2.core.block.ITeBlock;
import ic2.core.block.state.IIdProvider;
import ic2.core.block.type.ResourceBlock;
import ic2.core.block.wiring.CableType;
import ic2.core.item.block.ItemCable;
import ic2.core.item.type.*;
import ic2.core.ref.BlockName;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import mods.railcraft.common.items.ItemDust;
import mods.railcraft.common.items.ItemGear;
import mods.railcraft.common.items.Metal;
import mods.railcraft.common.items.RailcraftItems;
import mods.railcraft.common.util.crafting.RockCrusherCrafter;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;

import static com.cleanroommc.groovyscript.compat.mods.ModSupport.APPLIED_ENERGISTICS_2;
import static com.cleanroommc.groovyscript.compat.mods.ModSupport.INDUSTRIALCRAFT;

public final class GTIUtil {

    public static <T extends Enum<T> & IIdProvider> ItemStack getIC2ItemStack(ItemName name, T variant, int count) {
        ItemStack ret = name.getItemStack(variant);
        ret.setCount(count);
        return ret;
    }
    public static <T extends Enum<T> & ITeBlock> ItemStack getIC2ItemStack(BlockName name, T variant, int count) {
        ItemStack ret = name.getItemStack(variant);
        ret.setCount(count);
        return ret;
    }
    public static ItemStack getIC2ItemStack(ItemName name, int count) {
        ItemStack ret = name.getItemStack();
        ret.setCount(count);
        return ret;
    }
    public static void removeIC2MecRecepies() {
        IC2 ic2 = INDUSTRIALCRAFT.get();
        ic2.metalFormer.removeAll(0);
        ic2.metalFormer.removeAll(1);
        ic2.metalFormer.removeAll(2);
        ic2.solidCanner.removeAll();
        ic2.fluidCanner.removeAll();
        ic2.blastFurnace.removeAll();
        ic2.macerator.removeAll();
        ic2.extractor.removeAll();
        ic2.electrolyzer.removeAll();
        ic2.oreWasher.removeAll();
        ic2.centrifuge.removeAll();
        ic2.blockCutter.removeAll();
        ic2.compressor.removeAll();
        ic2.fermenter.removeAll();
        AppliedEnergistics2 ae2 = APPLIED_ENERGISTICS_2.get();
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.copper, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.iron, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.ender_pearl, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.bronze, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.lead, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.coal, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.gold, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.silver, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.tin, 1));
        ae2.grinder.removeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.obsidian, 1));


    }

    public static void removeIC2Mec() {
//old
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.barrel));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.wall));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.itnt));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.nuke));

//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.geo_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.kinetic_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.rt_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.semifluid_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.solar_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.stirling_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.wind_generator));
//        ModHandler.removeRecipeByOutput(getIC2ItemStack(BlockName.te,TeBlock.water_generator, 2));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.electric_heat_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.fluid_heat_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.rt_heat_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.solid_heat_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.electric_kinetic_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.manual_kinetic_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.steam_kinetic_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.stirling_kinetic_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.water_kinetic_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.wind_kinetic_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.reactor_access_hatch));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.reactor_fluid_port));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.reactor_redstone_port));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.condenser));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.reactor_chamber));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.nuclear_reactor));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.fluid_bottler));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.fluid_distributor));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.fluid_regulator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.liquid_heat_exchanger));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.pump));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.solar_distiller));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.steam_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.item_buffer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.luminator_flat));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.magnetizer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.sorting_machine));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.teleporter));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.terraformer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.tesla_coil));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.canner));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.compressor));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.electric_furnace));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.extractor));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.iron_furnace));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.macerator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.recycler));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.solid_canner));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.blast_furnace));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.block_cutter));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.centrifuge));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.fermenter));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.induction_furnace));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.metal_former));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.ore_washing_plant));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.advanced_miner));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.crop_harvester));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.cropmatron));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.miner));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.mass_fabricator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.uu_assembly_bench));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.matter_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.pattern_storage));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.replicator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.scanner));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.energy_o_mat));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.personal_chest));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.trade_o_mat));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.trading_terminal));
//old
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.cable));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.detector_cable));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.splitter_cable));

//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.chargepad_batbox));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.chargepad_cesu));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.chargepad_mfe));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.chargepad_mfsu));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.batbox));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.cesu));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.mfe));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.mfsu));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.electrolyzer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.lv_transformer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.mv_transformer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.hv_transformer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.ev_transformer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.tank));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.chunk_loader));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.item_buffer_2));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.creative_generator));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.steam_repressurizer));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.weighted_fluid_distributor));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.weighted_item_distributor));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.rci_rsh));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.rci_lzh));
//old
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.crop));

//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.industrial_workbench));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.batch_crafter));
//old
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.fluid_pipe));

//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.coke_kiln));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.coke_kiln_hatch));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.coke_kiln_grate));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.wooden_storage_box));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.iron_storage_box));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.bronze_storage_box));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.steel_storage_box));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.iridium_storage_box));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.bronze_tank));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.iron_tank));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.steel_tank));
//        ModHandler.removeRecipeByOutput(BlockName.te.getItemStack(TeBlock.iridium_tank));
    }

    public static void removeIC2Items() {
        //Jei show
        ModHandler.removeRecipeByOutput(ItemName.nuclear.getItemStack(NuclearResourceType.uranium));
        ModHandler.removeRecipeByOutput(ItemName.nuclear.getItemStack(NuclearResourceType.mox));
        //Jei hide
//        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.barrel, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.nuclear, NuclearResourceType.uranium_235, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.nuclear, NuclearResourceType.uranium_238, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.nuclear, NuclearResourceType.plutonium, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.nuclear, NuclearResourceType.small_plutonium, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.nuclear, NuclearResourceType.small_uranium_235, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.nuclear, NuclearResourceType.small_uranium_238, 9));
//        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.nuclear, NuclearResourceType.rtg_pellet, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.copper, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.tin, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.bronze, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.bronze, 4));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.diamond, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.energium, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.gold, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.iron, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.lapis, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.lead, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.lithium, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.obsidian, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.silver, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.sulfur, 1));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.dust, DustResourceType.emerald, 1));

//        ModHandler.removeRecipeByOutput(getIC2Item(ItemName.ingot, IngotResourceType.alloy, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.ingot, IngotResourceType.bronze, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.ingot, IngotResourceType.copper, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.ingot, IngotResourceType.lead, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.ingot, IngotResourceType.silver, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.ingot, IngotResourceType.steel, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.ingot, IngotResourceType.tin, 9));
        ModHandler.removeRecipeByOutput(getIC2ItemStack(ItemName.heat_vent, 1));


//        ModHandler.removeRecipeByOutput(getIC2Item(ItemName.ingot, IngotResourceType.refined_iron, 9));
//        ModHandler.removeRecipeByOutput(getIC2Item(ItemName.ingot, IngotResourceType.uranium, 9));

    }

    public static void removeIC2OreDict(){
        remove("oreCopper", BlockName.resource.getItemStack(ResourceBlock.copper_ore));
//        remove("oreLead", BlockName.resource.getItemStack(ResourceBlock.lead_ore));
        remove("oreTin", BlockName.resource.getItemStack(ResourceBlock.tin_ore));
        remove("oreUranium", BlockName.resource.getItemStack(ResourceBlock.uranium_ore));
        remove("treeLeaves", StackUtil.copyWithWildCard(BlockName.leaves.getItemStack()));
        remove("treeSapling", StackUtil.copyWithWildCard(BlockName.sapling.getItemStack()));
        remove("itemRubber", ItemName.crafting.getItemStack(CraftingItemType.rubber));
        remove("materialRubber", ItemName.crafting.getItemStack(CraftingItemType.rubber));
        remove("materialResin", ItemName.misc_resource.getItemStack(MiscResourceType.resin));
        remove("itemResin", ItemName.misc_resource.getItemStack(MiscResourceType.resin));
        remove("dustStone", ItemName.dust.getItemStack(DustResourceType.stone));
        remove("dustBronze", ItemName.dust.getItemStack(DustResourceType.bronze));
        remove("dustClay", ItemName.dust.getItemStack(DustResourceType.clay));
        remove("dustCoal", ItemName.dust.getItemStack(DustResourceType.coal));
//        remove("dustCopper", ItemName.dust.getItemStack(DustResourceType.copper));
        remove("dustGold", ItemName.dust.getItemStack(DustResourceType.gold));
        remove("dustIron", ItemName.dust.getItemStack(DustResourceType.iron));
        remove("dustSilver", ItemName.dust.getItemStack(DustResourceType.silver));
        remove("dustTin", ItemName.dust.getItemStack(DustResourceType.tin));
//        remove("dustLead", ItemName.dust.getItemStack(DustResourceType.lead));
        remove("dustObsidian", ItemName.dust.getItemStack(DustResourceType.obsidian));
        remove("dustLapis", ItemName.dust.getItemStack(DustResourceType.lapis));
        remove("dustSulfur", ItemName.dust.getItemStack(DustResourceType.sulfur));
        remove("dustLithium", ItemName.dust.getItemStack(DustResourceType.lithium));
        remove("dustDiamond", ItemName.dust.getItemStack(DustResourceType.diamond));
        remove("dustSiliconDioxide", ItemName.dust.getItemStack(DustResourceType.silicon_dioxide));
        remove("dustHydratedCoal", ItemName.dust.getItemStack(DustResourceType.coal_fuel));
        remove("dustNetherrack", ItemName.dust.getItemStack(DustResourceType.netherrack));
        remove("dustEnderPearl", ItemName.dust.getItemStack(DustResourceType.ender_pearl));
        remove("dustEnderEye", ItemName.dust.getItemStack(DustResourceType.ender_eye));
        remove("dustMilk", ItemName.dust.getItemStack(DustResourceType.milk));
        remove("powderMilk", ItemName.dust.getItemStack(DustResourceType.milk));
        remove("dustAshes", ItemName.misc_resource.getItemStack(MiscResourceType.ashes));
        remove("itemSlag", ItemName.misc_resource.getItemStack(MiscResourceType.slag));
        remove("dustTinyCopper", ItemName.dust.getItemStack(DustResourceType.small_copper));
        remove("dustTinyGold", ItemName.dust.getItemStack(DustResourceType.small_gold));
        remove("dustTinyIron", ItemName.dust.getItemStack(DustResourceType.small_iron));
        remove("dustTinySilver", ItemName.dust.getItemStack(DustResourceType.small_silver));
        remove("dustTinyTin", ItemName.dust.getItemStack(DustResourceType.small_tin));
        remove("dustTinyLead", ItemName.dust.getItemStack(DustResourceType.small_lead));
        remove("dustTinySulfur", ItemName.dust.getItemStack(DustResourceType.small_sulfur));
        remove("dustTinyLithium", ItemName.dust.getItemStack(DustResourceType.small_lithium));
        remove("dustTinyBronze", ItemName.dust.getItemStack(DustResourceType.small_bronze));
        remove("dustTinyLapis", ItemName.dust.getItemStack(DustResourceType.small_lapis));
        remove("dustTinyObsidian", ItemName.dust.getItemStack(DustResourceType.small_obsidian));
        remove("itemRubber", ItemName.crafting.getItemStack(CraftingItemType.rubber));
        remove("ingotBronze", ItemName.ingot.getItemStack(IngotResourceType.bronze));
        remove("ingotCopper", ItemName.ingot.getItemStack(IngotResourceType.copper));
        remove("ingotSteel", ItemName.ingot.getItemStack(IngotResourceType.steel));
        remove("ingotLead", ItemName.ingot.getItemStack(IngotResourceType.lead));
        remove("ingotTin", ItemName.ingot.getItemStack(IngotResourceType.tin));
        remove("ingotSilver", ItemName.ingot.getItemStack(IngotResourceType.silver));
        remove("ingotRefinedIron", ItemName.ingot.getItemStack(IngotResourceType.refined_iron));
        remove("ingotUranium", ItemName.ingot.getItemStack(IngotResourceType.uranium));
        remove("plateIron", ItemName.plate.getItemStack(PlateResourceType.iron));
        remove("plateGold", ItemName.plate.getItemStack(PlateResourceType.gold));
        remove("plateCopper", ItemName.plate.getItemStack(PlateResourceType.copper));
        remove("plateTin", ItemName.plate.getItemStack(PlateResourceType.tin));
        remove("plateLead", ItemName.plate.getItemStack(PlateResourceType.lead));
        remove("plateLapis", ItemName.plate.getItemStack(PlateResourceType.lapis));
        remove("plateObsidian", ItemName.plate.getItemStack(PlateResourceType.obsidian));
        remove("plateBronze", ItemName.plate.getItemStack(PlateResourceType.bronze));
        remove("plateSteel", ItemName.plate.getItemStack(PlateResourceType.steel));
        remove("plateDenseSteel", ItemName.plate.getItemStack(PlateResourceType.dense_steel));
        remove("plateDenseIron", ItemName.plate.getItemStack(PlateResourceType.dense_iron));
        remove("plateDenseGold", ItemName.plate.getItemStack(PlateResourceType.dense_gold));
        remove("plateDenseCopper", ItemName.plate.getItemStack(PlateResourceType.dense_copper));
        remove("plateDenseTin", ItemName.plate.getItemStack(PlateResourceType.dense_tin));
        remove("plateDenseLead", ItemName.plate.getItemStack(PlateResourceType.dense_lead));
        remove("plateDenseLapis", ItemName.plate.getItemStack(PlateResourceType.dense_lapis));
        remove("plateDenseObsidian", ItemName.plate.getItemStack(PlateResourceType.dense_obsidian));
        remove("plateDenseBronze", ItemName.plate.getItemStack(PlateResourceType.dense_bronze));
        remove("crushedIron", ItemName.crushed.getItemStack(OreResourceType.iron));
        remove("crushedGold", ItemName.crushed.getItemStack(OreResourceType.gold));
        remove("crushedSilver", ItemName.crushed.getItemStack(OreResourceType.silver));
        remove("crushedLead", ItemName.crushed.getItemStack(OreResourceType.lead));
        remove("crushedCopper", ItemName.crushed.getItemStack(OreResourceType.copper));
        remove("crushedTin", ItemName.crushed.getItemStack(OreResourceType.tin));
        remove("crushedUranium", ItemName.crushed.getItemStack(OreResourceType.uranium));
        remove("crushedPurifiedIron", ItemName.purified.getItemStack(OreResourceType.iron));
        remove("crushedPurifiedGold", ItemName.purified.getItemStack(OreResourceType.gold));
        remove("crushedPurifiedSilver", ItemName.purified.getItemStack(OreResourceType.silver));
        remove("crushedPurifiedLead", ItemName.purified.getItemStack(OreResourceType.lead));
        remove("crushedPurifiedCopper", ItemName.purified.getItemStack(OreResourceType.copper));
        remove("crushedPurifiedTin", ItemName.purified.getItemStack(OreResourceType.tin));
        remove("crushedPurifiedUranium", ItemName.purified.getItemStack(OreResourceType.uranium));
        remove("blockBronze", BlockName.resource.getItemStack(ResourceBlock.bronze_block));
        remove("blockCopper", BlockName.resource.getItemStack(ResourceBlock.copper_block));
        remove("blockTin", BlockName.resource.getItemStack(ResourceBlock.tin_block));
        remove("blockUranium", BlockName.resource.getItemStack(ResourceBlock.uranium_block));
        remove("blockLead", BlockName.resource.getItemStack(ResourceBlock.lead_block));
        remove("blockSilver", BlockName.resource.getItemStack(ResourceBlock.silver_block));
        remove("blockSteel", BlockName.resource.getItemStack(ResourceBlock.steel_block));
        remove("circuitBasic", ItemName.crafting.getItemStack(CraftingItemType.circuit));
        remove("circuitAdvanced", ItemName.crafting.getItemStack(CraftingItemType.advanced_circuit));

        remove("gemDiamond", ItemName.crafting.getItemStack(CraftingItemType.industrial_diamond));
        remove("craftingToolForgeHammer", StackUtil.copyWithWildCard(ItemName.forge_hammer.getItemStack()));
        remove("craftingToolWireCutter", StackUtil.copyWithWildCard(ItemName.cutter.getItemStack()));
        remove("fuelCoke", ItemName.coke.getItemStack());
        remove("itemUUMatter", ItemName.misc_resource.getItemStack(MiscResourceType.matter));
        remove("materialUUMatter", ItemName.misc_resource.getItemStack(MiscResourceType.matter));
        remove("nuggetIridium", ItemName.misc_resource.getItemStack(MiscResourceType.iridium_shard));
        remove("gemIridium", ItemName.misc_resource.getItemStack(MiscResourceType.iridium_ore));
        remove("itemFertilizer", ItemName.crop_res.getItemStack(CropResItemType.fertilizer));
        remove("plateCarbon", ItemName.crafting.getItemStack(CraftingItemType.carbon_plate));
        remove("plateAdvancedAlloy", ItemName.crafting.getItemStack(CraftingItemType.alloy));
        remove("plateadvancedAlloy", ItemName.crafting.getItemStack(CraftingItemType.alloy));
        remove("itemScrap", ItemName.crafting.getItemStack(CraftingItemType.scrap));
        remove("materialScrap", ItemName.crafting.getItemStack(CraftingItemType.scrap));
        remove("itemScrapBox", ItemName.crafting.getItemStack(CraftingItemType.scrap_box));
        remove("itemCarbonFibre", ItemName.crafting.getItemStack(CraftingItemType.carbon_fibre));
        remove("itemCarbonMesh", ItemName.crafting.getItemStack(CraftingItemType.carbon_mesh));
        remove("itemRawCarbonMesh", ItemName.crafting.getItemStack( CraftingItemType.carbon_mesh));
        remove("machineBlock", BlockName.resource.getItemStack(ResourceBlock.machine));
        remove("machineBlockCasing", BlockName.resource.getItemStack(ResourceBlock.machine));
        remove("machineBlockAdvanced", BlockName.resource.getItemStack(ResourceBlock.advanced_machine));
        remove("machineBlockAdvancedCasing", BlockName.resource.getItemStack( ResourceBlock.advanced_machine));
        remove("reBattery", StackUtil.copyWithWildCard(ItemName.re_battery.getItemStack()));
        remove("energyCrystal", StackUtil.copyWithWildCard(ItemName.energy_crystal.getItemStack()));
        remove("lapotronCrystal", StackUtil.copyWithWildCard(ItemName.lapotron_crystal.getItemStack()));

//        remove("ic2Generator", BlockName.te.getItemStack(TeBlock.generator));
//        remove("ic2SolarPanel", BlockName.te.getItemStack(TeBlock.solar_generator));
//        remove("ic2Macerator", BlockName.te.getItemStack(TeBlock.macerator));
//        remove("ic2Extractor", BlockName.te.getItemStack(TeBlock.extractor));
//        remove("ic2Windmill", BlockName.te.getItemStack(TeBlock.wind_generator));
//        remove("ic2Watermill", BlockName.te.getItemStack(TeBlock.water_generator));

        remove("itemTinCable", ItemCable.getCable(CableType.tin, 0));
        remove("itemInsulatedTinCable", ItemCable.getCable(CableType.tin, 1));
        remove("itemCopperCable", ItemCable.getCable(CableType.copper, 0));
        remove("itemInsulatedCopperCable", ItemCable.getCable(CableType.copper, 1));
        remove("itemGoldCable", ItemCable.getCable(CableType.gold, 0));
        remove("itemInsulatedGoldCable", ItemCable.getCable(CableType.gold, 2));
        remove("itemIronCable", ItemCable.getCable(CableType.iron, 0));
        remove("itemInsulatedIronCable", ItemCable.getCable(CableType.iron, 3));
        remove("itemInsulatedGlassCable", ItemCable.getCable(CableType.glass, 0));
        remove("itemDetectorCable", ItemCable.getCable(CableType.detector, 0));
        remove("itemDetectorSplitter", ItemCable.getCable(CableType.splitter, 0));
    }

    public static void removeRCOreDict() {
        //Ingots, plate, nuggets, blocks???
        removeRCOreDict("Lead", Metal.LEAD);
        removeRCOreDict("Copper", Metal.COPPER);
        removeRCOreDict("Tin", Metal.TIN);
        removeRCOreDict("Silver", Metal.SILVER);
        removeRCOreDict("Bronze", Metal.BRONZE);
        removeRCOreDict("Nickel", Metal.NICKEL);
        removeRCOreDict("Invar", Metal.INVAR);
        removeRCOreDict("Zinc", Metal.ZINC);
        removeRCOreDict("Brass", Metal.BRASS);
        removeRCOreDict("Steel", Metal.STEEL);
        removeRCOreDict("Iron", Metal.STEEL);
        removeRCOreDict("Gold", Metal.STEEL);

        //RailCraft dusts, exclude Slug
        remove("dustObsidian",  RailcraftItems.DUST.getStack(ItemDust.EnumDust.OBSIDIAN));
        remove("dustCharcoal", RailcraftItems.DUST.getStack(ItemDust.EnumDust.CHARCOAL));
        remove("dustCoal", RailcraftItems.DUST.getStack(ItemDust.EnumDust.COAL));
        remove("dustSaltpeter", RailcraftItems.DUST.getStack(ItemDust.EnumDust.SALTPETER));
        remove("dustEnder", RailcraftItems.DUST.getStack(ItemDust.EnumDust.ENDER));
        remove("dustEnderPearl", RailcraftItems.DUST.getStack(ItemDust.EnumDust.ENDER));
        remove("dustSulfur", RailcraftItems.DUST.getStack(ItemDust.EnumDust.SULFUR));

        //Gears
        remove("gearIron", RailcraftItems.GEAR.getStack(ItemGear.EnumGear.IRON));
        remove("gearSteel", RailcraftItems.GEAR.getStack(ItemGear.EnumGear.STEEL));
        remove("gearBronze", RailcraftItems.GEAR.getStack(ItemGear.EnumGear.BRONZE));
        remove("gearBrass", RailcraftItems.GEAR.getStack(ItemGear.EnumGear.BRASS));
        remove("gearInvar", RailcraftItems.GEAR.getStack(ItemGear.EnumGear.INVAR));
    }

    public static void removeCrusherRecepies() {;
    }

    public static void addIC2OreDict() {
        OreDictionary.registerOre("circuitBasic", MetaItems.ELECTRONIC_CIRCUIT_LV.getStackForm());
        OreDictionary.registerOre("circuitBasic", MetaItems.INTEGRATED_CIRCUIT_LV.getStackForm());
        OreDictionary.registerOre("circuitBasic", MetaItems.MICROPROCESSOR_LV.getStackForm());

        OreDictionary.registerOre("circuitAdvanced", MetaItems.INTEGRATED_CIRCUIT_HV.getStackForm());
        OreDictionary.registerOre("circuitAdvanced", MetaItems.PROCESSOR_ASSEMBLY_HV.getStackForm());
        OreDictionary.registerOre("circuitAdvanced", MetaItems.NANO_PROCESSOR_HV.getStackForm());
    }

    private static void removeRCOreDict(String name, Metal mt) {
        remove("ingot" + name, mt.getStack(Metal.Form.INGOT));
        remove("plate" + name, mt.getStack(Metal.Form.PLATE));
        remove("block" + name, mt.getStack(Metal.Form.BLOCK));
        remove("nugget" + name, mt.getStack(Metal.Form.NUGGET));
    }

    private static void remove(String name, ItemStack stack) {
        List<ItemStack> stacks = OreDictionary.getOres(name);
        stacks.removeIf(s -> OreDictionary.itemMatches(s, stack, false));
    }

    private static void removeRCRockCrusherRecipe(ItemStack input) {
        RockCrusherCrafter.INSTANCE.getRecipes().removeIf(recipe ->
                Arrays.stream(recipe.getInput().getMatchingStacks())
                        .anyMatch(s -> OreDictionary.itemMatches(s, input, false))
        );
    }

    public static void removeFromAllOreDict(ItemStack stack) {
        for (String name : OreDictionary.getOreNames()) {
            List<ItemStack> ores = OreDictionary.getOres(name);
            ores.removeIf(s -> OreDictionary.itemMatches(s, stack, false));
        }
    }
    private GTIUtil() {}
}
