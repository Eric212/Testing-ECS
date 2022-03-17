package com.germangascon.testingecs.ecs;

import java.util.Collection;
import java.util.List;

public class Engine implements SystemListener {
    private final EntityManager entityManager;
    private final SystemManager systemManager;

    public Engine(int minSystems, int minEntities, int minComponents) {
        entityManager = new EntityManager(minEntities, minComponents);
        systemManager = new SystemManager(this, minSystems);
    }

    @Override
    public void systemAdded(SystemBase systemBase) {
        systemBase.addedToEngine(this);
    }

    @Override
    public void systemRemoved(SystemBase systemBase) {
        systemBase.removedFromEngine();
    }

    public Entity createEntity(boolean isPlayer) {
        return entityManager.createEntity(isPlayer);
    }

    public void removeEntity(Entity entity) {
        entityManager.removeEntity(entity);
    }

    public Entity createEntity() {
        return entityManager.createEntity(false);
    }

    public Entity getEntity(int id) {
        return entityManager.getEntity(id);
    }

    public List<Entity> getEntities() {
        return entityManager.getEntities();
    }

    public <T extends Component> T createComponent(Class<T> componentType) {
        return entityManager.createComponent(componentType);
    }

    public int getComponentCount() {
        return entityManager.getComponentCount();
    }

    public <T extends Component> List<T> getComponents(Class<T> componentType) {
        return entityManager.getComponents(componentType);
    }

    public void addSystem(SystemBase systemBase) {
        systemManager.addSystem(systemBase);
    }

    public void removeSystem(SystemBase systemBase) {
        systemManager.removeSystem(systemBase);
    }

    public <T extends SystemBase> T getSystem(Class<T> systemType) {
        return systemManager.getSystem(systemType);
    }

    public List<SystemBase> getSystems() {
        return systemManager.getSystems();
    }

    public void update(float deltaTime) {
        List<SystemBase> systems = systemManager.getSystems();
        for(SystemBase system: systems) {
            system.update(deltaTime);
        }
        for(SystemBase system: systems) {
            system.lateUpdate(deltaTime);
        }
    }
}
