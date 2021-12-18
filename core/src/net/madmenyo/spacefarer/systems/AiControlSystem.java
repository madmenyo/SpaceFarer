package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.fsm.StateMachine;

import net.madmenyo.spacefarer.components.AiComponent;
import net.madmenyo.spacefarer.components.ControlComponent;
import net.madmenyo.spacefarer.components.HealthComponent;
import net.madmenyo.spacefarer.components.ShipComponent;
import net.madmenyo.spacefarer.utils.Mapper;

public class AiControlSystem extends IteratingSystem {

    private ImmutableArray<Entity> ships;

    private StateMachine stateMachine;

    public AiControlSystem() {
        super(Family.all(AiComponent.class, ControlComponent.class).get());
    }

    public static AiComponent create(PooledEngine engine){
        return engine.createComponent(AiComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        ships = engine.getEntitiesFor(Family.all(ShipComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        stateMachine.update();

        stateMachine.
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AiComponent ai = Mapper.AiMapper.get(entity);

    }




}
