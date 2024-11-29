package com.Angry_Bird.Blocks;

import com.Angry_Bird.BodyData;
import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;
import java.util.Objects;

public class Block_Rectangle  {

    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private float PPM;
    private World world;
    private launch game;
    private boolean destroyed = false;
    private float x,y;
    private float width,height;
    private String material;
    private float density;
    private float restitution;
    private float friction;
    private float healthPoint;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Sprite block_sprite;
    public Block_Rectangle(launch game, String material, float x, float y, float width, float height) {
        this.game = game;
        this.material = material;
        PPM = game.getPPM();
        world = game.getWorld();
        if (Objects.equals(material, "wood")){
            if (x==y){
                this.texture = new Texture("woodSquare.png");
            } else if (width>40) {
                this.texture = new Texture("woodRectThick.png");
            }else{
                this.texture = new Texture("woodLong.png");
            }
            density = 0.5f;
            restitution = 0.5f;
            friction = 10f;
            healthPoint = 200f;

//            this.texture = new Texture("woodLong.png");
        } else if (Objects.equals(material, "rock")) {
            if (x==y){
                this.texture = new Texture("stoneRectThick.png");
            } else if (width>40) {
                this.texture = new Texture("stoneRectThick.png");
            }else{
                this.texture = new Texture("stoneLong.png");
            }
            density = 1;
            restitution = 0.2f;
            friction = 10f;
            healthPoint = 500f;
//            this.texture = new Texture("stoneLong.png");

        }
        block_sprite = new Sprite(texture);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        block_sprite = new Sprite(texture);
        block_sprite.setSize(width / PPM, height / PPM);
        block_sprite.setPosition(x / PPM, y / PPM);
        block_sprite.setOriginCenter();


        world = game.getWorld();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        create();
    }
    private void create() {
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x / PPM, y / PPM);

        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox((width / 2) / PPM, (height / 2) / PPM);
        fixtureDef.shape = rectangle;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(this);
        rectangle.dispose();

    }
    private Vector2 velocity;
    float angle;
    public void draw_Block( SpriteBatch batch) {

        if (destroyed) return;

        Vector2 pos = body.getPosition(); // Get position in meters
        velocity = body.getLinearVelocity();
        angle = body.getAngle();
        // Align sprite center to body position
        block_sprite.setRotation((float) Math.toDegrees(body.getAngle()));
        block_sprite.setOriginCenter();
        block_sprite.setOriginBasedPosition(pos.x, pos.y);
        block_sprite.draw(batch);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void reducehealth(float damage) {
        healthPoint -= damage;
//        System.out.println(healthPoint);
        if (healthPoint <= 0){
            destroyed = true;
        }
    }
    public void destroy() {
        destroyed = true;
        world.destroyBody(body);
    }

    public void dispose() {
        block_sprite.getTexture().dispose(); // Dispose of the texture when done
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    public Body getBody() {
        return body;
    }
    public void setblock(float x, float y) {
        block_sprite.setPosition(x / PPM, y / PPM);
        body.setTransform(x / PPM, y / PPM, body.getAngle());
    }

    public void setState(){
        velocity = body.getLinearVelocity();
        angle = body.getAngle();
        body.setType(BodyDef.BodyType.StaticBody);


    }
    public void getState() {
        body.setType(BodyDef.BodyType.DynamicBody);
        body.setLinearVelocity(velocity);
        body.setAngularVelocity(angle);
    }

    public void load(BodyData bodyData){
        if (destroyed) return;
        body.setLinearVelocity(bodyData.getVelX(), bodyData.getVelY());
        body.setAngularVelocity(bodyData.getAngularVelocity());
        body.setTransform(bodyData.getPosX(), bodyData.getPosY(), bodyData.getAngle());
        body.setUserData(this);
    }

}
