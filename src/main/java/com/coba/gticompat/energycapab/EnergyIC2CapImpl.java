package com.coba.gticompat.energycapab;

import gregtech.api.capability.IEnergyContainer;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import ic2.core.block.machine.tileentity.TileEntityCropHarvester;
import ic2.core.block.machine.tileentity.TileEntityCropmatron;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.block.wiring.TileEntityTransformer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;


import java.util.Objects;

public class EnergyIC2CapImpl implements IEnergyContainer {
    private TileEntity te;
    private EnumFacing from;
    private long backup = 0;
    public EnergyIC2CapImpl(TileEntity te, EnumFacing side) {
        this.te = te;
        this.from = side;
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing side, long voltage, long amperage) {
        IEnergySink sink = this.getSink();
        if (sink == null) return 0;

        IEnergyAcceptor acceptor = sink;
        if (!acceptor.acceptsEnergyFrom(null, this.from)) return 0;

        if(((IBaseElectricCapability)te).getEnergy() != null && ((IBaseElectricCapability)te).getEnergy().getCapacity() <= ((IBaseElectricCapability)te).getEnergy().getEnergy())
            return 0;
        else if (((IBaseElectricCapability)te).getEnergy() == null && ((IBaseElectricCapability) te).getMaxEnergy() <= ((IBaseElectricCapability) te).getCurrEnergy())
            return 0;

        sink.injectEnergy(this.from, (double)voltage, (double)voltage);
        return 1;
    }
    @Override
    public boolean inputsEnergy(EnumFacing side) {
        return this.getTile() instanceof IEnergyAcceptor;
    }
    @Override
    public long changeEnergy(long differenceAmount) {
        return 0;
    }
    @Override
    public long addEnergy(long energyToAdd) {
        return IEnergyContainer.super.addEnergy(energyToAdd);
    }
    @Override
    public long removeEnergy(long energyToRemove) {
        return IEnergyContainer.super.removeEnergy(energyToRemove);
    }
    @Override
    public long getEnergyCanBeInserted() {
        if (((IBaseElectricCapability)te).getEnergy() != null)
            return (long) (((IBaseElectricCapability)te).getEnergy().getCapacity() - ((IBaseElectricCapability)te).getEnergy().getEnergy());
        return (long) (((IBaseElectricCapability)te).getMaxEnergy() - ((IBaseElectricCapability)te).getCurrEnergy());

    }
    @Override
    public long getEnergyStored() {
        return (long)(((IBaseElectricCapability)te).getEnergy() != null ? ((IBaseElectricCapability)te).getEnergy().getEnergy() : ((IBaseElectricCapability)te).getCurrEnergy());
    }
    @Override
    public long getEnergyCapacity() {
        return (long)(((IBaseElectricCapability)te).getEnergy() != null ? ((IBaseElectricCapability)te).getEnergy().getCapacity() : ((IBaseElectricCapability)te).getMaxEnergy());
    }
    @Override
    public long getOutputAmperage() {
        return 0;
    }
    @Override
    public long getOutputVoltage() {
        return 0;
    }
    @Override
    public long getInputAmperage() {
        return 0L;
    }
    @Override
    public long getInputVoltage() {
        return 0L;
    }
    @Override
    public long getInputPerSec() {
        return 0;
    }
    @Override
    public long getOutputPerSec() {
        return 0;
    }
    @Override
    public boolean isOneProbeHidden() {
        if (this.te instanceof TileEntityTransformer) return true;
        return IEnergyContainer.super.isOneProbeHidden();
    }
    private IEnergyTile getTile() {
        if (this.te.getWorld().isRemote)
            return null;
        else
            return EnergyNet.instance.getTile(this.te.getWorld(), this.te.getPos());
    }
    private IEnergySink getSink() {
        IEnergyTile tile = this.getTile();
        return tile instanceof IEnergySink ? (IEnergySink)tile : null;
    }
}
