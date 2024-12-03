package com.coba.gticompat.mixins;

import gregtech.common.items.MetaItems;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.core.IHasGui;
import ic2.core.block.invslot.*;
import ic2.core.block.machine.tileentity.TileEntityCropmatron;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.crop.TileEntityCrop;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidTank;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static com.coba.gticompat.common.GTICompatConfigHolder.ic2MachinesConfig;

@Mixin(TileEntityCropmatron.class)
public abstract class MixinTileEntityCropmatron extends TileEntityElectricMachine implements IHasGui, IUpgradableBlock {
    public long tick = ic2MachinesConfig.cropTick;
    public int radius = ic2MachinesConfig.cropRadius;
    public int scanX = -radius;
    public int scanZ = -radius;
    public int scanY = -1;
    @Final
    @Shadow
    public InvSlotConsumable fertilizerSlot = new InvSlotConsumableItemStack(this, "fertilizer", 7, MetaItems.FERTILIZER.getStackForm());
    @Final
    @Shadow
    protected FluidTank waterTank;
    @Final
    @Shadow
    protected FluidTank exTank;

    public MixinTileEntityCropmatron() {
        super(10000, 1, false);
    }

    @Shadow
    public abstract FluidTank getWaterTank();
    @Shadow
    protected abstract boolean tryHydrateFarmland(final BlockPos pos);
    @Shadow
    public abstract FluidTank getExTank();
    @Shadow
    public final InvSlotUpgrade upgradeSlot = new InvSlotUpgrade(this, "upgrade", 4);;
    @Shadow
    public final InvSlotOutput wasseroutputSlot = new InvSlotOutput(this, "wasseroutputSlot", 1);
    @Shadow
    public final InvSlotOutput exOutputSlot = new InvSlotOutput(this, "exOutputSlot", 1);
    @Shadow
    public final InvSlotConsumableLiquidByTank wasserinputSlot = new InvSlotConsumableLiquidByTank(this, "wasserinputSlot", InvSlot.Access.I, 1, InvSlot.InvSide.TOP, InvSlotConsumableLiquid.OpType.Drain, this.waterTank);
    @Shadow
    public final InvSlotConsumableLiquidByTank exInputSlot = new InvSlotConsumableLiquidByTank(this, "exInputSlot", InvSlot.Access.I, 1, InvSlot.InvSide.TOP, InvSlotConsumableLiquid.OpType.Drain, this.exTank);
    protected void updateEntityServer() {
        super.updateEntityServer();
        this.upgradeSlot.tick();
        this.wasserinputSlot.processIntoTank(this.waterTank, this.wasseroutputSlot);
        this.exInputSlot.processIntoTank(this.exTank, this.exOutputSlot);
        this.fertilizerSlot.organize();
        if (this.world.getTotalWorldTime() % ic2MachinesConfig.cropTick == 0L && this.energy.getEnergy() >= 31.0) {
            this.scan();
        }
    }
    public void scan() {
        ++this.scanX;
        if (this.scanX > radius) {
            this.scanX = -radius;
            ++this.scanZ;
            if (this.scanZ > radius) {
                this.scanZ = -radius;
                ++this.scanY;
                if (this.scanY > 1) {
                    this.scanY = -1;
                }
            }
        }
        this.energy.useEnergy(1.0);
        final BlockPos scan = this.pos.add(this.scanX, this.scanY, this.scanZ);
        final TileEntity te = this.getWorld().getTileEntity(scan);
        if (te instanceof TileEntityCrop) {
            final TileEntityCrop crop = (TileEntityCrop)te;
            if (!this.fertilizerSlot.isEmpty() && this.fertilizerSlot.consume(1, true, false) != null && crop.applyFertilizer(false)) {
                this.energy.useEnergy(10.0);
                this.fertilizerSlot.consume(1);
            }
            if (this.waterTank.getFluidAmount() > 0 && crop.applyHydration(this.getWaterTank())) {
                this.energy.useEnergy(10.0);
            }
            if (this.exTank.getFluidAmount() > 0 && crop.applyWeedEx(this.getExTank(), false)) {
                this.energy.useEnergy(10.0);
            }
        }
        else if (this.waterTank.getFluidAmount() > 0 && this.tryHydrateFarmland(scan)) {
            this.energy.useEnergy(10.0);
        }
    }
}
