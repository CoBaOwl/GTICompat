package com.coba.gticompat.items;


import com.coba.gticompat.GTIcompat;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item {
    public ItemBase(String name) {
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setMaxStackSize(64);
        Items.ITEMS.add(this);
    }
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        GTIcompat.proxy.registerItemRender(this, 0, this.getRegistryName().toString());
        GTIcompat.proxy.registerItemRender(this, 1, this.getRegistryName().toString());
    }
}
