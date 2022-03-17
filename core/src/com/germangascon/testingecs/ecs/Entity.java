package com.germangascon.testingecs.ecs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Entity {
    private static int NUM_ENTITIES = 0;
    private final int id;
    private final boolean player;
    private final ComponentListener listener;
    private final Map<Class<? extends Component>, Component> components;

    Entity(ComponentListener listener) {
        this(false, listener);
    }

    Entity(boolean player, ComponentListener listener) {
        id = ++NUM_ENTITIES;
        this.listener = listener;
        components = new HashMap<>();
        this.player = player;
    }

    public void addComponent(Component component) {
        Class<? extends Component> componentType = component.getClass();
        component.setEntityID(id);
        components.put(componentType, component);
        listener.onComponentAdded(this, component);
    }

    public <T extends Component> T getComponent(Class<T> componentType) {
        return (T) components.get(componentType);
    }

    // Vemos si podemos con el Collection
    public Collection<Component> getComponents() {
        return components.values();
    }

    public void removeComponent(Component component) {
        Class<? extends Component> componentType = component.getClass();
        components.remove(componentType);
        // Avisar al Entity Manager
        listener.onComponentRemoved(this, component);
    }

    public int getId() {
        return id;
    }

    public boolean isPlayer() {
        return player;
    }

    void removeAllComponents() {
        for(Component component : getComponents()) {
            removeComponent(component);
        }
    }
}
