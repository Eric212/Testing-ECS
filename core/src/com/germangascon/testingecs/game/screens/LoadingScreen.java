package com.germangascon.testingecs.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.germangascon.testingecs.game.GdxGame;
import com.germangascon.testingecs.utils.Assets;

public class LoadingScreen extends ScreenAdapter {
    private final GdxGame game;

    public LoadingScreen(GdxGame game) {
        this.game = game;
        Assets.load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Assets.update();
        game.batch.begin();
        game.batch.enableBlending();
        Assets.font.draw(game.batch, "Loading ...", GdxGame.VIRTUAL_SCREEN_WIDTH / 2f - 70,
                GdxGame.VIRTUAL_SCREEN_HEIGHT / 2f);
        game.batch.disableBlending();
        game.batch.end();

        if(Assets.isFinished()) {
            game.setScreen(new MainScreen(game));
        }
    }
}
