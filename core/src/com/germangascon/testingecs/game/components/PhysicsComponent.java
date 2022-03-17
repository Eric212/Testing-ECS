package com.germangascon.testingecs.game.components;

import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Component;

public class PhysicsComponent extends Component {
    /** If you touch me, you dieeee!!!! */
    public Vector2 velocity = new Vector2(0, 0);
    public float linearVelocity;
    public float maxLinearVelocity;
    /** Angular velocity */
    public float angularVelocity;
    public float maxAngularVelocity;

    // TODO: on next iteration
    public Vector2 acceleration;
    public float drag;
}
