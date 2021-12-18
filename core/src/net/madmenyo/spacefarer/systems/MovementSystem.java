package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import net.madmenyo.spacefarer.utils.Mapper;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {

    private Vector2 tmp = new Vector2();

    public MovementSystem(){
        super(Family.all(VelocityComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mapper.TransformMapper.get(entity);
        VelocityComponent velocity = Mapper.VelocityMapper.get(entity);

        tmp.set(velocity.speed).scl(deltaTime);
        transform.position.add(tmp.x, tmp.y, 0f);
    }
}
