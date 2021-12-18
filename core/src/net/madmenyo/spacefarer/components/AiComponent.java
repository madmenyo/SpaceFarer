package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import javax.swing.text.html.parser.Entity;

public class AiComponent implements Component, Pool.Poolable {
    public Entity target;

    public enum State {
        Idle,
        Wander,
        Gather,
        Dock,
        Follow,
        Attack,
        Flee
    }

    public State state;



    @Override
    public void reset() {
        target = null;
        state = State.Idle;
    }
}
