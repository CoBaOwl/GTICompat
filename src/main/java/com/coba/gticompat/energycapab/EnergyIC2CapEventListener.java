package com.coba.gticompat.energycapab;

import ic2.core.block.generator.tileentity.TileEntityGenerator;
import ic2.core.block.machine.tileentity.*;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.block.wiring.TileEntityTransformer;
import mods.railcraft.common.blocks.machine.manipulator.TileIC2Loader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import gregtech.api.capability.GregtechCapabilities;

import java.util.HashMap;
import java.util.Map;

public class EnergyIC2CapEventListener {
    private static final ResourceLocation rl = new ResourceLocation("gticompat", "ic2capattach");

    public EnergyIC2CapEventListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void capabilityInit(AttachCapabilitiesEvent<TileEntity> event) {
        final TileEntity te = event.getObject();
        if((te instanceof IBaseElectricCapability) ||  ((Loader.isModLoaded("railcraft")) && (te instanceof TileIC2Loader))) {
            event.addCapability(rl, new ICapabilityProvider() {

                private Map<EnumFacing, EnergyIC2CapImpl> map = new HashMap();
                public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                    return capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
                }

                public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                    return capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER ? GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(this.getCap(facing)) : null;
                }

                private EnergyIC2CapImpl getCap(EnumFacing side) {
                    EnergyIC2CapImpl cap = this.map.get(side);
                    if (cap == null) {
                        cap = new EnergyIC2CapImpl(te, side);
                        this.map.put(side, cap);
                    }

                    return cap;
                }
            });
        }
    }
}
