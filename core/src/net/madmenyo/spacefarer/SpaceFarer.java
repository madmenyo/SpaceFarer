package net.madmenyo.spacefarer;

import com.badlogic.gdx.Game;

import net.madmenyo.spacefarer.utils.Assets;

public class SpaceFarer extends Game {
	private Assets assets;
	@Override
	public void create () {
		assets = new Assets();
		assets.load();
		assets.getAssetManager().finishLoading();
		setScreen(new WorldScreen(assets.getAssetManager()));
	}
}
