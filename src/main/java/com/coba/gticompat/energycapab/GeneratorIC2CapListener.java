package com.coba.gticompat.energycapab;

import gregtech.api.capability.GregtechCapabilities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class GeneratorIC2CapListener {
    private static final ResourceLocation rl = new ResourceLocation("gticompat", "ic2capgenattach");

    public GeneratorIC2CapListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void capabilityInit(AttachCapabilitiesEvent<TileEntity> event) {
        final TileEntity te = event.getObject();
        if(te instanceof IBaseElectricCapability) {
            event.addCapability(rl, new ICapabilityProvider() {

                private Map<EnumFacing, GeneratorIC2CapImpl> map = new HashMap();
                public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                    return capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
                }

                public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                    return capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER ? GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(this.getCap(facing)) : null;
                }

                private GeneratorIC2CapImpl getCap(EnumFacing side) {
                    GeneratorIC2CapImpl cap = this.map.get(side);
                    if (cap == null) {
                        cap = new GeneratorIC2CapImpl(te, side);
                        this.map.put(side, cap);
                    }

                    return cap;
                }

            });
        }
    }
}
