package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class VelocityComponent implements Component, Pool.Poolable {
    public Vector2 speed = new Vector2();

    public static VelocityComponent create(PooledEngine engine){
        return engine.createComponent(VelocityComponent.class);
    }

    public VelocityComponent setSpeed(float x, float y){
        this.speed.set(x, y);
        return this;
    }

    @Override
    public void reset() {
        this.speed.set(0f, 0f);
    }
}
