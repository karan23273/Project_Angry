package com.Angry_Bird.Buttons;

import com.Angry_Bird.Birds.Bird_Red;
import com.Angry_Bird.Pig.Adult_pig;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.MouseDragGestureRecognizer;

public class Click_Button extends launch {
    private Texture before_click;
    private Texture after_click;
    private float button_x;
    private float button_y;
    private float button_width;
    private float button_height;
    private boolean clicked = false;
    private Camera camera;
    private boolean on;
    private boolean level = false;

    public Click_Button(Texture before_click, Texture after_click, float x, float y, OrthographicCamera camera) {
        level = false;
        this.before_click = before_click;
        this.after_click = after_click;
        this.button_x = x;
        this.button_y = y;
        this.button_width = before_click.getWidth();
        this.button_height = before_click.getHeight();
        this.camera = camera;
        this.on = false;
    }
    public Click_Button(Texture before_click, Texture after_click, float x, float y, OrthographicCamera camera, float button_width, float button_height) {
        level = true;
        this.before_click = before_click;
        this.after_click = after_click;
        this.button_x = x;
        this.button_y = y;
        this.button_width = button_width;
        this.button_height = button_height;
        this.camera = camera;
        this.on = false;
    }


    public void setInput(InputMultiplexer inputMultiplexer){
        inputMultiplexer.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            public boolean tap(float cursor_x, float cursor_y, int count, int button) {
                Vector3 worldCoordinates = camera.unproject(new Vector3(cursor_x, cursor_y, 0));
                if (hover_on(worldCoordinates.x, worldCoordinates.y)) {
//                    clicked = true;
                    if (on){
                        on = false;
                    }else {
                        on = true;
                    }
                    if (clicked) {
                        clicked = false;
                    } else {
                        clicked = true;
                    }
                    // Adding button sound
                    if (is_sound_ON()){
                        getSound().play();
                    }

                }
                return super.tap(cursor_x, cursor_y, count, button);
            }

        }));


    }


    public void set_Position(float x, float y) {
        this.button_x = x;
        this.button_y = y;
    }

    public boolean clicked() {
        return clicked;
    }
    public boolean isOn() {
        return on;
    }
    public void setOn(){
        this.on = true;
    }
    public void setOff(){
        this.on = false;
        clicked = false;
    }
    public void toggleDraw(SpriteBatch batch) {

        if(clicked){
            batch.draw(after_click, button_x, button_y, button_width, button_height);
        }else{
            batch.draw(before_click, button_x, button_y, button_width, button_height);
        }
    }


    public void toggleDrawON(SpriteBatch batch) {
        if (level){
            batch.draw(before_click, button_x, button_y, button_width, button_height);
        }else {
            batch.draw(before_click, button_x, button_y);
        }
    }
    public void toggleDrawOFF(SpriteBatch batch) {
        if (level){
            batch.draw(after_click, button_x, button_y, button_width, button_height);
        }else{
            batch.draw(after_click, button_x, button_y);
        }
    }

    public void draw(SpriteBatch batch) {
//        float x_diff = after_click.getWidth() - before_click.getWidth();
//        float y_diff = after_click.getHeight() - before_click.getHeight();

        Vector3 worldCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        float worldX = worldCoordinates.x;
        float worldY = worldCoordinates.y;
        float center_x = button_x + (float) before_click.getWidth() / 2;
        float center_y = button_y + (float) before_click.getHeight() / 2;

        if (hover_on(worldX, worldY)) {
            if (level){
                batch.draw(after_click,button_x,button_y, button_width + 10/getPPM(), button_height + 10/getPPM());
            }else {
                batch.draw(after_click, center_x  - (float) after_click.getWidth() /2, center_y - (float) after_click.getHeight() /2);
            }
        }else {
            if (level){
                batch.draw(before_click, button_x, button_y, button_width, button_height);
            }else {
                batch.draw(before_click, button_x, button_y);
            }
        }
    }

    public boolean hover_on(float cursor_x, float cursor_y){

        if (cursor_x >= button_x && cursor_y >= button_y ){
            if(cursor_x<= button_x + button_width && cursor_y<= button_y+button_height){
                return true;
            }return false;
        }return false;
    }

    private void button_dispose(){
        before_click.dispose();
        after_click.dispose();

    }
}
