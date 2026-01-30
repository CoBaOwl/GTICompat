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
import net.minecraftforge.fluids.FluidRegistry;
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
        if (GTICompatConfigHolder.oreDictConfig.IC2OreDictDelete) GTIUtil.removeIC2OreDict();
        if (GTICompatConfigHolder.oreDictConfig.RCOreDictDelete) GTIUtil.removeRCOreDict();
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
