package com.germangascon.testingecs.ecs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemManager {
    private final Map<Class<? extends SystemBase>, SystemBase> systemsByClass;
    private final List<SystemBase> systems;
    private SystemListener listener;

    public SystemManager(SystemListener listener, int initialCapacity) {
        systemsByClass = new HashMap<>(initialCapacity);
        systems = new ArrayList<>(initialCapacity);
        this.listener = listener;
    }

    void addSystem(SystemBase systemBase) {
        Class<? extends SystemBase> systemType = systemBase.getClass();
        SystemBase oldSystem = getSystem(systemType);
        if(oldSystem != null) {
            // Eliminar
            removeSystem(oldSystem);
        }
        systems.add(systemBase);
        systemsByClass.put(systemType, systemBase);
        Collections.sort(systems);
        listener.systemAdded(systemBase);
    }

    void removeSystem(SystemBase systemBase) {
        if(systems.remove(systemBase)) {
            systemsByClass.remove(systemBase.getClass());
            listener.systemRemoved(systemBase);
        }
    }

    <T extends SystemBase> T getSystem(Class<T> systemType) {
        return (T) systemsByClass.get(systemType);
    }

    List<SystemBase> getSystems() {
        return systems;
    }

}
