package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;


public class SlotComponent implements Component, Pool.Poolable {
    public enum Type{
        Light,
        Medium,
        Heavy
    }

    public Type type = Type.Light;

    public float direction = 0;

    public Entity weapon;

    public static SlotComponent create(PooledEngine engine){
        return engine.createComponent(SlotComponent.class);
    }

    @Override
    public void reset() {
        weapon = null;
    }
}
