package net.madmenyo.spacefarer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player {
    private Ship ship;

    private String name;

    public Player(String name) {
        this.name = name;

        // add default ship
        ship = new Ship(new Texture("tmp/Kai.png"));
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public void shipMovement(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            ship.forward(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            ship.reverse(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            ship.turnLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            ship.turnRight(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.C)){
            ship.fullStop();
        }
    }
}
