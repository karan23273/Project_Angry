package com.Angry_Bird.Birds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Birds {
    public void set_bird(float x, float y);
    public void destroy();
    public void draw_object(SpriteBatch batch);
    public void dispose();
}
