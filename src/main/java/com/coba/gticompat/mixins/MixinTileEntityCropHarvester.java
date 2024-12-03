package com.coba.gticompat.mixins;

import ic2.api.upgrade.IUpgradableBlock;
import ic2.core.IHasGui;
import ic2.core.block.invslot.InvSlotUpgrade;
import ic2.core.block.machine.tileentity.TileEntityCropHarvester;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.crop.TileEntityCrop;
import ic2.core.util.StackUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

import static com.coba.gticompat.common.GTICompatConfigHolder.ic2MachinesConfig;

@Mixin(TileEntityCropHarvester.class)
public abstract class MixinTileEntityCropHarvester extends TileEntityElectricMachine implements IHasGui, IUpgradableBlock {
    public long tick = ic2MachinesConfig.cropTick;
    public int radius = ic2MachinesConfig.cropRadius;
    public int scanX = -radius;
    public int scanZ = -radius;
    public int scanY = -1;
    @Final
    @Shadow
    public final InvSlotUpgrade upgradeSlot = new InvSlotUpgrade(this, "upgrade", 4);;

    public MixinTileEntityCropHarvester() {
        super(10000, 1, false);
    }

    @Shadow
    protected abstract boolean isInvFull();

    protected void updateEntityServer() {
        super.updateEntityServer();
        this.upgradeSlot.tick();
        if (this.world.getTotalWorldTime() % ic2MachinesConfig.cropTick == 0L && this.energy.getEnergy() >= 21.0) {
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
        World world = this.getWorld();
        TileEntity tileEntity = world.getTileEntity(this.pos.add(this.scanX, this.scanY, this.scanZ));
        if (tileEntity instanceof TileEntityCrop && !this.isInvFull()) {
            TileEntityCrop crop = (TileEntityCrop)tileEntity;
            if (crop.getCrop() != null) {
                List<ItemStack> drops = null;
                if (crop.getCurrentSize() == crop.getCrop().getOptimalHarvestSize(crop)) {
                    drops = crop.performHarvest();
                } else if (crop.getCurrentSize() == crop.getCrop().getMaxSize()) {
                    drops = crop.performHarvest();
                }

                if (drops != null) {
                    drops.forEach((drop) -> {
                        if (StackUtil.putInInventory(this, EnumFacing.WEST, drop, true) == 0) {
                            StackUtil.dropAsEntity(world, this.pos, drop);
                        } else {
                            StackUtil.putInInventory(this, EnumFacing.WEST, drop, false);
                        }

                        this.energy.useEnergy(20.0);
                    });
                }
            }
        }

    }

}
