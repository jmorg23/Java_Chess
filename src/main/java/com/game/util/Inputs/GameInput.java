package com.game.util.Inputs;


public abstract class GameInput {
    
    public boolean isMovingForward, isMovingBackward, isMovingLeft, isMovingRight, isFiring, isAiming, isAccelerating, isHandbraking, isUsingItem;
    private float forwardScale = 1, backwardScale = 1, turnScale = 1;

    public abstract void updateProperties();


    public float getForwardScale() {
        return this.forwardScale;
    }
    public float getBackwardScale() {
        return this.backwardScale;
    }
    public float getTurnScale() {
        return this.turnScale;
    }
 
    
}
