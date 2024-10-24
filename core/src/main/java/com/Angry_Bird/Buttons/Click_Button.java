package com.Angry_Bird.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

public class Click_Button {
    private Texture before_click;
    private Texture after_click;
    private float button_x;
    private float button_y;
    private float button_width;
    private float button_height;
    private boolean clicked = false;
    private Camera camera;

    public Click_Button(Texture before_click, Texture after_click, float x, float y, OrthographicCamera camera) {
        this.before_click = before_click;
        this.after_click = after_click;
        this.button_x = x;
        this.button_y = y;
        this.button_width = before_click.getWidth();
        this.button_height = before_click.getHeight();
        this.camera = camera;

    }

    public void setInput(InputMultiplexer inputMultiplexer){
        inputMultiplexer.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            public boolean tap(float cursor_x, float cursor_y, int count, int button) {
                Vector3 worldCoordinates = camera.unproject(new Vector3(cursor_x, cursor_y, 0));
                if(hover_on(worldCoordinates.x, worldCoordinates.y)){
//                    clicked = true;
                    if (clicked){
                        clicked = false;
                    }else{
                        clicked = true;
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

    public void toggleDraw(SpriteBatch batch) {

        if(clicked()){
            batch.draw(after_click, button_x, button_y);
        }else{
            batch.draw(before_click, button_x, button_y);
        }
    }

    public void draw(SpriteBatch batch) {
        float x_diff = after_click.getWidth() - before_click.getWidth();
        float y_diff = after_click.getHeight() - before_click.getHeight();

        Vector3 worldCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        float worldX = worldCoordinates.x;
        float worldY = worldCoordinates.y;
        float center_x = button_x + (float) before_click.getWidth() / 2;
        float center_y = button_y + (float) before_click.getHeight() / 2;

        if (hover_on(worldX, worldY)) {
            batch.draw(after_click, center_x  - (float) after_click.getWidth() /2, center_y - (float) after_click.getHeight() /2);
        }else {
            batch.draw(before_click, button_x, button_y);
        }
    }

    private boolean hover_on(float cursor_x, float cursor_y){

        if (cursor_x >= button_x && cursor_y >= button_y ){
            if(cursor_x<= button_x + button_width && cursor_y<= button_y+button_height){
                return true;
            }return false;
        }return false;
    }

    private void dispose(){
        before_click.dispose();
        after_click.dispose();

    }
}
