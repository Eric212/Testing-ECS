package com.germangascon.testingecs.ecs;


public interface SystemListener {
    void systemAdded(SystemBase systemBase);
    void systemRemoved(SystemBase systemBase);
}
