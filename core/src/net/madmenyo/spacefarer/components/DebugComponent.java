package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class DebugComponent implements Component, Pool.Poolable {
    public boolean drawOrigin;

    public static DebugComponent create(PooledEngine engine){
        return engine.createComponent(DebugComponent.class);
    }

    @Override
    public void reset() {
        drawOrigin = false;
    }
}
