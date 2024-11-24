package com.coba.gticompat.common;

import com.coba.gticompat.Tags;
import com.coba.gticompat.energycapab.EnergyCapEventListener;
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
        GTIMetatileEntity.init(event);
    }
    @SubscribeEvent
    public void init(FMLInitializationEvent event) {
        new EnergyCapEventListener();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
//        event.getRegistry().registerAll(Registry.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
//        event.getRegistry().registerAll(Registry.BLOCKS.toArray(new Block[0]));
    }
    public void postInit() {
//        Registry.recipes();
    }

    public void registerItemRender(Item item, int meta, String id){}
}
