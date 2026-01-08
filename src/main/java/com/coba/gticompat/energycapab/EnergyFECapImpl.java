package com.coba.gticompat.energycapab;

import mods.railcraft.common.blocks.logic.RockCrusherLogic;
import mods.railcraft.common.blocks.structures.TileRockCrusher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyFECapImpl implements IEnergyStorage {
    private final TileEntity tile;
    private final EnumFacing side;

    public EnergyFECapImpl(TileEntity tile, EnumFacing side) {
        this.tile = tile;
        this.side = side;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if(!(tile instanceof TileRockCrusher)) return 0;
        if(!((TileRockCrusher)tile).getLogic(RockCrusherLogic.class).isPresent()) return 0;

        RockCrusherLogic l = ((TileRockCrusher)tile).getLogic(RockCrusherLogic.class).get();

        if (l instanceof IRockCrusherEnergy)
            return ((IRockCrusherEnergy)l).addCharge(maxReceive, simulate);
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }
    @Override
    public boolean canReceive() {
        return true;
    }
    @Override
    public boolean canExtract() {
        return false;
    }
    @Override
    public int getEnergyStored() {
        if(!(tile instanceof TileRockCrusher)) return 0;
        if(!((TileRockCrusher)tile).getLogic(RockCrusherLogic.class).isPresent()) return 0;

        RockCrusherLogic l = ((TileRockCrusher)tile).getLogic(RockCrusherLogic.class).get();

        if (l instanceof IRockCrusherEnergy)
            return ((IRockCrusherEnergy)l).getStoredCharge();
        return 0;
    }
    @Override
    public int getMaxEnergyStored() {
        if(!(tile instanceof TileRockCrusher)) return 0;
        if(!((TileRockCrusher)tile).getLogic(RockCrusherLogic.class).isPresent()) return 0;

        RockCrusherLogic l = ((TileRockCrusher)tile).getLogic(RockCrusherLogic.class).get();

        if (l instanceof IRockCrusherEnergy)
            return ((IRockCrusherEnergy)l).getMaxCharge();
        return 0;
    }
}
