package net.madmenyo.spacefarer;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SystemView extends ScreenAdapter {

    private SpriteBatch spriteBatch;

    private ParallaxLayer starLayer;
    private ParallaxLayer testLayer;

    private Vector2 position = new Vector2();

    private Viewport viewport;
    private OrthographicCamera camera;

    private Player player;

    public SystemView() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera(1920, 1080);
        viewport = new ScreenViewport(camera);

        player = new Player("MadMenyo");

        starLayer = new ParallaxLayer( new Texture("backgrounds/nebula_purple_02.png"), 1f);
        testLayer = new ParallaxLayer( new Texture("backgrounds/spr_stars02.png"), .35f);

    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        player.shipMovement(delta);

        player.getShip().update(delta);

        camera.position.set(player.getShip().getX(), player.getShip().getY(), 0);
        //camera.position.set(0, 400, 0);
        camera.update();

        //camera.position.add(.8f, .5f,0);

        ScreenUtils.clear(0, 0, 0 ,1);

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        starLayer.draw(spriteBatch, camera);
        //testLayer.draw(spriteBatch, camera);

        player.getShip().draw(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }
}
