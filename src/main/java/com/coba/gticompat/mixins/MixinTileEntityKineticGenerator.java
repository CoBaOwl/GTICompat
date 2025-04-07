package com.coba.gticompat.mixins;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import ic2.core.block.generator.tileentity.TileEntityConversionGenerator;
import ic2.core.block.generator.tileentity.TileEntityKineticGenerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityKineticGenerator.class)
public abstract class MixinTileEntityKineticGenerator  extends TileEntityConversionGenerator {
    @Shadow protected abstract double getMultiplier();

    protected void updateEntityServer() {
        super.updateEntityServer();
        int eu = (int) (getEnergyAvailable() * getMultiplier());
        if(eu == 0) return;
        EnumFacing energyFace = getFacing().getOpposite();
        TileEntity reciever = this.getWorld().getTileEntity((this).getPos().offset(energyFace));
        if (reciever == null) return;
        IEnergyContainer s = reciever.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, energyFace.getOpposite());
        if (s == null || !s.inputsEnergy(energyFace.getOpposite()) || s.getEnergyCanBeInserted() == 0) return;
        long accepted = s.acceptEnergyFromNetwork(energyFace.getOpposite(), eu, 1);
        drawEnergyAvailable((int)(accepted * eu));
        drawEnergy((accepted * eu));
    }
}
