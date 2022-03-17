package com.germangascon.testingecs;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.germangascon.testingecs.game.GdxGame;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	private final static int SCREEN_WIDTH = 1280;
	private final static int SCREEN_HEIGHT = 960;

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		// config.setResizable(false);
		config.setWindowSizeLimits(SCREEN_WIDTH, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
		// config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
		config.useVsync(false);

		//config.setForegroundFPS(60);
		new Lwjgl3Application(new GdxGame(), config);
	}
}
