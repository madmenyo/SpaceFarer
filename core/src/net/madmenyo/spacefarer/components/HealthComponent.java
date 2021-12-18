package net.madmenyo.spacefarer.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class HealthComponent implements Component, Pool.Poolable {

    public float health = 1f;
    public float maxHealth = 1f;

    public static HealthComponent create(PooledEngine engine){
        return engine.createComponent(HealthComponent.class);
    }

    public HealthComponent setMaxHealth(float maxHealth){
        this.maxHealth = maxHealth;
        return this;
    }

    public HealthComponent setHealth(float health){
        this.health = health;
        return this;
    }

    @Override
    public void reset() {
        this.health = 1f;
        this.maxHealth = 1f;
    }
}
