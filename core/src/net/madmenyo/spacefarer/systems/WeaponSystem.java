package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;

import net.madmenyo.spacefarer.components.ParentComponent;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.components.WeaponComponent;
import net.madmenyo.spacefarer.utils.Mapper;

public class WeaponSystem extends IteratingSystem {
    public WeaponSystem() {
        super(Family.all(WeaponComponent.class, ParentComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mapper.TransformMapper.get(entity);

        transformComponent.rotation += 30 * deltaTime;

    }
}
