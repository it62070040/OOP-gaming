package com.TETOSOFT.tilegame.sprites;

import com.TETOSOFT.graphics.Animation;

/**
    A Fly is a Creature that fly slowly in the air.
*/
public class Bird extends Creature {

    public Bird(Animation left, Animation right,
        Animation deadLeft, Animation deadRight)
    {
        super(left, right, deadLeft, deadRight);
    }


    public float getMaxSpeed() {
        return 0.7f;
    }


    public boolean isFlying() {
        return isAlive();
    }

}
