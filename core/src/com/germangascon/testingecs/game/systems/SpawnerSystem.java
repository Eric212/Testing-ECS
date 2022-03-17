package com.germangascon.testingecs.game.systems;

import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.ecs.SystemBase;
import com.germangascon.testingecs.game.World;
import com.germangascon.testingecs.game.components.SpawnerComponent;
import com.germangascon.testingecs.game.components.TransformComponent;

import java.util.List;

public class SpawnerSystem extends SystemBase {

    private World world;

    public SpawnerSystem(int priority, World world) {
        super(priority);
        this.world=world;
    }

    @Override
    public void update(float deltaTime) {
        List<SpawnerComponent> spawnerComponents= getEngine().getComponents(SpawnerComponent.class);
        if(spawnerComponents==null){
            return;
        }
        long currentTime=System.currentTimeMillis();
        for(SpawnerComponent spawnerComponent:spawnerComponents){
            if(currentTime>spawnerComponent.lastSpawn+ spawnerComponent.spawnInterval){
                Entity entity=getEngine().getEntity(spawnerComponent.getEntityID());
                TransformComponent transformComponent=entity.getComponent(TransformComponent.class);
                spawnerComponent.enemyFactory.createEnemy(transformComponent.position.x,transformComponent.position.y,world.player.getComponent(TransformComponent.class).position.x,world.player.getComponent(TransformComponent.class).position.y);
                spawnerComponent.lastSpawn=currentTime;
            }
        }
    }
}
