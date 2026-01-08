package com.coba.gticompat.mixins;

import com.coba.gticompat.energycapab.IBaseElectricCapability;
import ic2.core.block.comp.Energy;
import ic2.core.block.generator.tileentity.TileEntityBaseGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityBaseGenerator.class)
public abstract class MixinTileEntityBaseGenerator implements IBaseElectricCapability {
    @Shadow protected Energy energy;
    @Override
    public Energy getEnergy() {
        return energy;
    }
}
