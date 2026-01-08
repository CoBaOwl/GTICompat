package com.coba.gticompat.energycapab;

public interface IRockCrusherEnergy {
    int addCharge(double chr, boolean simulate);
    int getMaxCharge();
    int getStoredCharge();
}
