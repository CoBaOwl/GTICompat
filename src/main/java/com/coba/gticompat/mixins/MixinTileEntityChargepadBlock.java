package com.coba.gticompat.mixins;

import gregtech.api.capability.FeCompat;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.common.ConfigHolder;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.block.wiring.TileEntityChargepadBlock;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.ref.ItemName;
import ic2.core.util.Util;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.EnumSet;

@Mixin(TileEntityChargepadBlock.class)
public abstract class MixinTileEntityChargepadBlock extends TileEntityElectricBlock {
    @Shadow
    private int updateTicker;
    @Shadow
    protected abstract int getTickRate();
    public MixinTileEntityChargepadBlock(int tier, int output, int maxStorage) {
        super(tier, output, maxStorage);
        this.energy.setDirections(EnumSet.complementOf(EnumSet.copyOf(Util.verticalFacings)), EnumSet.of(EnumFacing.DOWN));
        this.updateTicker = IC2.random.nextInt(this.getTickRate());
    }
    protected void chargeItem(ItemStack stack, int chargeFactor) {
        if (stack.getItem() != ItemName.debug_item.getInstance()) {
            double freeAmount = ElectricItem.manager.charge(stack, Double.POSITIVE_INFINITY, this.energy.getSourceTier(), true, true);
            double charge = 0.0;
            if (freeAmount >= 0.0) {
                if (freeAmount >= (double)(chargeFactor * this.getTickRate())) {
                    charge = (double)(chargeFactor * this.getTickRate());
                } else {
                    charge = freeAmount;
                }

                if (this.energy.getEnergy() < charge) {
                    charge = this.energy.getEnergy();
                }

                this.energy.useEnergy(ElectricItem.manager.charge(stack, charge, this.energy.getSourceTier(), true, false));
            }

            IElectricItem slotElectricItem = stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
            IEnergyStorage feEnergyItem = stack.getCapability(CapabilityEnergy.ENERGY, null);

            if (slotElectricItem != null && !slotElectricItem.canProvideChargeExternally())
                this.energy.useEnergy(slotElectricItem.charge(chargeFactor, energy.getSinkTier(), false, false));
            else if (ConfigHolder.compat.energy.nativeEUToFE && feEnergyItem != null) {
                if (feEnergyItem.getEnergyStored() < feEnergyItem.getMaxEnergyStored()) {
                    int energyMissing = feEnergyItem.getMaxEnergyStored() - feEnergyItem.getEnergyStored();
                    long euToCharge = FeCompat.toEu(energyMissing, ConfigHolder.compat.energy.feToEuRatio);
                    long energyToTransfer = Math.min(euToCharge, chargeFactor);
                    this.energy.useEnergy(FeCompat.insertEu(feEnergyItem, energyToTransfer));
                }
            }
        }
    }
}
