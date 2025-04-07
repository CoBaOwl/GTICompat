package com.coba.gticompat.energycapab;

import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.core.block.generator.tileentity.TileEntityKineticGenerator;
import ic2.core.block.reactor.tileentity.TileEntityNuclearReactorElectric;
import ic2.core.block.wiring.TileEntityTransformer;
import mods.railcraft.common.blocks.logic.IC2EmitterLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class IC2EventListener {

    private final Set<IEnergySource> tiles = Collections.newSetFromMap(new IdentityHashMap<>());
    private final Set<IEnergySource> railtiles = Collections.newSetFromMap(new IdentityHashMap<>());
    public IC2EventListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void ic2LoadEvent(EnergyTileLoadEvent ev) {
        if((ev.tile instanceof IEnergySource) && (ev.tile instanceof TileEntity))
            tiles.add((IEnergySource)ev.tile);
        if((ev.tile instanceof IC2EmitterLogic))
            railtiles.add((IEnergySource)ev.tile);
    }
    @SubscribeEvent
    public void ic2UnloadEvent(EnergyTileUnloadEvent ev) {
        if((ev.tile instanceof IEnergySource) && (ev.tile instanceof TileEntity))
            tiles.remove((IEnergySource)ev.tile);
        if((ev.tile instanceof IC2EmitterLogic))
            railtiles.remove((IEnergySource)ev.tile);
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
        ic2tick();
        rcTurbineTick();
    }

    private void rcTurbineTick() {
        for(IEnergySource t: this.railtiles) {
            IC2EmitterLogic el = (IC2EmitterLogic)t;
            if (el.getWorldObj().isRemote) continue;

            long capacity = (long)t.getOfferedEnergy();

            for(IEnergyTile source : el.getSubTiles()) {
                for(EnumFacing facing : EnumFacing.VALUES) {
                    if(capacity <= 0) continue;
                    TileEntity reciever = el.getWorldObj().getTileEntity(((IC2EmitterLogic)source).getPos().offset(facing));
                    if(reciever==null) continue;
                    IEnergyContainer s = reciever.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                    if(s==null) continue;
                    if(!s.inputsEnergy(facing.getOpposite()) || s.getEnergyCanBeInserted() == 0) continue;

                    capacity -= rcTurbineConverter(s, facing, el);
                }
            }
        }
    }

    private void ic2tick() {
        for(IEnergySource t: this.tiles) {
            TileEntity te = (TileEntity)t;

            if(te.getWorld().isRemote) continue;
            List<IEnergyTile> subTiles;
            if(te instanceof TileEntityNuclearReactorElectric)
                subTiles = new ArrayList<>(((TileEntityNuclearReactorElectric)te).getSubTiles());
            else {
                subTiles = new ArrayList<>();
                subTiles.add((IEnergyTile) te);
            }
            long capacity = (long)((IEnergySource)te).getOfferedEnergy();
            for(IEnergyTile source : subTiles) {
                for(EnumFacing facing : EnumFacing.VALUES) {
                    if(capacity <= 0) continue;
                    TileEntity reciever = ((TileEntity)source).getWorld().getTileEntity(((TileEntity)source).getPos().offset(facing));
                    if(reciever==null) continue;
                    IEnergyContainer s = reciever.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing.getOpposite());
                    if(s == null) continue;
                    if(!s.inputsEnergy(facing.getOpposite()) || s.getEnergyCanBeInserted() == 0) continue;
                    capacity -= convertEnergy(s, facing, te);
                }
            }
        }
    }

    private long rcTurbineConverter(IEnergyContainer reciever, EnumFacing side, IEnergySource source) {
        long transfer;
        long amps = 1;
        if(GTValues.V[source.getSourceTier()] > (long)source.getOfferedEnergy()) {
            transfer = (long)source.getOfferedEnergy();
        } else {
            amps = Math.min(1, (long)source.getOfferedEnergy() / GTValues.V[source.getSourceTier()]);
            transfer = GTValues.V[source.getSourceTier()];
        }
        long accepted = reciever.acceptEnergyFromNetwork(side.getOpposite(), transfer, amps);
        long transferResult = transfer * accepted;
        source.drawEnergy((double) transferResult);
        return transferResult;
    }

    private long convertEnergy(IEnergyContainer reciever, EnumFacing side, TileEntity source) {
        IEnergySource tesource = (IEnergySource)source;
        long transfer = 0;
        long amps = 1;
        if(GTValues.V[tesource.getSourceTier()] > (long)tesource.getOfferedEnergy()) {
            transfer = (long)tesource.getOfferedEnergy();
        } else {
            amps = Math.min(getMaxAmps(source), (long)tesource.getOfferedEnergy() / GTValues.V[tesource.getSourceTier()]);
            transfer = GTValues.V[tesource.getSourceTier()];
        }
        long accepted = reciever.acceptEnergyFromNetwork(side.getOpposite(), transfer, amps);
        long transferResult = transfer * accepted;
        tesource.drawEnergy((double) transferResult);
        return transferResult;
    }

    private long getMaxAmps(TileEntity subTile) {
        TileEntity t = subTile.getWorld().getTileEntity(subTile.getPos());
        long amps = 1;
        if ((t instanceof TileEntityTransformer) && (((TileEntityTransformer)t).getMode() == TileEntityTransformer.Mode.stepdown))
            amps = 4;
        return amps;
    }
}
