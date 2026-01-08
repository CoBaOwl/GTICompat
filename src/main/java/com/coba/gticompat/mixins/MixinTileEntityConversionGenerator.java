package com.coba.gticompat.mixins;

import com.coba.gticompat.energycapab.IBaseElectricCapability;
import ic2.core.block.comp.Energy;
import ic2.core.block.generator.tileentity.TileEntityConversionGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityConversionGenerator.class)
public class MixinTileEntityConversionGenerator implements IBaseElectricCapability {
    @Shadow private double maxProduction;
    @Shadow private double production;
    @Override
    public Energy getEnergy() {
        return null;
    }

    @Override
    public int getMaxEnergy() {
        return (int)maxProduction;
    }

    @Override
    public int getCurrEnergy() {
        return (int)production;
    }
}
