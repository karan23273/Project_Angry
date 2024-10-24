package com.Angry_Bird.Birds;

import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bird_Black implements Birds{
    private Sprite black_sprite;
    private float radius = 35f;
    private final launch game;
    private boolean destroyed = false;

    public Bird_Black(launch game){
        this.game = game;
        Texture texture = new Texture("blackbirdhighres.png");
        black_sprite = new Sprite(texture);
        black_sprite.setSize(radius * 2, radius * 2);
        black_sprite.setOriginCenter();

    }
    @Override
    public void set_bird(float x, float y) {
        black_sprite.setPosition(x, y);
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public void draw_object(SpriteBatch batch) {
        if (!destroyed){
            black_sprite.draw(batch);
        }
    }

    @Override
    public void dispose() {
        black_sprite.getTexture().dispose();
    }
}
