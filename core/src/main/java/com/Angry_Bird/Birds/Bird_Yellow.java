package com.Angry_Bird.Birds;

import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bird_Yellow implements Birds{
    private Sprite yellow_sprite;
    private float radius = 25f;
    private final launch game;
    private boolean destroyed = false;

    public Bird_Yellow(launch game){
        this.game = game;
        Texture texture = new Texture("yellow.png");
        yellow_sprite = new Sprite(texture);
        yellow_sprite.setSize(radius * 2, radius * 2);
        yellow_sprite.setOriginCenter();

    }
    @Override
    public void set_bird(float x, float y) {
        yellow_sprite.setPosition(x, y);
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public void draw_object(SpriteBatch batch) {
        if (!destroyed){
            yellow_sprite.draw(batch);
        }
    }

    @Override
    public void dispose() {
        yellow_sprite.getTexture().dispose();
    }
}
