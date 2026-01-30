package com.coba.gticompat.common;

import com.coba.gticompat.Tags;
import gregtech.api.GTValues;
import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MODID, name = GTValues.MODID + '/' + Tags.MODID)
public class GTICompatConfigHolder {
    @Config.Comment("IC2 machinery configurations")
    @Config.Name("machinery")
    public static IC2MachinesConfig ic2MachinesConfig = new IC2MachinesConfig();

    @Config.Comment("IC2 machinery configurations")
    @Config.Name("machinery")
    public static OreDictConfig oreDictConfig = new OreDictConfig();

    public static class IC2MachinesConfig {
        @Config.Comment({"IC2 Cropharvester and cropmatron radius" })
        @Config.RequiresMcRestart
        @Config.RangeInt(min = 4, max = 7)
        public int cropRadius = 5;

        @Config.Comment({"IC2 Cropharvester and cropmatron ticks between scan" })
        @Config.RequiresMcRestart
        @Config.RangeInt(min = 4, max = 10)
        public int cropTick = 7;
    }

    public static class OreDictConfig {

        @Config.Comment({"Delete IC2 Ore Dictionary" })
        @Config.RequiresMcRestart
        public boolean IC2OreDictDelete = true;

        @Config.Comment({"Delete RailCraft Ore Dictionary" })
        @Config.RequiresMcRestart
        public boolean RCOreDictDelete = true;
    }
}
