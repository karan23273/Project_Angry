package com.Angry_Bird.Buttons;

import com.Angry_Bird.Birds.Bird_Black;
import com.Angry_Bird.Birds.Bird_Red;
import com.Angry_Bird.Birds.Bird_Yellow;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;

public class Catapult implements Serializable {
    private static final long serialVersionUID = 1L;
    private final launch game;
    private final World world;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    // Catapult and bird properties
    private Vector2 initialPos;
    private Vector2 currentPos;
    private boolean dragging = false;

    // Catapult texture and dimensions
    private Texture catapultTexture;
    private float buttonX;
    private float buttonY;
    private final float radius = 30; // Radius in pixels
    private final float PPM;  // Pixels per meter

    private OrthographicCamera camera;


    private Vector2 touchStart = new Vector2();
    private Vector2 touchOffset = new Vector2();

    private final float MAX_DRAG_DISTANCE = 5;
    private Body body;
    private ArrayList<Body> birds;
    private int total_birds;
    private int index = 0;

    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }

    boolean birdzero = false;
    public boolean birdsLeft(){
        return birdzero;
    }
    public Catapult(launch game,ArrayList<Body> birds ,float x, float y) {
        this.game = game;
        this.world = game.getWorld();
        this.batch = game.getBatch();
        this.shapeRenderer = game.getShapeRenderer();
        camera = game.getCamera();
        this.buttonX = x;
        this.buttonY = y;
        this.initialPos = new Vector2(x, y);
        this.currentPos = new Vector2(x, y);
        this.PPM = game.getPPM();
        this.birds = birds;
        this.total_birds = birds.size();
        spawnNDestroyBird();
    }

    private void spawnNDestroyBird() {
        body = birds.get(index);
    }

    private void spawnNewBird() {
//        bird = new Bird_Red(game, initialPos.x, initialPos.y);
        body = null;
        if (index<total_birds) {

            body = birds.get(index);
            body.setTransform(initialPos.x, initialPos.y, body.getAngle() );
            body.setType(BodyDef.BodyType.StaticBody);
            body.setUserData(body.getUserData());
            index++;
//            System.out.println(index +" "+ total_birds);


        }
    }

    // Input handling for dragging
    public void setCatapultInput(float delta) {

        if (Gdx.input.isTouched()) {
            Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPosition);
            camera.update();
            if (touchStart.isZero()) {
                if (hoverOn(touchPosition.x, touchPosition.y)) {
                    touchStart.set(touchPosition.x, touchPosition.y);
                    touchOffset.set(touchPosition.x - buttonX, touchPosition.y - buttonY);
                    //                    touchOffset.set(touchPosition.x - catapultBody.getPosition().x, touchPosition.y - catapultBody.getPosition().y);
                    dragging = true;

//                        world.destroyBody(body);
                    if (index != 0){
                        if (body.getUserData() instanceof Bird_Red) {
                            Bird_Red red = (Bird_Red) body.getUserData();
                            red.destroy();
                        } else if (body.getUserData() instanceof Bird_Black) {
                            Bird_Black black = (Bird_Black) body.getUserData();
                            black.destroy();
                        }else if (body.getUserData() instanceof Bird_Yellow) {
                            Bird_Yellow yellow = (Bird_Yellow) body.getUserData();
                            yellow.destroy();
                        }

                    }
                    if (index < total_birds) {
                        spawnNewBird();
                    }else {
                        birdzero = true;
                        birds.clear();
                    }
                }
            }

            if (dragging) {
                currentPos.set(touchPosition.x - touchOffset.x, touchPosition.y - touchOffset.y);
                float distance = currentPos.dst(initialPos);
                if (distance > MAX_DRAG_DISTANCE) {
                    currentPos.set(initialPos.cpy().add(currentPos.sub(initialPos).nor().scl(MAX_DRAG_DISTANCE)));
                }
            }
        } else if (dragging) {
            launchBird();
            dragging = false;
            touchStart.set(0, 0);
            //            renderCatapult();
        }

    }


    public void launchBird() {
        body.setType(BodyDef.BodyType.DynamicBody);
        Vector2 launchDirection = currentPos.cpy().sub(initialPos).nor();
        float power = currentPos.dst(initialPos)*40;
        if (body.getUserData() instanceof Bird_Red) {
            Bird_Red red = (Bird_Red) body.getUserData();
            red.launch(launchDirection.scl(-power));
        } else if (body.getUserData() instanceof Bird_Black) {
            Bird_Black black = (Bird_Black) body.getUserData();
            black.launch(launchDirection.scl(-power));
        } else if (body.getUserData() instanceof Bird_Yellow) {
            Bird_Yellow yellow = (Bird_Yellow) body.getUserData();
            yellow.launch(launchDirection.scl(-power));
        }

    }


    public void renderCatapult() {

        if (body.getUserData() instanceof Bird_Red) {
            Bird_Red red = (Bird_Red) body.getUserData();
            red.draw_object(batch);
        }else if (body.getUserData() instanceof Bird_Black) {
            Bird_Black black = (Bird_Black) body.getUserData();
            black.draw_object(batch);
        } else if (body.getUserData() instanceof Bird_Yellow) {
            Bird_Yellow yellow = (Bird_Yellow) body.getUserData();
            yellow.draw_object(batch);
        }


        if (dragging) {
            body.setType(BodyDef.BodyType.KinematicBody);
            if (body.getUserData() instanceof Bird_Red) {
                Bird_Red red = (Bird_Red) body.getUserData();
                red.set_bird(currentPos.x, currentPos.y);
                red.draw_object(batch);
            } else if (body.getUserData() instanceof Bird_Black) {
                Bird_Black black = (Bird_Black) body.getUserData();
                black.set_bird(currentPos.x, currentPos.y);
                black.draw_object(batch);

            } else if (body.getUserData() instanceof Bird_Yellow) {
                Bird_Yellow yellow = (Bird_Yellow) body.getUserData();
                yellow.set_bird(currentPos.x, currentPos.y);
                yellow.draw_object(batch);
            }

        }

        if (dragging) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.line((initialPos.x-0.2f)*PPM, initialPos.y*PPM, currentPos.x*PPM, currentPos.y*PPM); // Left band
            shapeRenderer.line((initialPos.x )*PPM + radius, initialPos.y*PPM, currentPos.x*PPM, currentPos.y*PPM); // Right band
            shapeRenderer.end();
        }
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.RED); // Red color for the dragged object
//        if (dragging){
//
//            shapeRenderer.circle(currentPos.x, currentPos.y, 10);
//        }else {
//            shapeRenderer.circle(buttonX, buttonY, 10);
//        }
//        shapeRenderer.end();

    }

    public boolean hoverOn(float cursorX, float cursorY) {
        return new Vector2(cursorX, cursorY).dst(buttonX, buttonY) <= (radius*2)/PPM;
    }


    public void dispose() {
//        catapultTexture.dispose();
    }
}
