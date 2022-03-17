package com.germangascon.testingecs.game.components;

import com.badlogic.gdx.math.Vector2;
import com.germangascon.testingecs.ecs.Component;

public class TransformComponent extends Component {
    public Vector2 position;
    public float rotation;
    public float scale = 1;
}
