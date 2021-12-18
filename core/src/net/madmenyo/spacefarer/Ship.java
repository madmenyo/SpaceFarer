package net.madmenyo.spacefarer;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Ship extends Sprite {

    private final Vector2 direction = new Vector2(0, -1);
    private final Vector2 velocity = new Vector2();
    private final Vector2 acceleration = new Vector2();
    private final Vector2 position = new Vector2();

    boolean stop = false;

    private final Vector2 tmp = new Vector2();

    /**
     * below should be based on engine power and mass/power usage of ship
     */
    private final float accelerationAmount = 5;
    private final float maxVelocity = 300;
    private final float rotationSpeed = 60;



    public Ship(Texture texture) {
        super(texture);

        setOrigin(texture.getWidth() / 2f, texture.getHeight() / 2f);
        setScale(.25f);

    }

    public void update(float delta){

        // Rotate ship
        setRotation(-direction.angleDeg() + 90);

        if (!stop) {
            // Calculate velocity
            tmp.set(acceleration);
            velocity.add(tmp);
            if (velocity.len() > maxVelocity) {
                velocity.nor().scl(maxVelocity);
            }
        } else {
            if (velocity.len() > 0){
                // reverse
                tmp.set(direction).scl(accelerationAmount).rotateDeg(180);
                velocity.add(tmp);
                System.out.println(velocity);
            }
        }

        // Calculate new position
        tmp.set(velocity).scl(delta);
        position.add(tmp);

        // Reset velocity
        acceleration.set(0, 0);

        setOriginBasedPosition(position.x, position.y);
    }

    public void forward(float delta){
        //speed += 5f;
        //speed = MathUtils.clamp(speed, 0, maxSpeed);
        acceleration.set(direction).scl(accelerationAmount);
        stop = false;
    }

    public void reverse(float delta){
        //speed += 5f;
        //speed = MathUtils.clamp(speed, 0, maxSpeed);
        acceleration.set(direction).scl(-accelerationAmount);
        stop = false;
    }

    public void rotateLeft(float delta, float originX, float originY){

    }


    public void turnLeft(float delta) {
        direction.rotateDeg(-rotationSpeed * delta);
    }
    public void turnRight(float delta) {
        direction.rotateDeg(rotationSpeed * delta);
    }

    public void fullStop(){
        stop = true;
    }
}
