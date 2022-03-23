package com.germangascon.testingecs.game.components;

import com.germangascon.testingecs.ecs.Component;
import com.germangascon.testingecs.game.factories.enemies.EnemyBuilder;
import com.germangascon.testingecs.game.factories.enemies.EntityType;

public class SpawnerComponent extends Component {
    public int spawnInterval;
    public long lastSpawn;
    public EntityType entityType;
}
