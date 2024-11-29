package com.Angry_Bird.Birds;

import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Bird_Yellow implements Birds{
    private Sprite yellow_sprite;
    private float PPM ;
    private float radius = 33f;

    private boolean destroyed = false;
    private World world;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private launch game;
    public Bird_Yellow(launch game,float x,float y){
        this.game = game;
        PPM = game.getPPM();
        this.world = game.getWorld();
//        Texture texture = new Texture("yellow.png");
        Texture texture = game.getAssetManager().get("yellow.png", Texture.class);

        yellow_sprite = new Sprite(texture);
        yellow_sprite.setSize(radius / PPM * 2 + 0.4f , radius / PPM * 2 + 0.4f);
        yellow_sprite.setPosition(x / PPM, y / PPM);
        yellow_sprite.setOriginCenter();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        create();

    }

    public void create() {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(yellow_sprite.getX() / PPM + radius, yellow_sprite.getY() / PPM + radius);

        CircleShape circle = new CircleShape();
        circle.setRadius((radius)/PPM -0.2f);

        fixtureDef.shape = circle;
        fixtureDef.density = 5.4f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.5f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(this);
        circle.dispose();
    }
    @Override
    public void set_bird(float x, float y) {
        yellow_sprite.setPosition((x - radius) / PPM, (y - radius) / PPM);
        body.setTransform(x, y, body.getAngle());
    }

    @Override
    public void destroy() {
        destroyed = true;
        world.destroyBody(body);
    }
    private Vector2 velocity;
    float angle;
    @Override
    public void draw_object(SpriteBatch batch) {
        if (destroyed) return;

        Vector2 pos = body.getPosition();
        velocity = body.getLinearVelocity();
        angle = body.getAngle();
//        set_bird(pos.x, pos.y);
        yellow_sprite.setRotation((float) Math.toDegrees(body.getAngle()));
        yellow_sprite.setOrigin(yellow_sprite.getWidth() / 2, yellow_sprite.getHeight() / 2-5/PPM);
        yellow_sprite.setOriginBasedPosition(pos.x, pos.y);
//        red_sprite.setOriginBasedPosition(pos.x*PPM, pos.y*PPM);
        yellow_sprite.draw(batch);
    }

    @Override
    public void dispose() {
        yellow_sprite.getTexture().dispose();
    }
    public boolean isDestroyed() {
        return destroyed;
    }
    public Body getBody(){
        return body;
    }
    public void launch(Vector2 force) {
        body.applyLinearImpulse(force.scl(1.5f), body.getWorldCenter(), true);
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
}
