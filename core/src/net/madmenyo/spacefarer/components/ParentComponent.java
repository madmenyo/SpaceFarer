package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class ParentComponent implements Component, Pool.Poolable {
    public Entity entity;

    public static ParentComponent create(PooledEngine engine){
        return engine.createComponent(ParentComponent.class);
    }

    @Override
    public void reset() {
        entity = null;
    }
}
