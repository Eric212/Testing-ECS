package com.germangascon.testingecs.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.game.screens.LoadingScreen;
import com.germangascon.testingecs.game.screens.MainScreen;
import com.germangascon.testingecs.utils.Assets;
import com.germangascon.testingecs.utils.EventListener;


public class GdxGame extends Game {
	public static final int VIRTUAL_SCREEN_WIDTH = 800;
	public static final int VIRTUAL_SCREEN_HEIGHT = 600;
	public static final float ASPECT_RATIO = (float) VIRTUAL_SCREEN_WIDTH / (float) VIRTUAL_SCREEN_HEIGHT;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public Rectangle viewport;

	@Override
	public void create() {
		camera = new OrthographicCamera(VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT);
		camera.setToOrtho(false, VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render() {
		camera.update();
		// Actualizar si se modifican parámetros de la cámara
		// batch.setProjectionMatrix(camera.combined);
		batch.disableBlending();
		Gdx.gl.glViewport((int)viewport.x, (int)viewport.y, (int)viewport.width, (int) viewport.height);
		//ScreenUtils.clear(1, 1, 0, 1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(); // Render del Screen
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		float aspectRatio = (float)width / (float)height;
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);
		if(aspectRatio > ASPECT_RATIO) {
			scale = (float)height / (float) VIRTUAL_SCREEN_HEIGHT;
			crop.x = (width - VIRTUAL_SCREEN_WIDTH * scale) / 2f;
		} else if(aspectRatio < ASPECT_RATIO) {
			scale = (float)width / (float) VIRTUAL_SCREEN_WIDTH;
			crop.y = (height - VIRTUAL_SCREEN_HEIGHT * scale) / 2f;
		} else {
			scale = (float)width / (float) VIRTUAL_SCREEN_WIDTH;
		}
		float w = (float)VIRTUAL_SCREEN_WIDTH * scale;
		float h = (float) VIRTUAL_SCREEN_HEIGHT * scale;
		viewport = new Rectangle(crop.x, crop.y, w, h);
	}
}
