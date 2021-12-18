package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.utils.Pool;

import net.madmenyo.spacefarer.states.SimpleAiState;



public class AiComponent implements Component, Pool.Poolable {
    public State<Entity> state = new SimpleAiState();

    public StateMachine<>



    @Override
    public void reset() {

        state = null;
    }
}
