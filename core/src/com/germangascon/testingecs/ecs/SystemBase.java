package com.germangascon.testingecs.ecs;

import java.util.List;

public abstract class SystemBase implements Comparable<SystemBase> {
    private final int priority;
    private Engine engine;
    private List<Entity> entities;

    public SystemBase(int priority) {
        this.priority = priority;
    }

    public abstract void update(float deltaTime);

    public void lateUpdate(float deltaTime) {

    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public int compareTo(SystemBase systemBase) {
        return priority - systemBase.priority;
    }

    void addedToEngine(Engine engine) {
        this.engine = engine;
        this.entities = engine.getEntities();
    }

    void removedFromEngine() {
        this.engine = null;
        entities = null;
    }
}
