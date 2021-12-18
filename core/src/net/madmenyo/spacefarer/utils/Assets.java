package net.madmenyo.spacefarer.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private AssetManager assetManager = new AssetManager();

    //public static final AssetDescriptor<TextureAtlas> SPRITES = new AssetDescriptor<>("sprites/sprites.atlas", TextureAtlas.class);

    // TEMP
    public static final AssetDescriptor<Texture> TMP_SHIP01 = new AssetDescriptor<>("tmp/Kai.png", Texture.class);

    public void load(){
        //assetManager.load(SPRITES);

        // TEMP
        assetManager.load(TMP_SHIP01);
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
