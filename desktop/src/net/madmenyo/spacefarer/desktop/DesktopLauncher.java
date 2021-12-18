package net.madmenyo.spacefarer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import net.madmenyo.spacefarer.SpaceFarer;

public class DesktopLauncher {
	public static void main (String[] arg) {

		//CreateSprites();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;

		config.forceExit = false;
		new LwjglApplication(new SpaceFarer(), config);
	}

	private static void CreateSprites() {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.flattenPaths = true;
		settings.combineSubdirectories = true;
		settings.fast = true;

		settings.maxWidth = 4096;
		settings.maxHeight = 4096;

		String input = "../../images/";
		String output = "sprites";
		String filename = "sprites.atlas";

		TexturePacker.process(settings, input, output, filename);
	}
}
