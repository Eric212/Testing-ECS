package com.germangascon.testingecs.ecs;

public abstract class Component {
    private int entityID;

    public Component() {

    }

    void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public int getEntityID() {
        return entityID;
    }
}
