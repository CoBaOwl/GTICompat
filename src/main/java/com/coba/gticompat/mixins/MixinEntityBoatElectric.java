package com.coba.gticompat.mixins;

import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.EntityBoatElectric;
import ic2.core.item.EntityIC2Boat;
import ic2.core.util.StackUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityBoatElectric.class)
public abstract class MixinEntityBoatElectric {
    @Shadow private boolean accelerated;
    public void onUpdate() {
        ((Entity)(Object)this).extinguish();
        for (Entity e : ((Entity)(Object)this).getRecursivePassengers())
            e.extinguish();
        accelerated = false;
        Entity driver = ((Entity)(Object)this).getControllingPassenger();
        if (driver instanceof EntityPlayer && IC2.keyboard.isForwardKeyDown((EntityPlayer)driver))
            for (ItemStack stack : ((EntityPlayer)driver).inventory.armorInventory) {
                if (!StackUtil.isEmpty(stack) && ElectricItem.manager.discharge(stack, 4.0D, 2147483647, true, true, true) == 4.0D) {
                    ElectricItem.manager.discharge(stack, 4.0D, 2147483647, true, true, false);
                    this.accelerated = true;
                    break;
                }
            }
        ((EntityIC2Boat)(Object)this).onUpdate();
    }
}
