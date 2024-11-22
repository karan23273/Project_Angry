package com.Angry_Bird.Screen;

import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import static java.lang.System.exit;

public class Exit_Screen implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;

    private SpriteBatch batch;
    private Texture launch_image;
    private BitmapFont font;

    private Click_Button yes_Button;
    private Click_Button no_Button;

    private Texture yes_before;
    private Texture no_before;

    private Texture yes_after;
    private Texture no_after;

    private InputMultiplexer inputMultiplexer;
    private MainMenuScreen mainMenuScreen;

    public Exit_Screen(final launch game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.assetManager = game.getAssetManager();
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void update(float delta) {
        if(yes_Button.clicked()){
            exit(0);
        }else if (no_Button.clicked()) {
            game.setScreen(game.getMainMenuScreen());
        }
    }

    @Override
    public void show() {
        this.launch_image = assetManager.get("template.png", Texture.class);
        this.yes_before = assetManager.get("3B.png", Texture.class);
        this.no_before = assetManager.get("4B.png", Texture.class);
        this.yes_after = assetManager.get("3A.png", Texture.class);
        this.no_after = assetManager.get("4A.png", Texture.class);

        this.yes_Button = new Click_Button(yes_before, yes_after, viewport.getWorldWidth()/4, viewport.getWorldHeight()/3, camera);
        yes_Button.setInput(inputMultiplexer);

        this.no_Button = new Click_Button(no_before, no_after, viewport.getWorldWidth()/4 + 400, viewport.getWorldHeight()/3, camera);
        no_Button.setInput(inputMultiplexer);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.getData().setScale(0.9f);
        batch.draw(launch_image,0, 0,  viewport.getWorldWidth(), viewport.getWorldHeight());
        font.draw(batch, "ARE YOU SURE YOU ", viewport.getWorldWidth()/2 -400, viewport.getWorldHeight()/2+200);
        font.draw(batch, "WANT TO QUIT?", viewport.getWorldWidth()/2 -310, viewport.getWorldHeight()/2+80);
        yes_Button.draw(batch);
        no_Button.draw(batch);
        batch.end();
        update(v);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();

        yes_Button.set_Position(viewport.getWorldWidth()/4, viewport.getWorldHeight()/3);
        no_Button.set_Position(viewport.getWorldWidth()/4+800, viewport.getWorldHeight()/3);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        launch_image.dispose();
        yes_before.dispose();
        no_before.dispose();
        yes_after.dispose();
        no_after.dispose();

    }
}
