package com.coba.gticompat.mixins;

import com.coba.gticompat.energycapab.IRockCrusherEnergy;
import mods.railcraft.common.blocks.logic.RockCrusherLogic;
import mods.railcraft.common.core.RailcraftConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RockCrusherLogic.class)
public abstract class MixinRockCrusherLogic implements IRockCrusherEnergy {
    @Shadow private double storedCharge;
    @Shadow  private static double MAX_STORED_CHARGE;

    @Override
    public int addCharge(double chr, boolean simulate) {
        double result = Math.min(chr  * RailcraftConstants.FE_EU_RATIO, MAX_STORED_CHARGE - storedCharge);
        if (!simulate)
            storedCharge += result;
        return (int)(result / RailcraftConstants.FE_EU_RATIO);
    }

    @Override
    public int getMaxCharge() {
        return (int)(MAX_STORED_CHARGE / RailcraftConstants.FE_EU_RATIO);
    }
    @Override
    public int getStoredCharge() {
        return (int)(storedCharge / RailcraftConstants.FE_EU_RATIO);
    }

}
