package com.germangascon.testingecs.ecs;

public interface ComponentListener {
    void onComponentAdded(Entity entity, Component component);
    void onComponentRemoved(Entity entity, Component component);
}
