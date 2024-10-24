package com.Angry_Bird.Pig;

import com.Angry_Bird.launch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class King_pig implements Pig {
    private Sprite pig_sprite;
    private float radius = 60f;
    private final launch game;
    private boolean destroyed = false;
    public King_pig(launch game){
        this.game = game;
        pig_sprite = new Sprite(new Texture("kingPin.png"));
        pig_sprite.setSize(radius*2, radius*2);
        pig_sprite.setOriginCenter();

    }

    @Override
    public void setPig(float x, float y) {
        pig_sprite.setPosition(x, y);
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public void draw_Pig(SpriteBatch batch) {
        if (!destroyed){
            pig_sprite.draw(batch);
        }
    }
    @Override
    public void dispose() {
        pig_sprite.getTexture().dispose();
    }

}
