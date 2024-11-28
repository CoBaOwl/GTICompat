package com.coba.gticompat.common;

import com.coba.gticompat.Tags;
import com.coba.gticompat.energycapab.EnergyCapEventListener;
import com.coba.gticompat.energycapab.IC2EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.coba.gticompat.common.metatileentity.GTIMetatileEntity;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
    }
    @SubscribeEvent
    public void init(FMLInitializationEvent event) {
        new EnergyCapEventListener();
        new IC2EventListener();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    }
    public void postInit() {
    }

    public void registerItemRender(Item item, int meta, String id){}
}
