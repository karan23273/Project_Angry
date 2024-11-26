package com.Angry_Bird.Screen;

import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
    private launch game;
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

    public load_Screen(launch game) {
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.assetManager = game.getAssetManager();
        this.font = game.getFont();
        this.texture = new Texture(Gdx.files.internal("final.png"));
        this.loadbg = new Texture(Gdx.files.internal("loadBG.png"));
        this.loadfg = new Texture(Gdx.files.internal("loadFG.png"));
        progress = 0f;
    }

    private void queue_assets(){
        assetManager.load("loadBG.png", Texture.class);
        assetManager.load("loadFG.png", Texture.class);
        assetManager.load("final.png", Texture.class);
        assetManager.load("210869.jpg", Texture.class);
        assetManager.load("woodframe.png",Texture.class);
        assetManager.load("rockframe.png",Texture.class);
        assetManager.load(("woodTri.png"),Texture.class);
        assetManager.load(("rockTri.png"),Texture.class);
        assetManager.load("210869.jpg", Texture.class);
        assetManager.load("4B.png", Texture.class);
        assetManager.load("4A.png", Texture.class);
        assetManager.load("coins shop.png", Texture.class);
        assetManager.load("heading.png", Texture.class);
        assetManager.load("buttonA.png", Texture.class);
        assetManager.load("buttonB.png", Texture.class);
        assetManager.load("buy bird.png", Texture.class);
        assetManager.load("4B.png", Texture.class);
        assetManager.load("4A.png", Texture.class);
        assetManager.load("5B.png", Texture.class);
        assetManager.load("5A.png", Texture.class);
        assetManager.load("6B.png", Texture.class);
        assetManager.load("6A.png", Texture.class);
        assetManager.load("saveB.png", Texture.class);
        assetManager.load("saveA.png", Texture.class);
        assetManager.load("eraseB.png", Texture.class);
        assetManager.load("eraseA.png", Texture.class);
        assetManager.load("template.png", Texture.class);

        assetManager.load("pausePage (2).png", Texture.class);
        assetManager.load("front.png", Texture.class);
        assetManager.load("frontA.png", Texture.class);
        assetManager.load("restartB.png", Texture.class);
        assetManager.load("restartA.png", Texture.class);
        assetManager.load("level menuB.png", Texture.class);
        assetManager.load("level menuA.png", Texture.class);

        assetManager.load("5B.png", Texture.class);
        assetManager.load("5A.png", Texture.class);
        assetManager.load("6B.png", Texture.class);
        assetManager.load("6A.png", Texture.class);

        assetManager.load("main.png", Texture.class);
        assetManager.load("1B.png", Texture.class);
        assetManager.load("1D.png", Texture.class);
        assetManager.load("2B.png", Texture.class);
        assetManager.load("2A.png", Texture.class);
        assetManager.load("4B.png", Texture.class);
        assetManager.load("4A.png", Texture.class);
        assetManager.load("shopB.png", Texture.class);
        assetManager.load("shopA.png", Texture.class);
        assetManager.load("coinB.png", Texture.class);
        assetManager.load("frame.png", Texture.class);

        assetManager.load("template.png", Texture.class);
        assetManager.load("4B.png", Texture.class);
        assetManager.load("4A.png", Texture.class);
        assetManager.load("bar.png", Texture.class);
        assetManager.load("loginB.png", Texture.class);
        assetManager.load("loginA.png", Texture.class);
        assetManager.load("signB.png", Texture.class);
        assetManager.load("signA.png", Texture.class);

        assetManager.load("level.png", Texture.class);
        assetManager.load("back.png", Texture.class);
        assetManager.load("backA.png", Texture.class);
        assetManager.load("levelFrame.png", Texture.class);
        assetManager.load("lock.png", Texture.class);
        assetManager.load("levelFrameB.png", Texture.class);
        assetManager.load("levelFrameA.png", Texture.class);

        assetManager.load("Level clear.png", Texture.class);
        assetManager.load("front.png", Texture.class);
        assetManager.load("frontA.png", Texture.class);
        assetManager.load("restartB.png", Texture.class);
        assetManager.load("restartA.png", Texture.class);
        assetManager.load("level menuB.png", Texture.class);
        assetManager.load("level menuA.png", Texture.class);

        assetManager.load("Level failed.png", Texture.class);
        assetManager.load("restartB.png", Texture.class);
        assetManager.load("restartA.png", Texture.class);
        assetManager.load("level menuB.png", Texture.class);
        assetManager.load("level menuA.png", Texture.class);

        assetManager.load("A2.png", Texture.class);
        assetManager.load("catapult.png", Texture.class);
        assetManager.load("pauseB.png", Texture.class);
        assetManager.load("pauseA.png", Texture.class);
        assetManager.load("red40.png", Texture.class);
        assetManager.load("black.png", Texture.class);
        assetManager.load("yellowA.png", Texture.class);
        assetManager.load("restartB.png", Texture.class);
        assetManager.load("restartA.png", Texture.class);
        assetManager.load("AdultpigA.png", Texture.class);
        assetManager.load("BabyPigA.png", Texture.class);
        assetManager.load("kingPinA.png", Texture.class);

        assetManager.load("template.png", Texture.class);
        assetManager.load("3B.png", Texture.class);
        assetManager.load("4B.png", Texture.class);
        assetManager.load("3A.png", Texture.class);
        assetManager.load("4A.png", Texture.class);
        assetManager.load("star.png", Texture.class);
        assetManager.load("Red2.png", Texture.class);
        assetManager.load("kingPinA.png", Texture.class);
        assetManager.load("Babypig.png", Texture.class);
        assetManager.load("adultPigA.png", Texture.class);
        assetManager.load("blackbirdhighres.png", Texture.class);
        assetManager.load("yellow.png", Texture.class);
        assetManager.load("pausePage.png", Texture.class);
        assetManager.load("Level failed2.png", Texture.class);
    }

    @Override
    public void show() {
        queue_assets();
    }

    private void update(float delta) {
//        progress = MathUtils.lerp(progress, assetManager.getProgress(), 0.1f);
        progress = assetManager.getProgress();
        if (assetManager.update()){
            if (progress >= 0.999f){
                game.setLoadScreen(this);
                game.setScreen(game.getMainMenuScreen());
//                game.setScreen(game.getLevelPassed());
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

        batch.draw(loadbg,20,10,596,60);
        batch.draw(loadfg,20,10,600*progress,60);
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
        font.dispose();
        texture.dispose();
        loadbg.dispose();
        loadfg.dispose();

    }
}
