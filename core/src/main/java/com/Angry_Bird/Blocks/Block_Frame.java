package com.Angry_Bird.Blocks;

import com.Angry_Bird.launch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Block_Frame {
    private Sprite block_sprite;
    private float PPM;
    private World world;
    private float size;
    private launch game;
    private boolean destroyed = false;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private String frame_type;
    private String material;
    private float density;
    private float restitution;
    private float healthPoint;
    private ShapeRenderer shapeRenderer;
    private float frame_width = 0.8f; // New frame width parameter

    public Block_Frame(launch game, String frame_type, String material, float x, float y, float size) {
        this.game = game;
        this.frame_type = frame_type;
        this.material = material;
        this.size = size;
        PPM = game.getPPM();
        world = game.getWorld();
        this.shapeRenderer = game.getShapeRenderer();

        // Load texture based on frame type and material
        Texture texture = loadTexture(frame_type, material);
        block_sprite = new Sprite(texture);
        block_sprite.setSize((size / PPM) * 2, (size / PPM) * 2);
        block_sprite.setPosition(x, y);
        block_sprite.setOriginCenter();

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        create(x, y);
    }

    private Texture loadTexture(String frame_type, String material) {
        String texturePath = "";

        if (frame_type.equals("Square")) {
            if (material.equals("wood")) {
                density = 1;
                restitution = 0.5f;
                healthPoint = 2000f;
                texturePath = "woodframe.png";
            } else {
                density = 3;
                restitution = 0.1f;
                healthPoint = 4000f;
                texturePath = "rockframe.png";
            }
        } else { // Triangle
            if (material.equals("wood")) {
                density = 1;
                restitution = 0.5f;
                healthPoint = 2000f;
                texturePath = "woodTri.png";
            } else {
                density = 3;
                restitution = 0.1f;
                healthPoint = 4000f;
                texturePath = "rockTri.png";
            }
        }

        // Load the texture using the asset manager
        return game.getAssetManager().get(texturePath, Texture.class);
    }

    private void create(float x, float y) {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PPM, y / PPM);

        PolygonShape frameShape = new PolygonShape();
        float halfSize = size / PPM;

        // Define the shape of the block
        frameShape.set(new Vector2[] {
            new Vector2(-halfSize - frame_width, -halfSize - frame_width),
            new Vector2(halfSize + frame_width, -halfSize - frame_width),
            new Vector2(halfSize + frame_width, halfSize + frame_width),
            new Vector2(-halfSize - frame_width, halfSize + frame_width)
        });

        fixtureDef.shape = frameShape;
        fixtureDef.density = density;
        fixtureDef.friction = 0.5f; // Adjust friction as needed
        fixtureDef.restitution = restitution;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(this);

        // Dispose of shape after use
        frameShape.dispose();
    }

    public void draw_Block(float delta, SpriteBatch batch) {
        if (destroyed) return;

        Vector2 pos = body.getPosition(); // Get position in meters
        float angle = body.getAngle(); // Get body rotation

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // Use line mode
        shapeRenderer.setColor(1, 1, 1, 1); // Set the color for the frame

        // Draw the block's frame
        float frameSize = block_sprite.getWidth(); // Assuming square frame
        shapeRenderer.rect(
            pos.x - frameSize / 2,
            pos.y - frameSize / 2,
            frameSize / 2,
            frameSize / 2,
            frameSize,
            frameSize,
            1,
            1,
            (float) Math.toDegrees(angle)
        );

        shapeRenderer.end();
    }

    public void reducehealth(float damage) {
        healthPoint -= damage;

        if (healthPoint <= 0){
            destroyed = true; // Mark as destroyed when health reaches zero
            destroy(); // Optionally call destroy method here
        }
    }

    public void destroy() {
        destroyed = true;

        if(body != null) { // Check if body is not null before destroying it
            world.destroyBody(body);
        }

        body = null; // Set body to null after destruction to avoid dangling references
    }

    public void dispose() {
        if(block_sprite != null && block_sprite.getTexture() != null){
            block_sprite.getTexture().dispose();
        }
        if(shapeRenderer != null){
            shapeRenderer.dispose(); // Dispose of shape renderer when done
        }
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
}

