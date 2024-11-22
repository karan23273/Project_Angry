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

public class Setting_Screen implements Screen {
    private final launch game;
    private MainMenuScreen mainMenuScreen;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private AssetManager assetManager;
    private Texture launch_image;
    private BitmapFont font;
    private Click_Button cross_button;
    private Texture cross_before;
    private Texture cross_after;
    private InputMultiplexer input_multiplexer;

    private Click_Button Sound_button;
    private Texture Sound_before;
    private Texture Sound_after;
    private Click_Button music_button;
    private Texture music_before;
    private Texture music_after;

    private Click_Button erase_button;
    private Texture erase_before;
    private Texture erase_after;

    private Click_Button logout_button;
    private Texture logout_before;
    private Texture logout_after;

    public Setting_Screen(final launch game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.assetManager = game.getAssetManager();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.input_multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(input_multiplexer);

    }

    private void update(float delta) {
        if (cross_button.clicked()){
            game.setScreen(game.getMainMenuScreen());
        }

        if (Sound_button.isOn()){
            game.resumeSound();
        }else if(!Sound_button.isOn()){
            game.pauseSound();
        }

        if (music_button.isOn()){
            game.playMusic();
        } else if (!music_button.isOn()) {
            game.pauseMusic();
        }
        if (erase_button.clicked()){
            game.eraseData(game.getLoginScreen().getUserid());
            game.getLoginScreen().updateStatus();
            game.setScreen(game.getMainMenuScreen());
        }
        if (logout_button.clicked()){
            game.getLoginScreen().updateStatus();
            game.setScreen(game.getMainMenuScreen());
        }

    }

    @Override
    public void show() {
        this.launch_image = assetManager.get("template.png", Texture.class);

        this.cross_before = assetManager.get("4B.png", Texture.class);
        this.cross_after = assetManager.get("4A.png", Texture.class);
        this.cross_button = new Click_Button(cross_before, cross_after, viewport.getWorldWidth() / 2 + 200, viewport.getWorldHeight() / 2+300, camera);
        cross_button.setInput(input_multiplexer);

        this.Sound_before = assetManager.get("5B.png", Texture.class);
        this.Sound_after = assetManager.get("5A.png", Texture.class);
        this.Sound_button = new Click_Button(Sound_before, Sound_after, viewport.getWorldWidth()/2-400, viewport.getWorldHeight()/2-70, camera);
        Sound_button.setInput(input_multiplexer);

        if (game.is_sound_ON()){
            Sound_button.setOn();
        }else {
            Sound_button.setOff();
        }

        this.music_before = assetManager.get("6B.png", Texture.class);
        this.music_after = assetManager.get("6A.png", Texture.class);
        this.music_button = new Click_Button(music_before, music_after, viewport.getWorldWidth()/2-200, viewport.getWorldHeight()/2-70, camera);
        music_button.setInput(input_multiplexer);
        if (game.getMusic().isPlaying()){
            music_button.setOn();
        }else {
            music_button.setOff();
        }

        this.logout_before = assetManager.get("saveB.png", Texture.class);
        this.logout_after = assetManager.get("saveA.png", Texture.class);
        this.logout_button = new Click_Button(logout_before, logout_after, viewport.getWorldWidth()/2 + 50, viewport.getWorldHeight()/2 -30, camera);
        this.logout_button.setInput(input_multiplexer);

        this.erase_before = assetManager.get("eraseB.png", Texture.class);
        this.erase_after = assetManager.get("eraseA.png", Texture.class);
        this.erase_button = new Click_Button(erase_before, erase_after, viewport.getWorldWidth()/2 + 50, viewport.getWorldHeight()/2-200, camera);
        this.erase_button.setInput(input_multiplexer);

        Gdx.input.setInputProcessor(input_multiplexer);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(launch_image,0, 0,  viewport.getWorldWidth(), viewport.getWorldHeight());
        font.getData().setScale(0.9f);
        font.draw(batch, "Settings", viewport.getWorldWidth() / 2-180, viewport.getWorldHeight() / 2+200);
        cross_button.draw(batch);
        if (Sound_button.isOn()) Sound_button.toggleDrawON(batch);
        else Sound_button.toggleDrawOFF(batch);

        if (music_button.isOn()) music_button.toggleDrawON(batch);
        else music_button.toggleDrawOFF(batch);

        logout_button.draw(batch);
        erase_button.draw(batch);
        batch.end();
        update(v);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        cross_button.set_Position(viewport.getWorldWidth() / 2 + 450, viewport.getWorldHeight() / 2 + 200);
        Sound_button.set_Position(viewport.getWorldWidth() / 2 - 400, viewport.getWorldHeight() / 2 -70);
        music_button.set_Position(viewport.getWorldWidth() / 2 - 200, viewport.getWorldHeight() / 2 -70);
        logout_button.set_Position(viewport.getWorldWidth() / 2 + 50, viewport.getWorldHeight() / 2 - 30);
        erase_button.set_Position(viewport.getWorldWidth() / 2 + 50, viewport.getWorldHeight() / 2 - 200);
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
        cross_after.dispose();
        cross_before.dispose();
        Sound_after.dispose();
        Sound_before.dispose();
        music_after.dispose();
        music_before.dispose();
        logout_after.dispose();
        logout_before.dispose();
        erase_after.dispose();
        erase_before.dispose();


    }
}
