package com.coba.gticompat.energycapab;

import com.coba.gticompat.api.utils.GTIUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import gregtech.api.capability.GregtechCapabilities;

import java.util.HashMap;
import java.util.Map;

public class EnergyCapEventListener {
    private static final ResourceLocation rl = new ResourceLocation("gticompat", "ic2capattach");

    public EnergyCapEventListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void capabilityInit(AttachCapabilitiesEvent<TileEntity> event) {
        final TileEntity te = event.getObject();
        if(GTIUtil.checkEntity(te)) {
            event.addCapability(rl, new ICapabilityProvider() {

                private Map<EnumFacing, EnergyCapImpl> map = new HashMap();
                public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                    return capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
                }

                public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                    return capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER ? GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(this.getCap(facing)) : null;
                }

                private EnergyCapImpl getCap(EnumFacing side) {
                    EnergyCapImpl cap = this.map.get(side);
                    if (cap == null) {
                        cap = new EnergyCapImpl(te, side);
                        this.map.put(side, cap);
                    }

                    return cap;
                }
            });
        }
    }
}
