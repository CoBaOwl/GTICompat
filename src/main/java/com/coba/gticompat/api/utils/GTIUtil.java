package com.coba.gticompat.api.utils;

import com.coba.gticompat.Tags;
import ic2.core.block.machine.tileentity.TileEntityCropHarvester;
import ic2.core.block.machine.tileentity.TileEntityCropmatron;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.block.wiring.TileEntityTransformer;
import mods.railcraft.common.blocks.machine.manipulator.TileIC2Loader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import org.jetbrains.annotations.NotNull;

public final class GTIUtil {

    public static @NotNull ResourceLocation gtiId(@NotNull String path) {
        return new ResourceLocation(Tags.MODID, path);
    }

    public static boolean checkEntity(TileEntity te) {
        return ((te instanceof TileEntityStandardMachine) || (te instanceof TileEntityElectricBlock) || (te instanceof TileEntityTransformer) || (te instanceof TileEntityCropHarvester) || (te instanceof TileEntityCropmatron) || ((Loader.isModLoaded("railcraft")) && (te instanceof TileIC2Loader)));
    }

    private GTIUtil() {}
}
