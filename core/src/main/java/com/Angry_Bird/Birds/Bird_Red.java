package com.Angry_Bird.Birds;

import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bird_Red implements Birds{
    private Sprite red_sprite;
    private float radius = 20f;
    private final launch game;
    private boolean destroyed = false;
    public Bird_Red(final launch game) {
        this.game = game;
        Texture texture = new Texture("Red2.png");
        red_sprite = new Sprite(texture);
        red_sprite.setSize(radius * 2, radius * 2);
        red_sprite.setOriginCenter();

    }
    public void set_bird(float x, float y) {
        red_sprite.setPosition(x, y);
    }
    public void destroy() {
        destroyed = true;
    }
    public void draw_object(SpriteBatch batch) {
        if (!destroyed) {
            red_sprite.draw(batch);
        }
    }
    public void dispose() {
        red_sprite.getTexture().dispose();


    }

}
