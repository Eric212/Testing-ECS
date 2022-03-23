package com.germangascon.testingecs.game.systems;

import com.badlogic.gdx.math.MathUtils;
import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.ecs.SystemBase;
import com.germangascon.testingecs.game.components.PhysicsComponent;
import com.germangascon.testingecs.game.components.TransformComponent;

import java.util.List;

public class PhysiscsSystem extends SystemBase {

    public PhysiscsSystem(int priority) {
        super(priority);
    }

    @Override
    public void update(float deltaTime) {
        List<PhysicsComponent> physicsComponents = getEngine().getComponents(PhysicsComponent.class);
        for(PhysicsComponent physicsComponent : physicsComponents) {
            Entity entity = getEngine().getEntity(physicsComponent.getEntityID());
            TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
            transformComponent.rotation += physicsComponent.angularVelocity * deltaTime;

            if(transformComponent.rotation > 360)
                transformComponent.rotation -= 360;
            if(transformComponent.rotation < 0)
                transformComponent.rotation += 360;

            float drag= physicsComponent.linearVelocity*physicsComponent.drag *deltaTime;
            physicsComponent.linearVelocity-=drag;
            physicsComponent.linearVelocity+=physicsComponent.acceleration*deltaTime;
            physicsComponent.linearVelocity=MathUtils.clamp(physicsComponent.linearVelocity,-physicsComponent.maxLinearVelocity,physicsComponent.maxLinearVelocity);
            physicsComponent.velocity.x = (float) (physicsComponent.linearVelocity * Math.cos(Math.toRadians(transformComponent.rotation)));
            physicsComponent.velocity.y = (float) (physicsComponent.linearVelocity * Math.sin(Math.toRadians(transformComponent.rotation)));

            transformComponent.position.mulAdd(physicsComponent.velocity, deltaTime);
        }

    }
}
