package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import net.madmenyo.spacefarer.components.ControlComponent;
import net.madmenyo.spacefarer.components.ShipComponent;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.components.VelocityComponent;
import net.madmenyo.spacefarer.utils.Mapper;

public class ShipControlSystem extends IteratingSystem {

    private Vector2 currentDirection = new Vector2();
    private Vector2 tmp = new Vector2();

    public ShipControlSystem() {
        super(Family.all(ShipComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ShipComponent ship = Mapper.ShipMapper.get(entity);
        ControlComponent control = Mapper.ControlMapper.get(ship.pilot);
        VelocityComponent velocity = Mapper.VelocityMapper.get(entity);
        TransformComponent transform = Mapper.TransformMapper.get(entity);

        if (ship.pilot == null) return;


        currentDirection.set(0, 1);
        System.out.println(transform.rotation);
        currentDirection.setAngleDeg(transform.rotation);

        if (control.stop){
            tmp.set(velocity.speed).nor().scl(ship.acceleration);

            if (velocity.speed.len() < tmp.len()){
                velocity.speed.set(0, -0);
            }

            velocity.speed.sub(tmp);
        }

        if (control.forward){
            System.out.println("Thrusting forward...");
            tmp.set(currentDirection).scl(ship.acceleration);
            System.out.println("Accel: " + tmp);

            velocity.speed.add(tmp);
            if (velocity.speed.len() > ship.maxSpeed){
                velocity.speed.nor().scl(ship.maxSpeed);
            }

            System.out.println("Speed: " + velocity.speed);

        }

        if (control.reverse){
            System.out.println("Thrusting backward...");
            tmp.set(currentDirection).scl(ship.acceleration);
            tmp.rotateDeg(180);

            velocity.speed.add(tmp);
            if (velocity.speed.len() > ship.maxSpeed){
                velocity.speed.nor().scl(ship.maxSpeed);
            }
        }

        if (control.left && !control.strafe){
            transform.rotation += ship.rotationSpeed * deltaTime;
        }

        if (control.right && !control.strafe){
            transform.rotation -= ship.rotationSpeed * deltaTime;
        }
    }
}
