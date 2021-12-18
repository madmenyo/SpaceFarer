package net.madmenyo.spacefarer.locations;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.ScreenUtils;

import javafx.stage.Stage;

public abstract class BaseScreen implements Screen {
    protected AssetManager assetManager;

    public BaseScreen(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0 ,1);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
