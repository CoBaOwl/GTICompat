package com.coba.gticompat.energycapab;

import ic2.api.item.IElectricItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnergyItemCapabilityListener {
    private static final ResourceLocation rl = new ResourceLocation("gticompat", "ic2capattach");
    public EnergyItemCapabilityListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void initCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        Item item = event.getObject().getItem();
        if (item instanceof IElectricItem)
            event.addCapability(rl, new ItemCapImpl(event.getObject()));
    }
}
