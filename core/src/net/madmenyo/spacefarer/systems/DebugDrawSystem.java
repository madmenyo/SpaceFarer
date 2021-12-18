package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import net.madmenyo.spacefarer.components.BoundingCircleComponent;
import net.madmenyo.spacefarer.components.DebugComponent;
import net.madmenyo.spacefarer.components.ParentComponent;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.utils.Mapper;
import net.madmenyo.spacefarer.utils.TransformCalculator;


public class DebugDrawSystem extends IteratingSystem {


   private OrthographicCamera camera;
   private ShapeRenderer shapeRenderer;

   private boolean debugMode = true;

   private Array<Entity> circles = new Array<>();

   private Array<Entity> drawOrigin = new Array<>();

   private Vector2 tmp= new Vector2();

   private Vector2 position = new Vector2();
   private Vector2 scale = new Vector2();
   float rotation = 0;

    public DebugDrawSystem(OrthographicCamera camera) {
        super(Family.one(BoundingCircleComponent.class, DebugComponent.class).get());
        this.camera = camera;

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (debugMode){
            shapeRenderer.setProjectionMatrix(camera.combined);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.setColor(.99f, .99f, .1f, .5f);
            for (Entity circleEntity : circles){
                Circle circle = Mapper.BoundingCircleMapper.get(circleEntity).circle;
                shapeRenderer.circle(circle.x, circle.y, circle.radius);
            }

            shapeRenderer.setColor(.2f, .9f, .96f, .2f);
            for (Entity originEntity : drawOrigin){
                TransformComponent transform = Mapper.TransformMapper.get(originEntity);

                rotation = TransformCalculator.GetWorldTransform(originEntity, position, scale);

                /*
                // Create hierarchy array
                Array<Entity> hierarchy = new Array<>();

                ParentComponent parentComponent = Mapper.ParentMapper.get(originEntity);
                hierarchy.add(originEntity);

                // populate for each concurrent parent
                while (parentComponent != null){
                    if (Mapper.TransformMapper.has(parentComponent.entity)){
                        hierarchy.insert(0, parentComponent.entity);
                    }
                    parentComponent = Mapper.ParentMapper.get(parentComponent.entity);
                }

                // Set root
                TransformComponent rootTransform = Mapper.TransformMapper.get(hierarchy.get(0));
                position.set(rootTransform.position);
                rotation = rootTransform.rotation;
                scale.set(rootTransform.scale);


                // remove root
                hierarchy.removeIndex(0);

                for (Entity e : hierarchy){
                    TransformComponent parentTransform = Mapper.TransformMapper.get(Mapper.ParentMapper.get(e).entity);
                    TransformComponent currentTransform = Mapper.TransformMapper.get(e);

                    tmp.set(currentTransform.position.x, currentTransform.position.y).rotateDeg(parentTransform.rotation);
                    position.add(tmp.x, tmp.y, 0);

                    rotation += currentTransform.rotation;
                }

                 */


                shapeRenderer.circle(position.x + transform.originOffset.x,
                        position.y + transform.originOffset.y, 5);

                tmp.set(0, 1);
                tmp.rotateDeg(rotation);
                tmp.scl(10);

                shapeRenderer.line(
                        position.x + transform.originOffset.x,
                        position.y + transform.originOffset.y,
                        tmp.x + position.x + transform.originOffset.x,
                        tmp.y + position.y + transform.originOffset.y
                );

            }
        }

        shapeRenderer.end();

        circles.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
       if (!debugMode) return;

       if (Mapper.DebugMapper.has(entity)){
           if (Mapper.DebugMapper.get(entity).drawOrigin)
            drawOrigin.add(entity);
       }

       if (Mapper.BoundingCircleMapper.has(entity)){
           circles.add(entity);
       }

    }
}
