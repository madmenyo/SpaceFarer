package net.madmenyo.spacefarer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

public class WeaponComponent implements Component, Pool.Poolable {


    public static WeaponComponent create(PooledEngine engine){
        return engine.createComponent(WeaponComponent.class);
    }

    @Override
    public void reset() {

    }
}
