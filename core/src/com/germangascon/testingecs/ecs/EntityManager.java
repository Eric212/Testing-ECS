package com.germangascon.testingecs.ecs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManager implements ComponentListener {
    private final static int INITIAL_ARRAY_SIZE = 100;
    private final List<Entity> entitiesList;
    private final Map<Integer, Entity> entities;
    private final Map<Class<? extends Component>, List<Component>> components;

    public EntityManager(int minEntities, int minComponents) {
        this.entities = new HashMap<>(minEntities);
        this.entitiesList = new ArrayList<>(minEntities);
        this.components = new HashMap<>(minComponents);
    }

    public Entity getEntity(int id) {
        return entities.get(id);
    }

    public List<Entity> getEntities() {
        return entitiesList;
    }

    public Entity createEntity(boolean isPlayer) {
       Entity e = new Entity(isPlayer, this);
       entities.put(e.getId(), e);
       entitiesList.add(e);
       return e;
    }

    public boolean removeEntity(Entity entity) {
        entity.removeAllComponents();
        entities.remove(entity.getId());
        return entitiesList.remove(entity);
    }

    <T extends Component> T createComponent(Class<T> componentType) {
        try {
            return componentType.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException nsme) {
            nsme.printStackTrace();
        }
        return null;
    }

    <T extends Component> List<T> getComponents(Class<T> componentType) {
        return (List<T>) components.get(componentType);
    }

    int getComponentCount() {
        int sum = 0;
        for(List<Component> componentList : components.values()) {
            sum += componentList.size();
        }
        return sum;
    }

    @Override
    public void onComponentAdded(Entity entity, Component component) {
        Class<? extends Component> componentType = component.getClass();
        List<Component> componentList = components.get(componentType);
        if(componentList == null) {
            componentList = new ArrayList<>(INITIAL_ARRAY_SIZE);
            components.put(componentType, componentList);
        }
        componentList.add(component);
    }

    @Override
    public void onComponentRemoved(Entity entity, Component component) {
        Class<? extends Component> componentType = component.getClass();
        List<Component> cmps = components.get(componentType);
        cmps.remove(component);
    }
}
