package com.Angry_Bird.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Objects;

public class Block_Frame {
    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private boolean destroyed = false;
    private float x,y;
    private float width,height;
    public Block_Frame(String frame_type, String material, float x, float y, float size) {
        if (frame_type.equals("Square")) {
            if (Objects.equals(material, "wood")){
                this.texture = new Texture("woodframe.png");
            }else if (Objects.equals(material, "rock")){
                this.texture = new Texture("rockframe.png");
            }
        }else if (frame_type.equals("Triangle")) {
            if (Objects.equals(material, "wood")){
                this.texture = new Texture("woodTri.png");
            }else if (Objects.equals(material, "rock")){
                this.texture = new Texture("rockTri.png");
            }

        }
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
    }

    public void draw_frame(SpriteBatch batch) {
        if (!destroyed) {
            batch.draw(texture, x,y,width,height);
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    public void destroy(){

    }
    public void dispose() {
        texture.dispose();
    }
}
