package com.coba.gticompat.energycapab;

import mods.railcraft.common.blocks.logic.RockCrusherLogic;
import mods.railcraft.common.blocks.structures.TileRockCrusher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class EnergyFECapEventListener {
    private static final ResourceLocation rl = new ResourceLocation("gticompat", "ic2capfeattach");

    public EnergyFECapEventListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void capabilityInit(AttachCapabilitiesEvent<TileEntity> event) {
        final TileEntity te = event.getObject();
        if((te instanceof TileRockCrusher)) {
            event.addCapability(rl, new ICapabilityProvider() {

                private Map<EnumFacing, EnergyFECapImpl> mapFE = new HashMap();
                public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                    return capability == CapabilityEnergy.ENERGY;
                }

                public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                    return capability == CapabilityEnergy.ENERGY ? CapabilityEnergy.ENERGY.cast(getFECap(facing)) : null;
                }

                private EnergyFECapImpl getFECap(EnumFacing side) {
                    EnergyFECapImpl cap = this.mapFE.get(side);
                    if (cap == null) {
                        cap = new EnergyFECapImpl(te, side);
                        this.mapFE.put(side, cap);
                    }

                    return cap;
                }
            });
        }
    }
}
