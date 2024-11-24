package com.coba.gticompat.common.metatileentity;

import com.coba.gticompat.common.metatileentity.electric.TestEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.coba.gticompat.api.utils.GTIUtil.gtiId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GTIMetatileEntity {

    public static TestEntity TEST;
    public static void init(FMLPreInitializationEvent event) {
        TEST = registerMetaTileEntity(23500, new TestEntity(gtiId("wind_generator")));
    }
}
