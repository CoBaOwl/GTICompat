package com.coba.gticompat.items;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.init.Localization;
import java.util.ArrayList;
import java.util.List;

import ic2.core.util.StackUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemReactorThorium extends ItemRadioactive {
    public final int numberOfCells;
    public final float sEnergy;
    public final float sHeat;
    public final boolean sMox;
    private final int maxDmg;
    private final ItemStack sDep;
    public ItemReactorThorium(String aUnlocalized, int aCellcount, int maxDamage, float aEnergy, int aRadiation, float aHeat, boolean aMox, ItemStack aDep) {
        super(aUnlocalized, aRadiation);
        this.maxDmg = maxDamage;
        this.numberOfCells = aCellcount;
        this.sEnergy = aEnergy;
        this.sHeat = aHeat;
        this.sMox = aMox;
        this.sDep = aDep;

    }
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
        if (!reactor.produceEnergy())
            return;
        int basePulses = 1 + this.numberOfCells / 2;

        for (int iteration = 0; iteration < this.numberOfCells; iteration++) {
            int pulses = basePulses;
            if (!heatRun) {
                for (int i = 0; i < pulses; i++)
                    acceptUraniumPulse(stack, reactor, stack, x, y, x, y, heatRun);
                pulses += checkPulseable(reactor, x - 1, y, stack, x, y, heatRun) +
                        checkPulseable(reactor, x + 1, y, stack, x, y, heatRun) +
                        checkPulseable(reactor, x, y - 1, stack, x, y, heatRun) +
                        checkPulseable(reactor, x, y + 1, stack, x, y, heatRun);
            } else {
                pulses += checkPulseable(reactor, x - 1, y, stack, x, y, heatRun) +
                        checkPulseable(reactor, x + 1, y, stack, x, y, heatRun) +
                        checkPulseable(reactor, x, y - 1, stack, x, y, heatRun) +
                        checkPulseable(reactor, x, y + 1, stack, x, y, heatRun);
                int heat = triangularNumber(pulses) * 4;
                heat = getFinalHeat(stack, reactor, x, y, heat);
                heat = Math.round(heat * sHeat);
                ArrayList<ItemStackCoord> heatAcceptors = new ArrayList();
                checkHeatAcceptor(reactor, x - 1, y, heatAcceptors);
                checkHeatAcceptor(reactor, x + 1, y, heatAcceptors);
                checkHeatAcceptor(reactor, x, y - 1, heatAcceptors);
                checkHeatAcceptor(reactor, x, y + 1, heatAcceptors);
                while (!heatAcceptors.isEmpty() && heat > 0) {
                    int dheat = heat / heatAcceptors.size();
                    heat -= dheat;
                    ItemStackCoord acceptor = heatAcceptors.remove(0);
                    IReactorComponent acceptorComp = (IReactorComponent)acceptor.stack.getItem();
                    dheat = acceptorComp.alterHeat(acceptor.stack, reactor, acceptor.x, acceptor.y, dheat);
                    heat += dheat;
                }
                if (heat > 0)
                    reactor.addHeat(heat);
            }
        }
        if (!heatRun && getCustomDamage(stack) >= getMaxCustomDamage(stack) - 1) {
            reactor.setItemAt(x, y, getDepletedStack(stack, reactor));
        } else if (!heatRun) {
            applyCustomDamage(stack, 1, null);
        }
    }
    public int getCustomDamage(ItemStack stack) {
        if (!stack.hasTagCompound())
            return 0;
        return stack.getTagCompound().getInteger("advDmg");
    }
    public int getMaxCustomDamage(ItemStack stack) {
        return this.maxDmg;
    }
    public boolean applyCustomDamage(ItemStack stack, int damage, EntityLivingBase src) {
        setCustomDamage(stack, getCustomDamage(stack) + damage);
        return true;
    }
    public void setCustomDamage(ItemStack stack, int damage) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        nbt.setInteger("advDmg", damage);
    }
    public int getMetadata(ItemStack stack) {
        return (getCustomDamage(stack) > 0) ? 1 : 0;
    }
    protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
        return heat;
    }
    protected ItemStack getDepletedStack(ItemStack stack, IReactor reactor) {
        return sDep.copy();
    }
    protected static int checkPulseable(IReactor reactor, int x, int y, ItemStack stack, int mex, int mey, boolean heatrun) {
        ItemStack other = reactor.getItemAt(x, y);
        if (other != null && other.getItem() instanceof IReactorComponent && (
                (IReactorComponent)other.getItem()).acceptUraniumPulse(other, reactor, stack, x, y, mex, mey, heatrun))
            return 1;
        return 0;
    }
    protected static int triangularNumber(int x) {
        return (x * x + x) / 2;
    }
    protected void checkHeatAcceptor(IReactor reactor, int x, int y, ArrayList<ItemStackCoord> heatAcceptors) {
        ItemStack stack = reactor.getItemAt(x, y);
        if (stack != null && stack.getItem() instanceof IReactorComponent && (
                (IReactorComponent)stack.getItem()).canStoreHeat(stack, reactor, x, y))
            heatAcceptors.add(new ItemStackCoord(stack, x, y));
    }
    @Override
    public boolean canBePlacedIn(ItemStack itemStack, IReactor iReactor) {
        return true;
    }
    private static class ItemStackCoord {
        public final ItemStack stack;
        public final int x;
        public final int y;
        public ItemStackCoord(ItemStack stack, int x, int y) {
            this.stack = stack;
            this.x = x;
            this.y = y;
        }
    }
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun) {
            if(sMox) {
                float breedereffectiveness = (float)reactor.getHeat() / (float)reactor.getMaxHeat();
                float ReaktorOutput = 1.5F * breedereffectiveness + 1.0F;
                reactor.addOutput(ReaktorOutput * this.sEnergy);
            } else {
                reactor.addOutput((this.sEnergy));
            }
        }
        return true;
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
    public int alterHeat(ItemStack itemStack, IReactor iReactor, int x, int y, int heat) {
        return heat;
    }
    public float influenceExplosion(ItemStack stack, IReactor reactor) {
        return (2 * this.numberOfCells);
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(Localization.translate("ic2.reactoritem.durability") + " " + (
                getMaxCustomDamage(stack) - getCustomDamage(stack)) + "/" + getMaxCustomDamage(stack));
    }
    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (double)getCustomDamage(stack) / getMaxCustomDamage(stack);
    }
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }
}
