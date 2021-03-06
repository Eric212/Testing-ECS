package com.germangascon.testingecs.game.systems;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.ecs.SystemBase;
import com.germangascon.testingecs.game.World;
import com.germangascon.testingecs.game.components.AIComponent;
import com.germangascon.testingecs.game.components.PhysicsComponent;
import com.germangascon.testingecs.game.components.RenderComponent;
import com.germangascon.testingecs.game.components.TransformComponent;


import java.util.List;

public class AISystem extends SystemBase {
    private final World world;
    public AISystem(int priority,World world) {
        super(priority);
        this.world=world;
    }

    @Override
    public void update(float deltaTime) {
        List<AIComponent> aiComponents=getEngine().getComponents(AIComponent.class);
        if(aiComponents==null) {
            return;
        }
        long currentMillis = System.currentTimeMillis();

        for(AIComponent aiComponent : aiComponents) {
            if(aiComponent.target != null) {
                if(currentMillis > aiComponent.lastCooldown + aiComponent.cooldown ||
                        currentMillis > aiComponent.lastPerception + aiComponent.perceptionRate) {
                    Entity entity = getEngine().getEntity(aiComponent.getEntityID());
                    TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
                    Vector2 target = aiComponent.target.cpy();
                    target.sub(transformComponent.position);
                    // Check cooldown
                    if (currentMillis > aiComponent.lastCooldown + aiComponent.cooldown) {
                        float targetAngle = target.angleDeg();
                        if(Math.abs(transformComponent.rotation-targetAngle)< aiComponent.cooldownTriggerAngle) {
                            RenderComponent renderComponent = entity.getComponent(RenderComponent.class);
                            float originX = transformComponent.position.x + (float) (Math.cos(Math.toRadians(transformComponent.rotation)) * (renderComponent.textureRegion.getRegionWidth()));
                            float originY = transformComponent.position.y + (float) (Math.sin(Math.toRadians(transformComponent.rotation)) * (renderComponent.textureRegion.getRegionHeight()));
                            world.createBullet(originX, originY, transformComponent.rotation);
                            aiComponent.lastCooldown = currentMillis;
                        }
                    }
                    // Check perception
                    if (currentMillis > aiComponent.lastPerception + aiComponent.perceptionRate) {
                        // Apply estrategy / Pursue
                        PhysicsComponent physicsComponent = entity.getComponent(PhysicsComponent.class);

                        float linearVelocity = target.len();

                        if (linearVelocity < aiComponent.arrivalRadius) {

                        }
                        // linearVelocity = MathUtils.clamp(linearVelocity, 0, physicsComponent.maxLinearVelocity);
                        float acceleration = physicsComponent.maxAcceleration;

                        float targetAngle = target.angleDeg();
                        float angularVelocity = targetAngle - transformComponent.rotation;
                        if (angularVelocity > 180f) {
                            angularVelocity -= 360;
                        } else if (angularVelocity < -180f) {
                            angularVelocity += 360;
                        }


                        angularVelocity = MathUtils.clamp(angularVelocity, -physicsComponent.maxAngularVelocity, physicsComponent.maxAngularVelocity);
                        physicsComponent.acceleration = acceleration;
                        physicsComponent.angularVelocity = angularVelocity;

                        aiComponent.lastPerception = currentMillis;
                    }
                }
            }
        }
    }
}
