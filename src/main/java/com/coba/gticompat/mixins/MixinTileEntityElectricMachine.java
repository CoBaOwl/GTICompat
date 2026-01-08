package com.coba.gticompat.mixins;

import com.coba.gticompat.energycapab.IBaseElectricCapability;
import ic2.core.block.comp.Energy;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityElectricMachine.class)
public class MixinTileEntityElectricMachine implements IBaseElectricCapability {
    @Shadow protected Energy energy;
    @Override
    public Energy getEnergy() {
        return energy;
    }
}
