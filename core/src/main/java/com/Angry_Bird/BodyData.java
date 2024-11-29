package com.Angry_Bird;


import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;


public class BodyData implements Serializable {
    private static final long serialVersionUID = 1L;
    float posX, posY;
    float velX, velY;
    float angle;
    float angularVelocity;
    boolean destroyed;
    public BodyData(float positionx, float positiony, float velocityx, float velocityy, float angle, float angularVelocity, boolean destroyed) {
        this.posX = positionx;
        this.posY = positiony;
        this.velX = velocityx;
        this.velY = velocityy;
        this.angle = angle;
        this.angularVelocity = angularVelocity;
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
