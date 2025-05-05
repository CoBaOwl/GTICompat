package com.coba.gticompat.api.jei;

import ic2.core.item.type.DustResourceType;
import ic2.core.item.type.IngotResourceType;
import ic2.core.item.type.NuclearResourceType;
import ic2.core.item.type.OreResourceType;
import ic2.core.ref.ItemName;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import com.coba.gticompat.api.utils.GTIUtil;

@mezz.jei.api.JEIPlugin
public class JEI_Plugin implements IModPlugin {
    public @interface JEIPlugin {}

    public JEI_Plugin(){}


    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
    }

    /**
     * Register special ingredients, beyond the basic ItemStack and FluidStack.
     *
     * @since JEI 3.11.0
     */
    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
    }

    /**
     * Register the categories handled by this plugin.
     * These are registered before recipes so they can be checked for validity.
     *
     * @since JEI 4.5.0
     */
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
    }

    /**
     * Register this mod plugin with the mod registry.
     */
    @Override
    public void register(IModRegistry registry) {
        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.copper, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.tin, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.bronze, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.diamond, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.energium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.gold, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.iron, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.lapis, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.lead, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.lithium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.obsidian, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.silver, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.sulfur, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.emerald, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_bronze, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_copper, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_gold, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_iron, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_lapis, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_lead, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_lithium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_obsidian, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_silver, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_sulfur, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_tin, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_emerald, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.dust, DustResourceType.small_diamond, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.uranium_235, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.uranium_238, 9));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.plutonium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.small_plutonium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.small_uranium_235, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.small_uranium_238, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.depleted_uranium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.depleted_dual_uranium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.depleted_quad_uranium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.depleted_mox, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.depleted_dual_mox, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.nuclear, NuclearResourceType.depleted_quad_mox, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.crushed, OreResourceType.copper, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.crushed, OreResourceType.gold, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.crushed, OreResourceType.iron, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.crushed, OreResourceType.lead, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.crushed, OreResourceType.silver, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.crushed, OreResourceType.tin, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.crushed, OreResourceType.uranium, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.purified, OreResourceType.copper, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.purified, OreResourceType.gold, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.purified, OreResourceType.iron, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.purified, OreResourceType.lead, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.purified, OreResourceType.silver, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.purified, OreResourceType.tin, 1));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.purified, OreResourceType.uranium, 1));
        blacklist.addIngredientToBlacklist(ItemName.uranium_fuel_rod.getItemStack());
        blacklist.addIngredientToBlacklist(ItemName.dual_uranium_fuel_rod.getItemStack());
        blacklist.addIngredientToBlacklist(ItemName.quad_uranium_fuel_rod.getItemStack());
        blacklist.addIngredientToBlacklist(ItemName.mox_fuel_rod.getItemStack());
        blacklist.addIngredientToBlacklist(ItemName.dual_mox_fuel_rod.getItemStack());
        blacklist.addIngredientToBlacklist(ItemName.quad_mox_fuel_rod.getItemStack());
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.ingot, IngotResourceType.bronze, 9));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.ingot, IngotResourceType.copper, 9));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.ingot, IngotResourceType.lead, 9));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.ingot, IngotResourceType.silver, 9));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.ingot, IngotResourceType.steel, 9));
        blacklist.addIngredientToBlacklist(GTIUtil.getIC2ItemStack(ItemName.ingot, IngotResourceType.tin, 9));
    }

    /**
     * Called when jei's runtime features are available, after all mods have registered.
     *
     * @since JEI 2.23.0
     */
    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

    }
}