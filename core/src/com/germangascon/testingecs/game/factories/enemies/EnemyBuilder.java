package com.germangascon.testingecs.game.factories.enemies;

import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Engine;

public abstract class EnemyBuilder {
    private final Engine engine;
    public EnemyBuilder(Engine engine){
        this.engine=engine;
    }
    public int createEnemy(float positionX, float positionY, Vector2 target){
        return spawnEnemy(positionX,positionY,target);
    }
    public abstract int spawnEnemy(float positionX, float positionY,Vector2 target);

    protected Engine getEngine(){
        return engine;
    }
}
