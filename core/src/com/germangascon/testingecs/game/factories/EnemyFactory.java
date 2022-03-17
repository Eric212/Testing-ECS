package com.germangascon.testingecs.game.factories;

import com.germangascon.testingecs.ecs.Engine;

public abstract class EnemyFactory {
    private final Engine engine;
    public EnemyFactory(Engine engine){
        this.engine=engine;
    }
    public int createEnemy(float positionX,float positionY,float targetX,float targetY){
        return spawnEnemy(positionX,positionY,targetX,targetY);
    }
    public abstract int spawnEnemy(float positionX, float positionY,float targetX,float targetY);

    protected Engine getEngine(){
        return engine;
    }
}
