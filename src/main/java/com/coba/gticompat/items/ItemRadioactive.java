package com.coba.gticompat.items;

import ic2.api.item.ICustomDamageItem;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2Potion;
import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRadioactive extends ItemBase implements IReactorComponent, ICustomDamageItem {
    private final int sRadiation;
    public ItemRadioactive(String name, int aRadiation) {
        super(name);
        this.sRadiation = aRadiation;
    }

    @Override
    public int getCustomDamage(ItemStack itemStack) {
        return 0;
    }
    @Override
    public int getMaxCustomDamage(ItemStack itemStack) {
        return 0;
    }
    @Override
    public void setCustomDamage(ItemStack itemStack, int i) {

    }
    @Override
    public boolean applyCustomDamage(ItemStack itemStack, int i, EntityLivingBase entityLivingBase) {
        return false;
    }
    @Override
    public void processChamber(ItemStack itemStack, IReactor iReactor, int i, int i1, boolean b) {
    }
    @Override
    public boolean acceptUraniumPulse(ItemStack itemStack, IReactor iReactor, ItemStack itemStack1, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
    @Override
    public boolean canStoreHeat(ItemStack itemStack, IReactor iReactor, int i, int i1) {
        return false;
    }
    @Override
    public int getMaxHeat(ItemStack itemStack, IReactor iReactor, int i, int i1) {
        return 0;
    }
    @Override
    public int getCurrentHeat(ItemStack itemStack, IReactor iReactor, int i, int i1) {
        return 0;
    }
    @Override
    public int alterHeat(ItemStack itemStack, IReactor iReactor, int i, int i1, int i2) {
        return 0;
    }
    @Override
    public float influenceExplosion(ItemStack itemStack, IReactor iReactor) {
        return 0F;
    }
    @Override
    public boolean canBePlacedIn(ItemStack itemStack, IReactor iReactor) {
        return false;
    }
    public void onUpdate(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        if (this.sRadiation > 0 && (entity instanceof EntityLivingBase entityLiving)) {
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving))
                IC2Potion.radiation.applyTo(entityLiving, 200, 100);
        }
    }
}
