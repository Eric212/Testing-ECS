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

public abstract class EnemyBuilder {
    private final Engine engine;
    private static final float ROTATION=270;
    private static final int SCALE=1;
    private static final TextureRegion TEXTURE_REGION= Assets.shipsRegion[1][1];
    private static final float MAX_LINEAR_VELOCITY=300f;
    private static final float LINEAR_VELOCITY=0;
    private static final float MAX_ANGULAR_VELOCITY=200f;
    private static final float ANGULAR_VELOCITY=0;
    private static final float ACCELERATION=0;
    private static final float MAX_ACCELERATION=100f;
    private static final float DRAG=0.5f;
    private static final int PERCEPTION_RATE=300;
    private static final int COOLDOWN=1000;
    private static final int COOLDOWN_TRIGGER_ANGLE=30;
    private static final float ARRIVAL_RADIUS=5;
    public EnemyBuilder(Engine engine){
        this.engine=engine;
    }
    public int createEnemy(float positionX, float positionY, Vector2 target){
        return spawnEnemy(positionX,positionY,target);
    }
    public int spawnEnemy(float positionX, float positionY,Vector2 target){
        Entity enemy = getEngine().createEntity();
        TransformComponent transformComponent = getEngine().createComponent(TransformComponent.class);
        RenderComponent renderComponent = getEngine().createComponent(RenderComponent.class);
        PhysicsComponent physicsComponent = getEngine().createComponent(PhysicsComponent.class);
        AIComponent aiComponent=getEngine().createComponent(AIComponent.class);

        transformComponent.position = new Vector2(positionX, positionY);
        transformComponent.rotation = getRotation();
        transformComponent.scale = getScale();

        renderComponent.textureRegion = getTextureRegion();

        physicsComponent.maxLinearVelocity = getMaxLinearVelocity();
        physicsComponent.linearVelocity = getLinearVelocity();
        physicsComponent.angularVelocity = getAngularVelocity();
        physicsComponent.maxAngularVelocity = getMaxAngularVelocity();
        physicsComponent.maxAcceleration=getMaxAcceleration();
        physicsComponent.acceleration = getAcceleration();
        physicsComponent.drag = getDrag();

        aiComponent.target=target;
        aiComponent.lastPerception=System.currentTimeMillis();
        aiComponent.perceptionRate=getPerceptionRate();
        aiComponent.cooldown=getCooldown();
        aiComponent.cooldownTriggerAngle=getCooldownTriggerAngle();
        aiComponent.arrivalRadius=getArrivalRadius();
        aiComponent.lastCooldown=System.currentTimeMillis();


        enemy.addComponent(transformComponent);
        enemy.addComponent(renderComponent);
        enemy.addComponent(physicsComponent);
        enemy.addComponent(aiComponent);
        return enemy.getId();
    }

    public float getRotation() {
        return ROTATION;
    }

    public int getScale() {
        return SCALE;
    }

    public TextureRegion getTextureRegion() {
        return TEXTURE_REGION;
    }

    public float getMaxLinearVelocity() {
        return MAX_LINEAR_VELOCITY;
    }

    public float getLinearVelocity() {
        return LINEAR_VELOCITY;
    }

    public float getMaxAngularVelocity() {
        return MAX_ANGULAR_VELOCITY;
    }

    public float getAngularVelocity() {
        return ANGULAR_VELOCITY;
    }

    public float getAcceleration() {
        return ACCELERATION;
    }

    public float getMaxAcceleration() {
        return MAX_ACCELERATION;
    }

    public float getDrag() {
        return DRAG;
    }

    public int getPerceptionRate() {
        return PERCEPTION_RATE;
    }

    public int getCooldown() {
        return COOLDOWN;
    }

    public int getCooldownTriggerAngle() {
        return COOLDOWN_TRIGGER_ANGLE;
    }

    public float getArrivalRadius() {
        return ARRIVAL_RADIUS;
    }

    protected Engine getEngine(){
        return engine;
    }
}
