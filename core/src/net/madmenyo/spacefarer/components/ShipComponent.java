package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.List;

public class ShipComponent implements Component, Pool.Poolable {
    public Entity pilot;

    // Ship specs
    public float maxSpeed = 120;
    public float rotationSpeed = 30;
    public float acceleration = 3;

    public List<Entity> slots = new ArrayList<>();

    public static ShipComponent create(PooledEngine engine){
        return engine.createComponent(ShipComponent.class);
    }

    @Override
    public void reset() {
        pilot = null;
        maxSpeed = 50;
        rotationSpeed = 30;
        acceleration = 5;
    }

}
