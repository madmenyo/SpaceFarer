package net.madmenyo.spacefarer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.madmenyo.spacefarer.components.TextureComponent;
import net.madmenyo.spacefarer.components.TransformComponent;
import net.madmenyo.spacefarer.locations.BaseScreen;
import net.madmenyo.spacefarer.systems.BoundsSystem;
import net.madmenyo.spacefarer.systems.DebugDrawSystem;
import net.madmenyo.spacefarer.systems.KeyboardInputSystem;
import net.madmenyo.spacefarer.systems.MovementSystem;
import net.madmenyo.spacefarer.systems.RenderSystem;
import net.madmenyo.spacefarer.systems.ShipControlSystem;
import net.madmenyo.spacefarer.systems.WeaponSystem;
import net.madmenyo.spacefarer.utils.Assets;
import net.madmenyo.spacefarer.utils.EntityFactory;
import net.madmenyo.spacefarer.utils.Mapper;
import net.madmenyo.spacefarer.utils.TransformCalculator;

public class WorldScreen extends BaseScreen {

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;

    private ParallaxLayer parallaxLayer;

    private PooledEngine engine;

    private InputMultiplexer multiplexer;

    private KeyboardInputSystem keyboardInputSystem = new KeyboardInputSystem();

    private Entity ship;

    public WorldScreen(AssetManager assetManager) {
        super(assetManager);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        viewport = new ScreenViewport();

        //((OrthographicCamera)viewport.getCamera()).zoom = .5f;
        viewport.getCamera().update();

        parallaxLayer = new ParallaxLayer(new Texture("backgrounds/nebula_purple_02.png"), 1f);

        engine = new PooledEngine();
        addPlayerShip();
        addSystems();

    }

    private void addPlayerShip() {
        Entity player = EntityFactory.CreatePlayer(engine);
        engine.addEntity(player);

        //ship = EntityFactory.CreateShip(engine, player);
        ship = EntityFactory.CreateArmedShip(engine, player);

        engine.addEntity(ship);
    }

    private void addSystems() {

        engine.addSystem(keyboardInputSystem);
        engine.addSystem(new ShipControlSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new WeaponSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new RenderSystem(batch, (OrthographicCamera) viewport.getCamera(), 1));
        engine.addSystem(new DebugDrawSystem((OrthographicCamera) viewport.getCamera()));
    }

    @Override
    public void show() {
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(keyboardInputSystem);

        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScreenUtils.clear(0, 0, 0, 1, true);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        parallaxLayer.draw(batch, viewport.getCamera());
        batch.end();
        viewport.getCamera().position.set(Mapper.TransformMapper.get(ship).position);
        engine.update(delta);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        TransformCalculator.DrawFieldOfFire(shapeRenderer, new Vector2(-100, 0), 90, 135, 50, 300);
        shapeRenderer.end();

        TransformComponent transformComponent = Mapper.TransformMapper.get(ship);
        //System.out.println(transformComponent.position);


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
