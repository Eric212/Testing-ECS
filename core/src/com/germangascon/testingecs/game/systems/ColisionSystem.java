package com.germangascon.testingecs.game.systems;

import com.germangascon.testingecs.ecs.Entity;
import com.germangascon.testingecs.ecs.SystemBase;
import com.germangascon.testingecs.game.components.ColliderComponent;
import com.germangascon.testingecs.game.components.TransformComponent;
import com.germangascon.testingecs.utils.BoundingBox2D;

import java.util.List;

public class ColisionSystem extends SystemBase {
    //ToDo: Declare ArrayList of Entities to be removed
    public ColisionSystem(int priority) {
        super(priority);
    }

    @Override
    public void update(float deltaTime) {
        List<ColliderComponent> colliderComponents=getEngine().getComponents(ColliderComponent.class);
        if (colliderComponents==null){
            return;
        }
        for(ColliderComponent colliderComponent:colliderComponents){
            colliderComponent.collided=false;
        }
        for(int i=0;i<colliderComponents.size();i++){
            ColliderComponent c1=colliderComponents.get(i);
            Entity e1=getEngine().getEntity(c1.getEntityID());
            TransformComponent t1=e1.getComponent(TransformComponent.class);

            for(int j=i+1;j<colliderComponents.size();j++){
                ColliderComponent c2=colliderComponents.get(j);
                if((c1.mask & c2.mask)==0){
                    continue;
                }
                Entity e2=getEngine().getEntity(c2.getEntityID());
                TransformComponent t2=e2.getComponent(TransformComponent.class);
                if(checkCollision(c1,t1,c2,t2)){
                    c1.collided=true;
                    c2.collided=true;

                    //ToDo: Aplly damage
                }
            }
        }
    }

    public boolean checkCollision(ColliderComponent c1, TransformComponent t1, ColliderComponent c2, TransformComponent t2) {
        BoundingBox2D b1=c1.box.translateWorldCoord(t1.position.x,t1.position.y);
        BoundingBox2D b2=c2.box.translateWorldCoord(t2.position.x,t2.position.y);
        return b1.overlaps(b2);
    }
}
