package net.madmenyo.spacefarer.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import net.madmenyo.spacefarer.components.ParentComponent;
import net.madmenyo.spacefarer.utils.Mapper;
import net.madmenyo.spacefarer.components.TextureComponent;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.utils.TransformCalculator;

import java.util.Comparator;

public class RenderSystem extends IteratingSystem {

    private final float PPM;

    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera cam;

    private Color tintPlaceholder = Color.WHITE.cpy();

    public RenderSystem(SpriteBatch batch, OrthographicCamera cam, float pixelsPerMeter) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());
        PPM = pixelsPerMeter;

        renderQueue = new Array<>();
        comparator = new ZComparator();

        this.batch = batch;


        this.cam = cam;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent texture = Mapper.TextureMapper.get(entity);
            TransformComponent transform = Mapper.TransformMapper.get(entity);

            if (texture.region == null || transform.isHidden) {
                continue;
            }

            Array<Entity> hierarchy = new Array<>();
            Vector2 position = new Vector2();
            Vector2 scale = new Vector2();
            Float rotation = TransformCalculator.GetWorldTransform(entity, position, scale);

            /*
            Vector3 tmp = new Vector3();

            ParentComponent parentComponent = Mapper.ParentMapper.get(entity);
            hierarchy.add(entity);
            while (parentComponent != null){
                if (Mapper.TransformMapper.has(parentComponent.entity)){
                    hierarchy.insert(0, parentComponent.entity);
                }
                parentComponent = Mapper.ParentMapper.get(parentComponent.entity);
            }

            TransformComponent rootTransform = Mapper.TransformMapper.get(hierarchy.get(0));
            position.set(rootTransform.position);
            rotation = rootTransform.rotation;
            scale.set(rootTransform.scale);


            hierarchy.removeIndex(0);

            for (Entity e : hierarchy){
                TransformComponent parentTransform = Mapper.TransformMapper.get(Mapper.ParentMapper.get(e).entity);
                TransformComponent currentTransform = Mapper.TransformMapper.get(e);

                tmp.set(currentTransform.position).rotate(Vector3.Z, parentTransform.rotation);
                position.add(tmp);

                rotation += currentTransform.rotation;
            }
             */

            Color c = batch.getColor();
            tintPlaceholder.set(transform.tint.r, transform.tint.g, transform.tint.b, transform.tint.a);
            batch.setColor(tintPlaceholder);
            float width = PixelsToMeters(texture.region.getRegionWidth());
            float height = PixelsToMeters(texture.region.getRegionHeight());
            float halfWidth = width/2f;
            float halfHeight = height/2f;
            //Allow for Offset
            float originX = halfWidth + transform.originOffset.x;
            float originY = halfHeight + transform.originOffset.y;

            batch.draw(texture.region,
                    position.x - halfWidth, position.y - halfHeight,
                    originX, originY,
                    width, height,
                    transform.scale.x, transform.scale.y,
                    rotation);

            /*
            batch.draw(texture.region,
                    transform.position.x - halfWidth, transform.position.y - halfHeight,
                    originX, originY,
                    width, height,
                    transform.scale.x, transform.scale.y,
                    transform.rotation);

             */
            batch.setColor(c);
        }

        batch.end();
        renderQueue.clear();
    }


    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return cam;
    }


    private float PixelsToMeters(float pixelValue){
        return pixelValue * (1.0f/PPM);
    }

    private float MetersToPixels(float meterValue){
        return  PPM * meterValue;
    }
}
