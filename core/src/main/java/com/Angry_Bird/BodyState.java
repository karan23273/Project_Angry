package com.Angry_Bird;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BodyState {
    private final Vector2 velocity; // Stores the body's linear velocity
    private final float angularVelocity; // Stores the body's angular velocity
    private final Body body; // Reference to the body

    public BodyState(Body body, Vector2 velocity, float angularVelocity) {
        this.velocity = velocity;
        this.angularVelocity = angularVelocity;
        this.body = body;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public Body getBody() {
        return body;
    }
}
