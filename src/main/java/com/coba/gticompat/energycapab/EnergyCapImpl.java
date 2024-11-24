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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;


import java.util.Objects;
import java.util.function.Predicate;

public class EnergyCapImpl implements IEnergyContainer {
    private final long maxOutputVoltage = 0;
    private final long maxOutputAmperage = 0;
    protected long energyInputPerSec = 0;
    protected long energyOutputPerSec = 0;

    protected long amps = 0;
    private TileEntity te;
    private EnumFacing from;
    public EnergyCapImpl(TileEntity te, EnumFacing side) {
        this.te = te;
        this.from = side;
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing side, long voltage, long amperage) {
        IEnergySink sink = this.getSink();
        if (sink == null) {
            return 0;
        } else {
            IEnergyAcceptor acceptor = sink;
            if (!acceptor.acceptsEnergyFrom((IEnergyEmitter)null, this.from)) {
                return 0;
            } else {
                if ((int)(sink.getDemandedEnergy()) > voltage) {
                    sink.injectEnergy(this.from, (double)voltage, 1.0);
                    return 1;
                }
                return 0;
            }
        }
    }
    @Override
    public boolean inputsEnergy(EnumFacing side) {
        return this.getTile() instanceof IEnergyAcceptor;
    }
    @Override
    public boolean outputsEnergy(EnumFacing side) {
        return this.getTile() instanceof IEnergyEmitter;
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
         return (long) Objects.requireNonNull(this.getSink()).getDemandedEnergy();
    }
    @Override
    public long getEnergyStored() {
        long res = 0;
        if(this.te instanceof TileEntityStandardMachine)
            res = (long)((TileEntityStandardMachine)this.te).getEnergy();
        else if (this.te instanceof TileEntityCropmatron)
            res = (long)((TileEntityCropmatron)this.te).getEnergy();
        else if (this.te instanceof TileEntityCropHarvester)
            res = (long)((TileEntityCropHarvester)this.te).getEnergy();
        return res;
    }
    @Override
    public long getEnergyCapacity() {
        long res = this.getEnergyStored() + (long) this.getSink().getDemandedEnergy();
        return res;
    }
    @Override
    public long getOutputAmperage() {
        return this.maxOutputAmperage;
    }
    @Override
    public long getOutputVoltage() {
        return this.maxOutputVoltage;
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
        return this.energyInputPerSec;
    }
    @Override
    public long getOutputPerSec() {
        return this.energyOutputPerSec;
    }
    @Override
    public boolean isOneProbeHidden() {
        return IEnergyContainer.super.isOneProbeHidden();
    }
    private IEnergyTile getTile() {
        if (this.te.getWorld().isRemote) {
            return null;
        } else {
            IEnergyTile tile = EnergyNet.instance.getTile(this.te.getWorld(), this.te.getPos());
            return tile;
        }
    }
    private IEnergyTile getSubTile() {
        if (this.te.getWorld().isRemote) {
            return null;
        } else {
            IEnergyTile tile = EnergyNet.instance.getSubTile(this.te.getWorld(), this.te.getPos());
            return tile;
        }
    }
    private IEnergySink getSink() {
        IEnergyTile tile = this.getTile();
        return tile instanceof IEnergySink ? (IEnergySink)tile : null;
    }
}
