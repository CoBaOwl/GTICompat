package com.coba.gticompat.mixins;

import com.google.common.collect.Lists;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;

public class GTICompatMixinLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Lists.newArrayList("mixins.gticompat.json");
    }

}
