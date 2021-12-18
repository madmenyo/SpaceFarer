package net.madmenyo.spacefarer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class ParallaxLayer {

    private float speedMultiplier;
    private Texture texture;

    private float offsetX;
    private float offsetY;

    public ParallaxLayer(Texture texture, float speedMultiplier) {
        this(texture, speedMultiplier, 0 ,0);
    }

    public ParallaxLayer(Texture texture,float speedMultiplier, float offsetX, float offsetY) {
        this.texture = texture;
        this.speedMultiplier = speedMultiplier;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

    }

    public void draw(SpriteBatch spriteBatch, Camera camera){

        int xOffset = (int)(camera.position.x * speedMultiplier);
        int yOffset = (int)(camera.position.y * speedMultiplier);

        /*
        spriteBatch.draw(texture,
                0, 0,
                (int)camera.viewportWidth, (int)camera.viewportHeight,
                xOffset, yOffset,
                texture.getWidth(), texture.getHeight(),
                false, false);

         */


        /*
        spriteBatch.draw(texture,
                camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2,
                xOffset, yOffset,
                (int)camera.viewportWidth, (int)camera.viewportHeight
        );
         */




        spriteBatch.draw(texture,
                camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2,
                0, 0,
                camera.viewportWidth, camera.viewportHeight,
                2f, 2f,
                0,
                xOffset, yOffset,
                (int)camera.viewportWidth, (int)camera.viewportHeight,
                false, false
        );

    }
}
