package com.germangascon.testingecs.game.components;

import com.germangascon.testingecs.ecs.Component;
import com.germangascon.testingecs.game.factories.EnemyFactory;

public class SpawnerComponent extends Component {
    public int spawnInterval;
    public long lastSpawn;
    public EnemyFactory enemyFactory;
}
