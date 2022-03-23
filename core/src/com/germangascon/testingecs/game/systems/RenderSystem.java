package com.germangascon.testingecs.game.systems;

import static com.germangascon.testingecs.utils.Config.DEBUG;
import static com.germangascon.testingecs.utils.Config.showFps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.ecs.SystemBase;
import com.germangascon.testingecs.game.GdxGame;
import com.germangascon.testingecs.game.components.ColliderComponent;
import com.germangascon.testingecs.game.components.RenderComponent;
import com.germangascon.testingecs.game.components.TransformComponent;
import com.germangascon.testingecs.utils.Assets;
import com.germangascon.testingecs.utils.BoundingBox2D;
import com.germangascon.testingecs.utils.Config;

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
        if(showFps && DEBUG){
            showFps(5,10);
        }
        game.batch.end();
    }

    @Override
    public void lateUpdate(float deltaTime) {
        super.lateUpdate(deltaTime);
        if(!Config.DEBUG){
            return;
        }
        game.shapeRenderer.begin();
        showDebugPosition();
        showDebugColliders();
        game.shapeRenderer.end();
    }

    public void showDebugPosition() {
        List<RenderComponent> renderComponents = getEngine().getComponents(RenderComponent.class);
        if(renderComponents==null){
            return;
        }
        game.shapeRenderer.setColor(Color.VIOLET);
        for (RenderComponent renderComponent : renderComponents) {
            Entity entity = getEngine().getEntity(renderComponent.getEntityID());
            TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
            game.shapeRenderer.circle(transformComponent.position.x,transformComponent.position.y,2);
        }
    }

    public void showFps(int offsetX, int offsetY) {
        Assets.font.draw(game.batch, "FPS" + Gdx.graphics.getFramesPerSecond(),offsetX,GdxGame.VIRTUAL_SCREEN_HEIGHT-offsetY);
    }

    public void showDebugColliders(){
        List<ColliderComponent> colliderComponents= getEngine().getComponents(ColliderComponent.class);
        if(colliderComponents==null){
            return;
        }
        for(ColliderComponent colliderComponent:colliderComponents){
            Entity entity =getEngine().getEntity(colliderComponent.getEntityID());
            TransformComponent transformComponent=entity.getComponent(TransformComponent.class);
            game.shapeRenderer.setColor(Color.GREEN);
            if(colliderComponent.collided){
                game.shapeRenderer.setColor(Color.RED);
            }
            BoundingBox2D boxWorld= colliderComponent.box.translateWorldCoord(transformComponent.position.x,transformComponent.position.y);
            game.shapeRenderer.rect(boxWorld.xLeft,boxWorld.yDown, boxWorld.xRight- boxWorld.xLeft, boxWorld.yUp- boxWorld.yDown);
        }
    }

}
