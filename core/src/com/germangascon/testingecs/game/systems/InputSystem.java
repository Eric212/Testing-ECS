package com.germangascon.testingecs.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.ecs.SystemBase;
import com.germangascon.testingecs.game.World;
import com.germangascon.testingecs.game.components.InputComponent;
import com.germangascon.testingecs.game.components.PhysicsComponent;
import com.germangascon.testingecs.game.components.RenderComponent;
import com.germangascon.testingecs.game.components.TransformComponent;
import com.germangascon.testingecs.utils.Config;

import java.util.List;

public class InputSystem extends SystemBase {
    private final World world;

    public InputSystem(int priority, World world) {
        super(priority);
        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        List<InputComponent> inputComponents = getEngine().getComponents(InputComponent.class);
        for (InputComponent inputComponent : inputComponents) {
            Entity entity = getEngine().getEntity(inputComponent.getEntityID());
            PhysicsComponent physicsComponent = entity.getComponent(PhysicsComponent.class);
            physicsComponent.angularVelocity=0;
            physicsComponent.acceleration=0;
            if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
                if (Gdx.input.isKeyPressed(inputComponent.left)) {
                    physicsComponent.angularVelocity = 360;
                }
                if (Gdx.input.isKeyPressed(inputComponent.right)) {
                    physicsComponent.angularVelocity = -360;
                }
                if (Gdx.input.isKeyPressed(inputComponent.up)) {
                    physicsComponent.acceleration = physicsComponent.maxAcceleration;
                }
                if (Gdx.input.isKeyPressed(inputComponent.down)) {
                    physicsComponent.acceleration = -physicsComponent.maxAcceleration;
                }
                if (Gdx.input.isKeyJustPressed(inputComponent.fire)) {
                    TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
                    RenderComponent renderComponent = entity.getComponent(RenderComponent.class);
                    // TODO: to implement
                    float originX = (float)(transformComponent.position.x + Math.cos(Math.toRadians(transformComponent.rotation))*(renderComponent.textureRegion.getRegionWidth()));
                    float originY = (float)(transformComponent.position.y + Math.sin(Math.toRadians(transformComponent.rotation))*(renderComponent.textureRegion.getRegionHeight()));
                    world.createBullet(originX, originY, transformComponent.rotation);
                }
                if(Gdx.input.isKeyJustPressed(inputComponent.debug)){
                    if(Config.DEBUG){
                        Config.DEBUG=false;
                    }else {
                        Config.DEBUG = true;
                    }
                    System.out.println(Config.DEBUG);
                }
            }
        }
    }
}

