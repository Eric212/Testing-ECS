package com.germangascon.testingecs.game.components;

import com.germangascon.testingecs.ecs.Component;
import com.germangascon.testingecs.utils.BoundingBox2D;

public class ColliderComponent extends Component {
    public final static int LAYER_NONE      =0X00000000;
    public final static int LAYER_PLAYER    =0X00000001;
    public final static int LAYER_ENEMY     =0X00000002;
    public final static int LAYER_BULLET    =0X00000004;
    //This layer must be the lst one
    public final static int LAYER_ALL       =0x11111111;
    //Alternatives:
    // -Rectangle from badlogic
    // -Intersection class, also from badlogic
    public BoundingBox2D box;
    public int mask;
    public int damage;
    public boolean collided;
}
