package com.germangascon.testingecs.game.factories.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Engine;
import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.game.components.AIComponent;
import com.germangascon.testingecs.game.components.PhysicsComponent;
import com.germangascon.testingecs.game.components.RenderComponent;
import com.germangascon.testingecs.game.components.TransformComponent;
import com.germangascon.testingecs.utils.Assets;

public class EnemyRedBuilder extends EnemyBuilder {

    public EnemyRedBuilder(Engine engine) {
        super(engine);
    }

    @Override
    public TextureRegion getTextureRegion() {
        return Assets.shipsRegion[1][4];
    }
}
