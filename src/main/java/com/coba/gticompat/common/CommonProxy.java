package com.coba.gticompat.common;

import com.coba.gticompat.Tags;
import com.coba.gticompat.api.utils.GTIUtil;
import com.coba.gticompat.energycapab.*;
import com.coba.gticompat.items.Items;
import com.coba.gticompat.items.Materials;
import com.coba.gticompat.items.RecepiesHandler;
import gregtech.api.unification.material.event.MaterialEvent;
import ic2.core.item.type.*;
import ic2.core.ref.ItemName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Items.initItems();
    }
    @SubscribeEvent
    public void init(FMLInitializationEvent event) {
        new EnergyIC2CapEventListener();
        new EnergyFECapEventListener();
        new IC2EventListener();
        new GeneratorIC2CapListener();
        new EnergyItemCapabilityListener();
        GTIUtil.addIC2OreDict();

//        removeItemFromOreDict("plateBronze", ItemName.plate.getItemStack(PlateResourceType.bronze));
//        removeItemFromOreDict("plateCopper", ItemName.plate.getItemStack(PlateResourceType.copper));
//        removeItemFromOreDict("plateCopper", ItemName.plate.getItemStack(PlateResourceType.copper));
//        removeItemFromOreDict("plateGold", ItemName.plate.getItemStack(PlateResourceType.gold));
//        removeItemFromOreDict("plateIron", ItemName.plate.getItemStack(PlateResourceType.iron));
//        removeItemFromOreDict("plateLead", ItemName.plate.getItemStack(PlateResourceType.lead));
//        removeItemFromOreDict("plateObsidian", ItemName.plate.getItemStack(PlateResourceType.obsidian));
//        removeItemFromOreDict("plateSteel", ItemName.plate.getItemStack(PlateResourceType.steel));
//        removeItemFromOreDict("plateTin", ItemName.plate.getItemStack(PlateResourceType.tin));
//        removeItemFromOreDict("plateLapis", ItemName.plate.getItemStack(PlateResourceType.lapis));
//
//        removeItemFromOreDict("ingotBronze", ItemName.ingot.getItemStack(IngotResourceType.bronze));
//        removeItemFromOreDict("ingotCopper", ItemName.ingot.getItemStack(IngotResourceType.copper));
//        removeItemFromOreDict("ingotLead", ItemName.ingot.getItemStack(IngotResourceType.lead));
//        removeItemFromOreDict("ingotSilver", ItemName.ingot.getItemStack(IngotResourceType.silver));
//        removeItemFromOreDict("ingotSteel", ItemName.ingot.getItemStack(IngotResourceType.steel));
//        removeItemFromOreDict("ingotTin", ItemName.ingot.getItemStack(IngotResourceType.tin));
//
//        removeItemFromOreDict("dustTin", ItemName.dust.getItemStack(DustResourceType.tin));
//        removeItemFromOreDict("dustBronze", ItemName.dust.getItemStack(DustResourceType.bronze));
//        removeItemFromOreDict("dustClay", ItemName.dust.getItemStack(DustResourceType.clay));
//        removeItemFromOreDict("dustCoal", ItemName.dust.getItemStack(DustResourceType.coal));
//        removeItemFromOreDict("dustCopper", ItemName.dust.getItemStack(DustResourceType.copper));
//        removeItemFromOreDict("dustDiamond", ItemName.dust.getItemStack(DustResourceType.diamond));
//        removeItemFromOreDict("dustGold", ItemName.dust.getItemStack(DustResourceType.gold));
//        removeItemFromOreDict("dustIron", ItemName.dust.getItemStack(DustResourceType.iron));
//        removeItemFromOreDict("dustLapis", ItemName.dust.getItemStack(DustResourceType.lapis));
//        removeItemFromOreDict("dustLead", ItemName.dust.getItemStack(DustResourceType.lead));
//        removeItemFromOreDict("dustLithium", ItemName.dust.getItemStack(DustResourceType.lithium));
//        removeItemFromOreDict("dustObsidian", ItemName.dust.getItemStack(DustResourceType.obsidian));
//        removeItemFromOreDict("dustSiliconDioxide", ItemName.dust.getItemStack(DustResourceType.silicon_dioxide));
//        removeItemFromOreDict("dustSilver", ItemName.dust.getItemStack(DustResourceType.silver));
//        removeItemFromOreDict("dustStone", ItemName.dust.getItemStack(DustResourceType.stone));
//        removeItemFromOreDict("dustSulfur", ItemName.dust.getItemStack(DustResourceType.sulfur));
//        removeItemFromOreDict("dustNetherrack", ItemName.dust.getItemStack(DustResourceType.netherrack));
//        removeItemFromOreDict("dustEnderPearl", ItemName.dust.getItemStack(DustResourceType.ender_pearl));
//        removeItemFromOreDict("dustEnderEye", ItemName.dust.getItemStack(DustResourceType.ender_eye));
//
//        removeItemFromOreDict("crushedPurifiedCopper", ItemName.purified.getItemStack(OreResourceType.copper));
//        removeItemFromOreDict("crushedCopper", ItemName.crushed.getItemStack(OreResourceType.copper));
//        removeItemFromOreDict("crushedPurifiedGold", ItemName.purified.getItemStack(OreResourceType.gold));
//        removeItemFromOreDict("crushedGold", ItemName.crushed.getItemStack(OreResourceType.gold));
//        removeItemFromOreDict("crushedPurifiedIron", ItemName.purified.getItemStack(OreResourceType.iron));
//        removeItemFromOreDict("crushedIron", ItemName.crushed.getItemStack(OreResourceType.iron));
//        removeItemFromOreDict("crushedPurifiedLead", ItemName.purified.getItemStack(OreResourceType.lead));
//        removeItemFromOreDict("crushedLead", ItemName.crushed.getItemStack(OreResourceType.lead));
//        removeItemFromOreDict("crushedPurifiedSilver", ItemName.purified.getItemStack(OreResourceType.silver));
//        removeItemFromOreDict("crushedSilver", ItemName.crushed.getItemStack(OreResourceType.silver));
//        removeItemFromOreDict("crushedPurifiedTin", ItemName.purified.getItemStack(OreResourceType.tin));
//        removeItemFromOreDict("crushedTin", ItemName.crushed.getItemStack(OreResourceType.tin));
//        removeItemFromOreDict("crushedPurifiedUranium", ItemName.purified.getItemStack(OreResourceType.uranium));
//        removeItemFromOreDict("crushedUranium", ItemName.crushed.getItemStack(OreResourceType.uranium));
    }

    public static void removeItemFromOreDict(String oreName, ItemStack itemStack) {
        List<ItemStack> oreList = OreDictionary.getOres(oreName);
        oreList.removeIf(item -> ItemStack.areItemsEqual(item, itemStack));
    }
    public void registerItemRender(Item item, int meta, String id){}
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Item[] items = Items.ITEMS.toArray(new Item[0]);
        event.getRegistry().registerAll(items);
    }

    public void postInit(FMLPostInitializationEvent event) {
        GTIUtil.removeIC2MecRecepies();
        GTIUtil.removeIC2OreDict();
        GTIUtil.removeRCOreDict();
        GTIUtil.removeCrusherRecepies();
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GTIUtil.removeIC2Items();
        GTIUtil.removeIC2Mec();

        RecepiesHandler.initRecipes();
    }
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerMaterials(MaterialEvent event) {
        Materials.registryMaterials();
    }
}
