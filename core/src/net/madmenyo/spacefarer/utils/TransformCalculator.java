package net.madmenyo.spacefarer.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.madmenyo.spacefarer.components.ParentComponent;
import net.madmenyo.spacefarer.components.TransformComponent;

public class TransformCalculator {

    private static Vector2 TMP = new Vector2();

    /**
     * Calculates world transform. If the entity has parents it is anchored to these parent
     * @param entity entity to calculate
     * @param position
     * @param scale
     * @return
     */
    public static float GetWorldTransform(Entity entity, Vector2 position, Vector2 scale){

        TransformComponent transform = Mapper.TransformMapper.get(entity);

        Array<Entity> hierarchy = new Array<>();

        ParentComponent parentComponent = Mapper.ParentMapper.get(entity);
        hierarchy.add(entity);
        while (parentComponent != null){
            if (Mapper.TransformMapper.has(parentComponent.entity)){
                hierarchy.insert(0, parentComponent.entity);
            }
            parentComponent = Mapper.ParentMapper.get(parentComponent.entity);
        }

        // get root
        TransformComponent rootTransform = Mapper.TransformMapper.get(hierarchy.get(0));
        position.set(rootTransform.position.x , rootTransform.position.y);
        float rotation = rootTransform.rotation;
        scale.set(rootTransform.scale);
        // remove root from collection
        hierarchy.removeIndex(0);

        for (Entity e : hierarchy){
            TransformComponent parentTransform = Mapper.TransformMapper.get(Mapper.ParentMapper.get(e).entity);
            TransformComponent currentTransform = Mapper.TransformMapper.get(e);


            TMP.set(currentTransform.position.x, currentTransform.position.y).rotateDeg(parentTransform.rotation);
            position.add(TMP.x, TMP.y);

            rotation += currentTransform.rotation;
        }

        return rotation;
    }

    private static int segments = 10;

    public static void DrawFieldOfFire(ShapeRenderer shapeRenderer, Vector2 origin, float fromRotation, float toRotation, float minDistance, float maxDistance){


        Vector2 p1 = new Vector2();
        p1.set(0, 1);
        //p1.rotateDeg(fromRotation);
        p1.setAngleDeg(fromRotation);
        // set to outer
        p1.scl(minDistance);

        Vector2 p2 = new Vector2();
        p2.set(0, 1);
        //p2.rotateDeg(fromRotation);
        p2.setAngleDeg(fromRotation);
        p2.scl(maxDistance);

        //left line
        shapeRenderer.line(origin.x + p1.x,origin.y + p1.y, origin.x + p2.x, origin.y + p2.y);



        //right line
        p1.rotateAroundDeg(Vector2.Zero, toRotation - fromRotation);
        p2.rotateAroundDeg(Vector2.Zero, toRotation - fromRotation);
        shapeRenderer.line(origin.x + p1.x,origin.y + p1.y, origin.x + p2.x, origin.y + p2.y);



        float angleStep = (toRotation - fromRotation) / segments;
        float angle = 0;

        p1.nor().scl(maxDistance);
        p2.nor().scl(maxDistance);
        System.out.println(fromRotation);
        p1.setAngleDeg(fromRotation);

        for (int i = 1; i <= segments; i++) {

            p2.setAngleDeg(fromRotation + angleStep * i);

            shapeRenderer.line(origin.x + p1.x,origin.y + p1.y, origin.x + p2.x, origin.y + p2.y);

            p1.set(p2);
        }

    }
}
