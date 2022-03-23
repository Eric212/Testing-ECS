package com.germangascon.testingecs.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Engine;
import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.game.components.InputComponent;
import com.germangascon.testingecs.game.components.PhysicsComponent;
import com.germangascon.testingecs.game.components.RenderComponent;
import com.germangascon.testingecs.game.components.SpawnerComponent;
import com.germangascon.testingecs.game.components.TransformComponent;
import com.germangascon.testingecs.game.factories.enemies.EnemyBuilder;
import com.germangascon.testingecs.game.factories.enemies.EnemyFactory;
import com.germangascon.testingecs.game.factories.enemies.EnemyRedBuilder;
import com.germangascon.testingecs.game.factories.enemies.EntityType;
import com.germangascon.testingecs.utils.Assets;

public class World {
    private final Engine engine;
    public Entity player;
    public final EnemyFactory enemyFactory;

    public World(Engine engine) {
        this.engine = engine;
        this.enemyFactory=EnemyFactory.getInstance(engine);
        init();
    }

    private void init() {
        int idPlayer = createPlayer(40, 40);
        createSpawner(20,500,2000, EntityType.ENEMY_RED_TYPE);
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

        physicsComponent.maxLinearVelocity = 250f;
        physicsComponent.linearVelocity = 0;
        physicsComponent.angularVelocity = 0;
        physicsComponent.maxAngularVelocity = 180f;

        physicsComponent.acceleration = 0;
        physicsComponent.maxAcceleration = 200f;
        physicsComponent.drag = 1.2f;

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

        physicsComponent.acceleration=0;
        physicsComponent.maxAcceleration=400f;
        physicsComponent.drag = 0f;

        bullet.addComponent(transformComponent);
        bullet.addComponent(renderComponent);
        bullet.addComponent(physicsComponent);
        return bullet.getId();
    }
    public int createSpawner(float x, float y, int spawnInterval, EntityType entityType){
        Entity spawner= engine.createEntity();
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        SpawnerComponent spawnerComponent=engine.createComponent(SpawnerComponent.class);
        transformComponent.position=new Vector2(x,y);
        transformComponent.rotation=270;
        transformComponent.scale=1;
        spawnerComponent.spawnInterval=spawnInterval;
        spawnerComponent.lastSpawn=System.currentTimeMillis();
        spawnerComponent.entityType = entityType;
        spawner.addComponent(transformComponent);
        spawner.addComponent(spawnerComponent);
        return spawner.getId();
    }

}