package com.Angry_Bird.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Objects;

public class Block_Rectangle {
    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private boolean destroyed = false;
    private float x,y;
    private float width,height;
    public Block_Rectangle(String material, float x, float y, float width, float height) {
        if (Objects.equals(material, "wood")){
            if (x==y){
                this.texture = new Texture("woodSquare.png");
            } else if (width>40) {
                this.texture = new Texture("woodRectThick.png");
            }else{
                this.texture = new Texture("woodLong.png");
            }
        } else if (Objects.equals(material, "rock")) {
            if (x==y){
                this.texture = new Texture("stoneRectThick.png");
            } else if (width>40) {
                this.texture = new Texture("stoneRectThick.png");
            }else{
                this.texture = new Texture("stoneLong.png");
            }

        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw_obstacle(SpriteBatch batch) {
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
