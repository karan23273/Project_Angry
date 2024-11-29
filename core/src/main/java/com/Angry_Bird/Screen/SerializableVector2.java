package com.Angry_Bird.Screen;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class SerializableVector2 implements Serializable {
    private float x;
    private float y;

    public SerializableVector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2 toVector2() {
        return new Vector2(x, y);
    }
}
