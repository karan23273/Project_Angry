package com.Angry_Bird.Pig;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Pig {
    public void setPig(float x, float y);
    public void draw_Pig(float delta, SpriteBatch batch);
    public void dispose();
    public void destroy();
    public void reducehealth(float v);
}
