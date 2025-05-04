package com.coba.gticompat;

import com.coba.gticompat.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import gregtech.api.capability.GregtechCapabilities;

@Mod(
        modid = Tags.MODID,
        version = Tags.VERSION,
        name = Tags.MODNAME,
        acceptedMinecraftVersions = "[1.12.2]",
        dependencies = "required:forge@[14.23.5.2847,);" + "required-after:gregtech@[2.8.7-beta,);" +
                "required-after:ic2@[2.8.67-ex112,);" + "required-after:groovyscript@[1.1.1,);" + "after:railcraft"
)
public class GTIcompat {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);
    @SidedProxy(modId = Tags.MODID, clientSide = "com.coba.gticompat.client.ClientProxy", serverSide = "com.coba.gticompat.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        LOGGER.info("I am " + Tags.MODNAME + " + at version " + Tags.VERSION);
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
    @EventHandler
    public void init(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
