package com.Angry_Bird.Birds;

import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Bird_Black implements Birds{
    private Sprite black_sprite;
    private float PPM ;
    private float radius = 40f;

    private boolean destroyed = false;
    private World world;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private launch game;

    public Bird_Black(launch game, float x, float y){
        this.game = game;
        PPM = game.getPPM();
        this.world = game.getWorld();
        Texture texture = game.getAssetManager().get("blackbirdhighres.png", Texture.class);

        black_sprite = new Sprite(texture);
        black_sprite.setSize(radius / PPM * 2, radius / PPM * 2 + 1);
        black_sprite.setPosition(x / PPM, y / PPM );
        black_sprite.setOriginCenter();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        create();

    }
    public void create() {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(black_sprite.getX() / PPM + radius, black_sprite.getY() / PPM + radius);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius/PPM);

        fixtureDef.shape = circle;
        fixtureDef.density = 6f;
        fixtureDef.friction = 5f;
        fixtureDef.restitution = 0.1f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(this);
        circle.dispose();
    }
    public Body getBody() {
        return body;
    }

    public void launch(Vector2 force) {
        body.applyLinearImpulse(force.scl(3.5f), body.getWorldCenter(), true);
    }


    @Override
    public void set_bird(float x, float y) {
        black_sprite.setPosition((x - radius) / PPM, (y - radius) / PPM);
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
        black_sprite.setRotation((float) Math.toDegrees(body.getAngle()));
        black_sprite.setOrigin(black_sprite.getWidth() / 2, black_sprite.getWidth() / 2);
        black_sprite.setOriginBasedPosition(pos.x, pos.y);
//        red_sprite.setOriginBasedPosition(pos.x*PPM, pos.y*PPM);
        black_sprite.draw(batch);
    }
    @Override
    public void dispose() {
        black_sprite.getTexture().dispose();

    }
    public boolean isDestroyed() {
        return destroyed;
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
