package com.coba.gticompat.mixins;

import gregtech.common.items.MetaItems;
import ic2.api.crops.BaseSeed;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import ic2.core.crop.TileEntityCrop;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityCrop.class)
public abstract class MixinTileEntityCrop {
    @Shadow
    public boolean dirty = true;
    @Shadow
    private CropCard crop = null;
    @Shadow
    private boolean crossingBase;
    @Shadow
    private byte currentSize;
    @Shadow
    public abstract boolean applyFertilizer(boolean manual);
    @Shadow
    public abstract boolean applyHydration(IFluidHandler handler);
    @Shadow
    public abstract boolean applyWeedEx(IFluidHandler handler, boolean manual);
    @Shadow
    public abstract void reset();
    @Shadow
    public abstract void setCrop(CropCard crop);
    @Shadow
    private byte statGain;
    @Shadow
    private byte statGrowth;
    @Shadow
    private byte statResistance;
    public boolean rightClick(final EntityPlayer player, final EnumHand hand) {
        final ItemStack heldItem = StackUtil.get(player, hand);
        final boolean creative = player.capabilities.isCreativeMode;
        if (!StackUtil.isEmpty(heldItem)) {
            if (this.crop == null && !this.crossingBase && heldItem.getItem() == ItemName.crop_stick.getInstance()) {
                if (!creative) {
                    StackUtil.consumeOrError(player, hand, 1);
                }
                this.crossingBase = true;
                return this.dirty = true;
            }
            if (this.crop != null && StackUtil.checkItemEquality(heldItem, MetaItems.FERTILIZER.getStackForm())) {
                if (this.applyFertilizer(true)) {
                    this.dirty = true;
                }
                if (!creative) {
                    StackUtil.consumeOrError(player, hand, 1);
                }
                return true;
            }
            final IFluidHandler handler = (IFluidHandler)FluidUtil.getFluidHandler(heldItem);
            if (handler != null) {
                if (this.applyHydration(handler) || this.applyWeedEx(handler, true)) {
                    this.dirty = true;
                }
                return true;
            }
            if (this.crop == null && !this.crossingBase && Crops.instance.getBaseSeed(heldItem) != null) {
                this.reset();
                final BaseSeed baseSeed = Crops.instance.getBaseSeed(heldItem);
                this.setCrop(baseSeed.crop);
                this.currentSize = (byte)baseSeed.size;
                this.statGain = (byte)baseSeed.statGain;
                this.statGrowth = (byte)baseSeed.statGrowth;
                this.statResistance = (byte)baseSeed.statResistance;
                if (!creative) {
                    StackUtil.consumeOrError(player, hand, 1);
                }
                return true;
            }
        }
        return this.crop != null && this.crop.onRightClick((ICropTile) this, player);
    }

}
