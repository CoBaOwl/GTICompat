package com.coba.gticompat.mixins;

import com.coba.gticompat.energycapab.IBaseElectricCapability;
import mods.railcraft.common.blocks.machine.manipulator.TileIC2Manipulator;
import mods.railcraft.common.util.charge.Battery;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileIC2Manipulator.class)
public class MixinTileIC2Manipulator implements IBaseElectricCapability {
    @Shadow protected Battery battery;
    @Override
    public int getMaxEnergy() {
        return (int)battery.getCapacity();
    }
    @Override
    public int getCurrEnergy() {
        return (int)battery.getCharge();
    }
}
