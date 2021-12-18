package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;

import net.madmenyo.spacefarer.components.AiComponent;
import net.madmenyo.spacefarer.components.ControlComponent;

public class AiControlSystem extends IteratingSystem {

    public AiControlSystem() {
        super(Family.all(AiComponent.class, ControlComponent.class).get());
    }

    public static AiComponent create(PooledEngine engine){
        return engine.createComponent(AiComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
