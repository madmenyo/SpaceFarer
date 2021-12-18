package net.madmenyo.spacefarer.locations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javafx.stage.Stage;

public class PlayScreen extends BaseScreen {
    private Viewport viewport;
    private SpriteBatch batch;

    public PlayScreen(AssetManager assetManager) {
        super(assetManager);

        viewport = new ScreenViewport();
        batch = new SpriteBatch();




    }
}
