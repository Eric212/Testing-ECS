package com.germangascon.testingecs.game.components;

import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Component;

public class AIComponent extends Component {
    public Vector2 target;
    public int perceptionRate;
    public long lastPerception;
    public int cooldown;
    public long lastCooldown;
    public int cooldownTriggerAngle;
    public float arrivalRadius;
}
