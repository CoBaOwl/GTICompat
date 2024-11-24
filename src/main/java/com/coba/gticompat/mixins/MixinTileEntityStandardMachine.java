package com.coba.gticompat.mixins;

import ic2.core.block.comp.Energy;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityStandardMachine.class, remap = false)
public abstract class MixinTileEntityStandardMachine extends TileEntityElectricMachine implements IEnergyCont {
    public MixinTileEntityStandardMachine(int maxEnergy, int tier) {
        super(maxEnergy, tier);
    }

    public MixinTileEntityStandardMachine(int maxEnergy, int tier, boolean allowRedstone) {
        super(maxEnergy, tier, allowRedstone);
    }

    @Override
    public double getCap() {
        return this.energy.getCapacity();
    }
}
