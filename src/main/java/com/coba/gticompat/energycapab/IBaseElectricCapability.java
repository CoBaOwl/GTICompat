package com.coba.gticompat.energycapab;

import ic2.core.block.comp.Energy;

public interface IBaseElectricCapability {
    default Energy getEnergy() {
        return null;
    }
    default int getMaxEnergy() {
        return 0;
    }
    default int getCurrEnergy() {
        return 0;
    }
}
