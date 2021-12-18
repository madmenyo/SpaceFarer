package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class FactionComponent implements Component, Pool.Poolable {
    String faction = "hostile";

    public static FactionComponent create(PooledEngine engine){
        return engine.createComponent(FactionComponent.class);
    }

    @Override
    public void reset() {
        faction = "hostile";
    }
}
