package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Pool;

public class BoundingCircleComponent implements Component, Pool.Poolable {
    public Circle circle = new Circle();

    public static BoundingCircleComponent create(PooledEngine engine){
        return engine.createComponent(BoundingCircleComponent.class);
    }

    @Override
    public void reset() {
        circle.set(0, 0, 0);

    }
}
