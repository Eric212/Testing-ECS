package com.germangascon.testingecs.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.germangascon.testingecs.game.GdxGame;
import com.germangascon.testingecs.ecs.Engine;
import com.germangascon.testingecs.game.World;
import com.germangascon.testingecs.game.systems.AISystem;
import com.germangascon.testingecs.game.systems.InputSystem;
import com.germangascon.testingecs.game.systems.PhysiscsSystem;
import com.germangascon.testingecs.game.systems.RenderSystem;
import com.germangascon.testingecs.game.systems.SpawnerSystem;

public class MainScreen extends ScreenAdapter {
    private final GdxGame game;
    private final Engine engine;
    private final World world;

    public MainScreen(GdxGame game) {
        this.game = game;

        engine = new Engine(10, 100, 20);
        world = new World(engine);
        engine.addSystem(new SpawnerSystem(6,world));
        engine.addSystem(new PhysiscsSystem(5));
        engine.addSystem(new RenderSystem(10, game));
        engine.addSystem(new InputSystem(4, world));
        engine.addSystem(new AISystem(3,world));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
    }

    void update(float deltaTime) {
        engine.update(deltaTime);
    }
}
