package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Entity;

import net.madmenyo.spacefarer.utils.Mapper;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    public ZComparator(){

    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        return (int) Math.signum(Mapper.TransformMapper.get(entityB).position.z -
                Mapper.TransformMapper.get(entityA).position.z);
    }
}
