package com.germangascon.testingecs.game.factories.enemies;

import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Engine;

import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {
    private Map<EntityType,EnemyBuilder>factories;
    private static EnemyFactory instance;
    private EnemyFactory(Engine engine){
        factories=new HashMap<>();
        //Create all enemies factories
        EnemyRedBuilder enemyRedBuilder=new EnemyRedBuilder(engine);
        //Put all factories in the map
        factories.put(EntityType.ENEMY_RED_TYPE,enemyRedBuilder);
    }
    public synchronized static EnemyFactory getInstance(Engine engine){
        if(instance==null){
            instance=new EnemyFactory(engine);
        }
        return instance;
    }

    public int createEnemy(EntityType entityType, float x, float y, Vector2 target){
        EnemyBuilder enemyBuilder=factories.get(entityType);
        assert enemyBuilder!=null;
        return enemyBuilder.createEnemy(x,y,target);
    }
}
