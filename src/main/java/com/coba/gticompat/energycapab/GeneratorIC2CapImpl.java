package com.coba.gticompat.energycapab;

import gregtech.api.capability.IEnergyContainer;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class GeneratorIC2CapImpl implements IEnergyContainer {
    private TileEntity te;
    private EnumFacing from;

    public GeneratorIC2CapImpl(TileEntity te, EnumFacing side) {
        this.te = te;
        this.from = side;
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing side, long voltage, long amperage) {
        return 0;
    }

    @Override
    public boolean inputsEnergy(EnumFacing side) {
        return false;
    }

    @Override
    public boolean outputsEnergy(EnumFacing side) {
        return true;
    }

    @Override
    public long changeEnergy(long differenceAmount) {
        return 0;
    }

    @Override
    public long addEnergy(long energyToAdd) {
        return 0;
    }

    @Override
    public long removeEnergy(long energyToRemove) {
        return 0;
    }

    @Override
    public long getEnergyCanBeInserted() {
         return 0;
    }

    @Override
    public long getEnergyStored() {
        if (((IBaseElectricCapability)te).getEnergy() != null) {
            return (int)((IBaseElectricCapability)te).getEnergy().getEnergy();
        }
        return ((IBaseElectricCapability)te).getCurrEnergy();
    }

    @Override
    public long getEnergyCapacity() {
        if (((IBaseElectricCapability)te).getEnergy() != null) {
            return (int)((IBaseElectricCapability)te).getEnergy().getCapacity();
        }
        return ((IBaseElectricCapability)te).getMaxEnergy();
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
