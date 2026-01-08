package com.coba.gticompat.energycapab;

import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import ic2.api.item.ElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.function.BiConsumer;

class EnergyItemCapImpl implements IElectricItem, ICapabilityProvider {
    private ItemStack item;
    public EnergyItemCapImpl(ItemStack i) {
        this.item = i;
    }
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM;
    }
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return this.hasCapability(capability, facing) ? GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM.cast(this) : null;
    }
    @Override
    public boolean canProvideChargeExternally() {
        return false;
    }
    @Override
    public boolean chargeable() {
        return true;
    }
    @Override
    public void addChargeListener(BiConsumer<ItemStack, Long> chargeListener) {
    }
    @Override
    public long charge(long amount, int chargerTier, boolean ignoreTransferLimit, boolean simulate) {
        return (long)ElectricItem.manager.charge(this.item, amount, Integer.MAX_VALUE, true, simulate);
    }
    @Override
    public long discharge(long amount, int dischargerTier, boolean ignoreTransferLimit, boolean externally, boolean simulate) {
        return 0;
    }
    @Override
    public long getTransferLimit() {
        return (long)(ElectricItem.manager.getMaxCharge(this.item) - ElectricItem.manager.getCharge(this.item));
    }
    @Override
    public long getMaxCharge() {
        return (long)(ElectricItem.manager.getMaxCharge(this.item));
    }
    @Override
    public long getCharge() {
        return (long)(ElectricItem.manager.getCharge(this.item));
    }
    @Override
    public int getTier() {
        return GTValues.LV;
    }
}