package com.Angry_Bird.Pig;

import com.Angry_Bird.BodyData;
import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class Adult_pig implements Pig, Serializable {
    private static final long serialVersionUID = 1L;
    private float PPM; // Pixels per meter

    private World world;
    private Sprite pig_sprite;
    private float radius = 40f; // Radius in meters
    private launch game;
    private boolean destroyed = false;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private float healthPoint = 80;

    public Adult_pig(launch game, float x, float y) {
        this.game = game;
        PPM = game.getPPM();
        pig_sprite = new Sprite(new Texture("Adultpig.png"));
        pig_sprite.setSize(radius / PPM * 2, radius / PPM * 2+0.4f);
        pig_sprite.setPosition(x / PPM, y / PPM);
        pig_sprite.setOriginCenter();
        world = game.getWorld();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        create();
    }

    private void create() {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((pig_sprite.getX()) / PPM + radius / PPM, (pig_sprite.getY()) / PPM + radius / PPM);

        // Define Box2D CircleShape for the pig's physics body
        CircleShape circle = new CircleShape();
        circle.setRadius(radius / PPM); // Adjust radius for Box2D physics (in meters)

        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.2f; // Slight bounciness
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(this);
        circle.dispose();
    }

    @Override
    public void setPig(float x, float y) {
        pig_sprite.setPosition(x / PPM, y / PPM);
        body.setTransform(x / PPM, y / PPM, body.getAngle());
    }


    @Override
    public void destroy() {
        destroyed = true;
//        System.out.println("destroy king");
        world.destroyBody(body);

    }

    @Override
    public void reducehealth(float damage) {
        healthPoint -= damage;
//        System.out.println("adult pig "+ healthPoint);
        if (healthPoint <= 0){
            destroyed = true;
        }
    }
    private Vector2 velocity;
    float angle;
    @Override
    public void draw_Pig(float delta, SpriteBatch batch) {
        if (!destroyed){
            Vector2 pos = body.getPosition(); // Get position in meters
            velocity = body.getLinearVelocity();
            angle = body.getAngle();
            pig_sprite.setRotation((float) Math.toDegrees(body.getAngle()));
            pig_sprite.setOrigin(pig_sprite.getWidth() / 2, pig_sprite.getWidth() / 2);
            pig_sprite.setOriginBasedPosition(pos.x , pos.y);
//            pig_sprite.setPosition(pos.x-radius/PPM, pos.y - radius/PPM);
            pig_sprite.draw(batch);

        }

    }

    @Override
    public void dispose() {
        pig_sprite.getTexture().dispose(); // Dispose of the texture when done
    }
    public boolean isDestroyed(){
        return destroyed;
    }
    public  Body getBody(){
        return body;
    }

    public void setState(){
        velocity = body.getLinearVelocity();
        angle = body.getAngle();

        body.setType(BodyDef.BodyType.StaticBody);
    }
    public void getState() {
        body.setType(BodyDef.BodyType.DynamicBody);
        body.setLinearVelocity(velocity);
//        body.setAngularVelocity(angle);
    }
    public void load(BodyData bodyData){
        if (destroyed) return;
        body.setLinearVelocity(bodyData.getVelX(), bodyData.getVelY());
        body.setAngularVelocity(bodyData.getAngularVelocity());
        body.setTransform(bodyData.getPosX(), bodyData.getPosY(), bodyData.getAngle());
        body.setUserData(this);
    }
}
