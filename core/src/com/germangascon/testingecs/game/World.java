package com.germangascon.testingecs.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Engine;
import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.game.components.AIComponent;
import com.germangascon.testingecs.game.components.InputComponent;
import com.germangascon.testingecs.game.components.PhysicsComponent;
import com.germangascon.testingecs.game.components.RenderComponent;
import com.germangascon.testingecs.game.components.SpawnerComponent;
import com.germangascon.testingecs.game.components.TransformComponent;
import com.germangascon.testingecs.game.factories.EnemyFactory;
import com.germangascon.testingecs.game.factories.EnemyRedFactory;
import com.germangascon.testingecs.utils.Assets;

public class World {
    private final Engine engine;
    public Entity player;
    private EnemyFactory enemyFactory;

    public World(Engine engine) {
        this.engine = engine;
        init();
    }

    private void init() {
        int idPlayer = createPlayer(40, 40);
        enemyFactory =new EnemyRedFactory(engine);
        createSpawner();
    }

    public int createPlayer(float x, float y) {
        player = engine.createEntity(true);
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        RenderComponent renderComponent = engine.createComponent(RenderComponent.class);
        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);
        InputComponent inputComponent = engine.createComponent(InputComponent.class);

        transformComponent.position = new Vector2(x, y);
        transformComponent.rotation = 90;
        transformComponent.scale = 1;

        renderComponent.textureRegion = Assets.player;

        physicsComponent.maxLinearVelocity = 200f;
        physicsComponent.linearVelocity = physicsComponent.maxLinearVelocity;
        physicsComponent.angularVelocity = 0;
        physicsComponent.maxAngularVelocity = 180f;

        physicsComponent.acceleration = new Vector2(0f, 50f);
        physicsComponent.drag = 30f;

        inputComponent.left = Input.Keys.LEFT;
        inputComponent.right = Input.Keys.RIGHT;
        inputComponent.up = Input.Keys.UP;
        inputComponent.down = Input.Keys.DOWN;
        inputComponent.fire = Input.Keys.SPACE;

        player.addComponent(transformComponent);
        player.addComponent(renderComponent);
        player.addComponent(physicsComponent);
        player.addComponent(inputComponent);
        return player.getId();
    }

    /*public int createEnemy(float x, float y, float targetX, float targetY, EnemyFactory enemyFactory) {
        return enemyFactory.createEnemy(x,y,targetX,targetY);
    }*/

    public int createBullet(float x, float y, float rotation) {
        Entity bullet = engine.createEntity();
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        RenderComponent renderComponent = engine.createComponent(RenderComponent.class);
        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);

        transformComponent.position = new Vector2(x, y);
        transformComponent.rotation = rotation;
        transformComponent.scale = 1;

        renderComponent.textureRegion = Assets.tilesRegion[0][0];

        physicsComponent.maxLinearVelocity = 400f;
        physicsComponent.linearVelocity = physicsComponent.maxLinearVelocity;
        physicsComponent.angularVelocity = 0;
        physicsComponent.maxAngularVelocity = 300;

        physicsComponent.acceleration = new Vector2(0, 0);
        physicsComponent.drag = 0f;

        bullet.addComponent(transformComponent);
        bullet.addComponent(renderComponent);
        bullet.addComponent(physicsComponent);
        return bullet.getId();
    }
    public int createSpawner(){
        Entity spawner= engine.createEntity();
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        SpawnerComponent spawnerComponent=engine.createComponent(SpawnerComponent.class);
        transformComponent.position=new Vector2(20,500);
        spawnerComponent.spawnInterval=1000;
        spawnerComponent.lastSpawn=System.currentTimeMillis();
        spawnerComponent.enemyFactory=enemyFactory;
        spawner.addComponent(transformComponent);
        spawner.addComponent(spawnerComponent);
        return spawner.getId();
    }

}