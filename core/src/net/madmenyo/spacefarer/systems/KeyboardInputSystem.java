package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import net.madmenyo.spacefarer.components.ControlComponent;
import net.madmenyo.spacefarer.utils.Mapper;

public class KeyboardInputSystem extends EntitySystem implements InputProcessor {

    private Entity player;

    @Override
    public void addedToEngine(Engine engine) {
        // TODO: probably needs a player component, now it takes looks for all ships
        player = engine.getEntitiesFor(Family.all(ControlComponent.class, ControlComponent.class).get()).first();
    }

    private boolean handleKeyInput(int keyCode, boolean down){

        ControlComponent control = Mapper.ControlMapper.get(player);

        if (keyCode == Input.Keys.W){
            control.forward = down;
            if (down) control.stop = false;
            return true;
        }

        if (keyCode == Input.Keys.S){
            control.reverse = down;
            if (down) control.stop = false;
            return true;
        }

        if (keyCode == Input.Keys.A){
            control.left = down;
            return true;
        }

        if (keyCode == Input.Keys.D){
            control.right = down;
            return true;
        }

        if (keyCode == Input.Keys.C){
            control.stop = true;
            control.strafe = false;
            control.forward = false;
            control.reverse = false;
            return true;
        }

        if (keyCode == Input.Keys.SHIFT_LEFT){
            control.strafe = down;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return handleKeyInput(keycode, true);
    }

    @Override
    public boolean keyUp(int keycode) {
        return handleKeyInput(keycode, false);
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
