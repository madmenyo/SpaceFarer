package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class ControlComponent implements Component, Pool.Poolable {
    public boolean forward;
    public boolean reverse;

    public boolean left;
    public boolean right;

    public boolean strafe;

    public boolean stop;

    public static ControlComponent create(PooledEngine engine){
        return engine.createComponent(ControlComponent.class);
    }

    @Override
    public void reset() {
        forward = false;
        reverse = false;
        left = false;
        right = false;
        strafe = false;
        stop = false;
    }
}
