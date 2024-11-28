package com.coba.gticompat.energycapab;

import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.core.block.machine.tileentity.TileEntityCropHarvester;
import ic2.core.block.machine.tileentity.TileEntityCropmatron;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import ic2.core.block.reactor.tileentity.TileEntityNuclearReactorElectric;
import ic2.core.block.wiring.TileEntityTransformer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class IC2EventListener {

    private Set<IEnergySource> tiles = Collections.newSetFromMap(new IdentityHashMap());
    public IC2EventListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void ic2LoadEvent(EnergyTileLoadEvent ev) {
        if(ev.tile instanceof IEnergySource)
            tiles.add((IEnergySource)ev.tile);
    }
    @SubscribeEvent
    public void ic2UnloadEvent(EnergyTileUnloadEvent ev) {
        if(ev.tile instanceof IEnergySource)
            tiles.remove((IEnergySource)ev.tile);
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
        for(IEnergySource t: this.tiles) {
            TileEntity te = (TileEntity)t;

            if(te.getWorld().isRemote) continue;
            List<IEnergyTile> subTiles;
            if(te instanceof TileEntityNuclearReactorElectric)
                subTiles = new ArrayList<>(((TileEntityNuclearReactorElectric)te).getSubTiles());
            else {
                subTiles = new ArrayList<>();
                subTiles.add((IEnergyTile) te);
            }
            long capacity = (long)((IEnergySource)te).getOfferedEnergy();
            for(IEnergyTile source : subTiles) {
                for(EnumFacing facing : EnumFacing.VALUES) {
                    if(capacity <= 0) continue;
                    TileEntity reciever = ((TileEntity)source).getWorld().getTileEntity(((TileEntity)source).getPos().offset(facing));
                    if(reciever==null) continue;
                    IEnergyContainer s = reciever.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                    if(s==null) continue;
                    if(!s.inputsEnergy(facing.getOpposite()) || s.getEnergyCanBeInserted() == 0) continue;
                    capacity -= convertEnergy(s, facing, te);
                }
            }
//            for(EnumFacing facing : EnumFacing.VALUES) {
//                TileEntity reciever = te.getWorld().getTileEntity(te.getPos().offset(facing));
//                if(reciever==null) continue;
//                IEnergyContainer s = reciever.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
//                if(s==null) continue;
//                if(!s.inputsEnergy(facing.getOpposite()) || s.getEnergyCanBeInserted() == 0) continue;
//                convertEnergy(s, facing.getOpposite(), te);
//            }
        }
    }

    private long convertEnergy(IEnergyContainer reciever, EnumFacing side, TileEntity source) {
        IEnergySource tesource = (IEnergySource)source;
        long transfer = 0;
        long amps = 1;
        if(GTValues.V[tesource.getSourceTier()] > (long)tesource.getOfferedEnergy()) {
            transfer = (long)tesource.getOfferedEnergy();
        } else {
            amps = Math.min(getMaxAmps(source), (long)tesource.getOfferedEnergy() / GTValues.V[tesource.getSourceTier()]);
            transfer = GTValues.V[tesource.getSourceTier()];
        }
        long accepted = reciever.acceptEnergyFromNetwork(side.getOpposite(), transfer, amps);
        long transferResult = transfer * accepted;
        tesource.drawEnergy((double) transferResult);
        return transferResult;
    }

    private long getMaxAmps(TileEntity subTile) {
        TileEntity t = subTile.getWorld().getTileEntity(subTile.getPos());
        long amps = 1;
        if ((t instanceof TileEntityTransformer) && (((TileEntityTransformer)t).getMode() == TileEntityTransformer.Mode.stepdown))
            amps = 4;
        return amps;
    }

//    @SubscribeEvent
//    public void capabilityInit(AttachCapabilitiesEvent<TileEntity> event) {
//        final TileEntity te = event.getObject();
//        if((te instanceof IEnergySource)) event.addCapability(new ResourceLocation("gticompat", "ic2capattach"), new ICapabilityProvider() {
//            private Map<EnumFacing, i2cGeneratorCap> map = new HashMap();
//            public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
//                return capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
//            }
//            public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
//                return capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER ? GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(this.getCap(facing)) : null;
//            }
//            private i2cGeneratorCap getCap(EnumFacing side) {
//                i2cGeneratorCap cap = (i2cGeneratorCap)this.map.get(side);
//                if (cap == null) {
//                    cap = new i2cGeneratorCap(te, side);
//                    this.map.put(side, cap);
//                }
//
//                return cap;
//            }
//        });
//    }
//
//    public class i2cGeneratorCap implements IEnergyContainer {
//        TileEntity te;
//        EnumFacing side;
//        public i2cGeneratorCap(TileEntity te, EnumFacing side) {
//            this.te = te;
//            this.side = side;
//        }
//        @Override
//        public long acceptEnergyFromNetwork(EnumFacing side, long voltage, long amperage) {
//            return 0;
//        }
//
//        @Override
//        public boolean inputsEnergy(EnumFacing side) {
//            return false;
//        }
//
//        @Override
//        public boolean outputsEnergy(EnumFacing side) {
//            return IEnergyContainer.super.outputsEnergy(side);
//        }
//
//        @Override
//        public long changeEnergy(long differenceAmount) {
//            return 0;
//        }
//
//        @Override
//        public long addEnergy(long energyToAdd) {
//            return IEnergyContainer.super.addEnergy(energyToAdd);
//        }
//
//        @Override
//        public long removeEnergy(long energyToRemove) {
//            return IEnergyContainer.super.removeEnergy(energyToRemove);
//        }
//
//        @Override
//        public long getEnergyCanBeInserted() {
//            return IEnergyContainer.super.getEnergyCanBeInserted();
//        }
//
//        @Override
//        public long getEnergyStored() {
//            return 0;
//        }
//
//        @Override
//        public long getEnergyCapacity() {
//            return 0;
//        }
//
//        @Override
//        public long getOutputAmperage() {
//            return IEnergyContainer.super.getOutputAmperage();
//        }
//
//        @Override
//        public long getOutputVoltage() {
//            return IEnergyContainer.super.getOutputVoltage();
//        }
//
//        @Override
//        public long getInputAmperage() {
//            return 0;
//        }
//
//        @Override
//        public long getInputVoltage() {
//            return 0;
//        }
//
//        @Override
//        public long getInputPerSec() {
//            return IEnergyContainer.super.getInputPerSec();
//        }
//
//        @Override
//        public long getOutputPerSec() {
//            return IEnergyContainer.super.getOutputPerSec();
//        }
//
//        @Override
//        public boolean isOneProbeHidden() {
//            return IEnergyContainer.super.isOneProbeHidden();
//        }
//    }

}
