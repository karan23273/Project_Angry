package com.Angry_Bird.Screen;

import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class load_Screen implements Screen {
    private final launch game;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;
    private BitmapFont font;
    private Texture texture;
    private float progress;
    private Texture loadbg;
    private Texture loadfg;

    public load_Screen(final launch game) {
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.assetManager = new AssetManager();
        this.font = game.getFont();
        texture = new Texture(Gdx.files.internal("final.png"));
        this.loadbg = new Texture(Gdx.files.internal("loadBG.png"));
        this.loadfg = new Texture(Gdx.files.internal("loadFG.png"));
        progress = 0f;
    }

    private void queue_assets(){
        assetManager.load("loadBG.png", Texture.class);
        assetManager.load("loadFG.png", Texture.class);
        assetManager.load("final.png", Texture.class);
    }

    @Override
    public void show() {
        queue_assets();
    }

    private void update(float delta) {
//        progress = MathUtils.lerp(progress, assetManager.getProgress(), 0.04f);
        progress = assetManager.getProgress();
        if (assetManager.update()){
            if (progress >= 0.999f){
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);


        batch.begin();

        font.getData().setScale(1.0f);
        batch.draw(texture, 0,0,viewport.getWorldWidth(), viewport.getWorldHeight());
        font.draw(batch, "Loading..", 20, 180);

        batch.draw(loadbg,20,10,596,70);
        batch.draw(loadfg,20,10,600*progress,70);
        batch.end();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.GRAY);
//        shapeRenderer.rect(20,10,500, 40);

//        shapeRenderer.setColor(Color.CYAN);
//        shapeRenderer.rect(20,10,500*progress, 40);

//        shapeRenderer.end();
        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        assetManager.dispose();
        font.dispose();
        texture.dispose();

    }
}
