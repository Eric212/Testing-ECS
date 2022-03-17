package com.germangascon.testingecs.game.factories;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Engine;
import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.game.components.AIComponent;
import com.germangascon.testingecs.game.components.PhysicsComponent;
import com.germangascon.testingecs.game.components.RenderComponent;
import com.germangascon.testingecs.game.components.TransformComponent;
import com.germangascon.testingecs.utils.Assets;

public class EnemyRedFactory extends EnemyFactory{

    private static final float ROTATION=270;
    private static final int SCALE=1;
    private static final TextureRegion TEXTURE_REGION= Assets.shipsRegion[1][4];
    private static final float MAX_LINEAR_VELOCITY=300;
    private static final float LINEAR_VELOCITY=MAX_LINEAR_VELOCITY;
    private static final float MAX_ANGULAR_VELOCITY=200;
    private static final float ANGULAR_VELOCITY=0;
    private static final Vector2 ACCELERATION=new Vector2(0,0);
    private static final float DRAG=0;
    private static final int PERCEPTION_RATE=300;
    private static final int COOLDOWN=1000;
    private static final int COOLDOWN_TRIGGER_ANGLE=30;
    private static final float ARRIVAL_RADIUS=5;

    public EnemyRedFactory(Engine engine) {
        super(engine);
    }

    @Override
    public int spawnEnemy(float positionX, float positionY, float targetX, float targetY) {
        Entity enemy = getEngine().createEntity();
        TransformComponent transformComponent = getEngine().createComponent(TransformComponent.class);
        RenderComponent renderComponent = getEngine().createComponent(RenderComponent.class);
        PhysicsComponent physicsComponent = getEngine().createComponent(PhysicsComponent.class);
        AIComponent aiComponent=getEngine().createComponent(AIComponent.class);

        transformComponent.position = new Vector2(positionX, positionY);
        transformComponent.rotation = ROTATION;
        transformComponent.scale = SCALE;

        renderComponent.textureRegion = TEXTURE_REGION;

        physicsComponent.maxLinearVelocity = MAX_LINEAR_VELOCITY;
        physicsComponent.linearVelocity = LINEAR_VELOCITY;
        physicsComponent.angularVelocity = ANGULAR_VELOCITY;
        physicsComponent.maxAngularVelocity = MAX_ANGULAR_VELOCITY;

        physicsComponent.acceleration = physicsComponent.acceleration;
        physicsComponent.drag = DRAG;

        aiComponent.target=new Vector2(targetX,targetY);
        aiComponent.lastPerception=System.currentTimeMillis();
        aiComponent.perceptionRate=PERCEPTION_RATE;
        aiComponent.cooldown=COOLDOWN;
        aiComponent.cooldownTriggerAngle=COOLDOWN_TRIGGER_ANGLE;
        aiComponent.arrivalRadius=ARRIVAL_RADIUS;
        aiComponent.lastCooldown=System.currentTimeMillis();


        enemy.addComponent(transformComponent);
        enemy.addComponent(renderComponent);
        enemy.addComponent(physicsComponent);
        enemy.addComponent(aiComponent);
        return enemy.getId();
    }
}
