package com.coba.gticompat.mixins;

import gregtech.common.items.MetaItems;
import ic2.api.crops.ICropTile;
import ic2.core.crop.cropcard.CropStickreed;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CropStickreed.class)
public class MixinCropStickreed {
    public ItemStack getGain(final ICropTile crop) {
        if (crop.getCurrentSize() <= 3) {
            return new ItemStack(Items.REEDS, crop.getCurrentSize() - 1);
        }
        return MetaItems.STICKY_RESIN.getStackForm();
    }
}
