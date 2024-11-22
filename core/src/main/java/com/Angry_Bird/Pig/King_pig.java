package com.Angry_Bird.Pig;

import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class King_pig implements Pig {
    private Sprite pig_sprite;
    private float PPM; // Pixels per meter
    private World world;
    private float radius = 50f; // Radius of the pig in pixels
    private launch game;
    private boolean destroyed = false;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private float healthPoint = 100;

    public King_pig(launch game, float x, float y) {
        this.game = game;
        PPM = game.getPPM(); // Get the Pixels per Meter value
        world = game.getWorld();

        // Load the texture and create the sprite
        pig_sprite = new Sprite(game.getAssetManager().get("kingPinA.png", Texture.class));
        PPM = game.getPPM();
        pig_sprite.setSize(radius / PPM * 2, radius / PPM * 2 + 1.2f);
        pig_sprite.setPosition(x / PPM, y / PPM);
        pig_sprite.setOriginCenter(); // Set the origin to center for proper rotation
        world = game.getWorld();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        create();
    }

    private void create() {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((pig_sprite.getX() -radius)/PPM, (pig_sprite.getX() -radius)/PPM);

        // Define Box2D CircleShape for the pig's physics body
        CircleShape circle = new CircleShape();
        circle.setRadius(radius/PPM); // Adjust radius for Box2D physics (in meters)

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
        // Set the sprite position to match the Box2D body
        pig_sprite.setPosition(x/PPM, y/PPM);
        body.setTransform(x / PPM, y / PPM, body.getAngle()); // Set Box2D body position in meters
    }

    @Override
    public void draw_Pig(float delta, SpriteBatch batch) {
        if (!destroyed){
            Vector2 pos = body.getPosition(); // Get position in meters

            pig_sprite.setRotation((float) Math.toDegrees(body.getAngle()));
            pig_sprite.setOrigin(pig_sprite.getWidth() / 2, pig_sprite.getWidth() / 2);
            pig_sprite.setOriginBasedPosition(pos.x , pos.y);
//            pig_sprite.setPosition(pos.x-radius/PPM, pos.y - radius/PPM);
            pig_sprite.draw(batch);

        }

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
//        System.out.println("king pig "+ healthPoint);
        if (healthPoint <= 0){
            destroyed = true;
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
}























