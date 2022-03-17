package com.germangascon.testingecs.game.systems;

import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.ecs.SystemBase;
import com.germangascon.testingecs.game.GdxGame;
import com.germangascon.testingecs.game.components.RenderComponent;
import com.germangascon.testingecs.game.components.TransformComponent;

import java.util.List;

public class RenderSystem extends SystemBase {
    private final GdxGame game;

    public RenderSystem(int priority, GdxGame game) {
        super(priority);
        this.game = game;
    }

    @Override
    public void update(float deltaTime) {
        game.batch.begin();
        game.batch.enableBlending();
        List<RenderComponent> renderComponents = getEngine().getComponents(RenderComponent.class);
        for(RenderComponent renderComponent : renderComponents) {
            Entity entity = getEngine().getEntity(renderComponent.getEntityID());
            TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
            game.batch.draw(renderComponent.textureRegion,
                    transformComponent.position.x, transformComponent.position.y,
                    renderComponent.textureRegion.getRegionWidth() / 2f, renderComponent.textureRegion.getRegionHeight() / 2f,
                    renderComponent.textureRegion.getRegionWidth(), renderComponent.textureRegion.getRegionHeight(),
                    transformComponent.scale, transformComponent.scale, transformComponent.rotation);
        }
        game.batch.end();
    }
}
