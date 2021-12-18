package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import net.madmenyo.spacefarer.components.BoundingCircleComponent;
import net.madmenyo.spacefarer.components.ParentComponent;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.utils.Mapper;

public class BoundsSystem extends IteratingSystem {

    private Vector3 currentPosition = new Vector3();
    private float currentRotation;

    private Vector3 tmp = new Vector3();

    private Array<Entity> hierarchy = new Array();

    public BoundsSystem() {
        super(Family.one(BoundingCircleComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ParentComponent parent = Mapper.ParentMapper.get(entity);
        //TransformComponent transform = Mapper.TransformMapper.get(entity);
        BoundingCircleComponent circleComponent = Mapper.BoundingCircleMapper.get(entity);

        //worldPosition.set(transform.position);
        hierarchy.clear();

        hierarchy.add(entity);

        while (parent != null){
            if (Mapper.TransformMapper.has(parent.entity)){
                hierarchy.insert(0, parent.entity);

                //worldPosition.add(Mapper.TransformMapper.get(parent.entity).position);
            }
            parent = Mapper.ParentMapper.get(parent.entity);
        }

        TransformComponent rootTransform = Mapper.TransformMapper.get(hierarchy.get(0));
        currentPosition.set(rootTransform.position);
        currentRotation = rootTransform.rotation;

        //Remove first, should be the one without a parent, we can lookup this one by the parent of it's child
        hierarchy.removeIndex(0);

        for (Entity e : hierarchy){
            //System.out.println(++i);
            TransformComponent parentTransform = Mapper.TransformMapper.get(Mapper.ParentMapper.get(e).entity);
            TransformComponent currentTransform = Mapper.TransformMapper.get(e);

            tmp.set(currentTransform.position).rotate(Vector3.Z, parentTransform.rotation);
            currentPosition.add(tmp);
        }

        circleComponent.circle.setPosition(currentPosition.x, currentPosition.y);


    }
}
