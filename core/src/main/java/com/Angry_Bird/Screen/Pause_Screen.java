package com.Angry_Bird.Screen;

import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Pause_Screen implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;
    private SpriteBatch batch;
    private Texture launch_image;
    private BitmapFont font;

    private Click_Button continue_button;
    private Texture no_before;
    private Texture no_after;

    private Click_Button restart_button;
    private Texture restartB;
    private Texture restartA;

    private Click_Button menu;
    private Texture menuB;
    private Texture menuA;

    private InputMultiplexer inputMultiplexer;

    private Screen previousScreen;

    private Click_Button Sound_button;
    private Texture Sound_before;
    private Texture Sound_after;

    private Click_Button music_button;
    private Texture music_before;
    private Texture music_after;

    private int currLevel;
    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

    public Pause_Screen(final launch game) {
        this.game = game;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.assetManager = game.getAssetManager();
        this.inputMultiplexer = new InputMultiplexer();

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void update(float delta) {
        if (continue_button.clicked()) {
            if (currLevel == 1) {
                game.setWorld(new World(new Vector2(0, -9.8f), true));
                game.setScreen(game.getLevel_1());
            } else if (currLevel == 2) {
//                game.setWorld(new World(new Vector2(0, -9.8f),true));
                game.setWorld(new World(new Vector2(0, -9.8f), true));
                game.setScreen(game.getLevel_2());
            }
            else if (currLevel == 3) {
                game.setWorld(new World(new Vector2(0, -9.8f),true));
                game.setScreen(game.getLevel_3());
            }
        }
        if (restart_button.clicked()) {
            game.setWorld(new World(new Vector2(0, -9.8f), true));
            if (currLevel == 1) {
                game.setLevel_1(new level1(game));
                game.setScreen(game.getLevel_1());
            } else if (currLevel == 2) {
                game.setLevel_2(new level2(game));
                game.setScreen(game.getLevel_2());
            }
            else if (currLevel == 3) {
                game.setLevel_3(new level3(game));
                game.setScreen(game.getLevel_3());
            }
        }
        if (menu.clicked()) {
            game.setScreen(game.getLevelScreen());
        }
        if (Sound_button.isOn()) {
            game.resumeSound();
        } else if (!Sound_button.isOn()) {
            game.pauseSound();
        }
        if (music_button.isOn()) {
            game.playMusic();
        } else if (!music_button.isOn()) {
            game.pauseMusic();
        }
    }

    @Override
    public void show() {
        this.launch_image = assetManager.get("pausePage.png", Texture.class);
        this.no_before = assetManager.get("front.png", Texture.class);
        this.no_after = assetManager.get("frontA.png", Texture.class);
        this.restartB = assetManager.get("restartB.png", Texture.class);
        this.restartA = assetManager.get("restartA.png", Texture.class);
        this.menuB = assetManager.get("level menuB.png", Texture.class);
        this.menuA = assetManager.get("level menuA.png", Texture.class);

        this.continue_button = new Click_Button(no_before, no_after, viewport.getWorldWidth() - 150, viewport.getWorldHeight() - 150, camera);
        continue_button.setInput(inputMultiplexer);

        this.restart_button = new Click_Button(restartB, restartA, 150, viewport.getWorldHeight()-130, camera);
        restart_button.setInput(inputMultiplexer);

        this.menu = new Click_Button(menuB, menuA, 150, viewport.getWorldHeight()/2, camera);
        menu.setInput(inputMultiplexer);

        this.Sound_before = new Texture("5B.png");
        this.Sound_after = new Texture("5A.png");
        this.Sound_button = new Click_Button(Sound_before, Sound_after, 150, 100, camera);
        Sound_button.setInput(inputMultiplexer);
        if (game.is_sound_ON()) {
            Sound_button.setOn();
        } else {
            Sound_button.setOff();
        }

        this.music_before = new Texture("6B.png");
        this.music_after = new Texture("6A.png");
        this.music_button = new Click_Button(music_before, music_after, 150, 300, camera);
        music_button.setInput(inputMultiplexer);
        if (game.getMusic().isPlaying()) {
            music_button.setOn();
        } else {
            music_button.setOff();
        }

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        Gdx.graphics.setVSync(true);
        batch.begin();
        batch.draw(launch_image, 0, 0, 400, viewport.getWorldHeight());
        continue_button.draw(batch);
        restart_button.draw(batch);
        menu.draw(batch);
        if (Sound_button.isOn()) Sound_button.toggleDrawON(batch);
        else Sound_button.toggleDrawOFF(batch);

        if (music_button.isOn()) music_button.toggleDrawON(batch);
        else music_button.toggleDrawOFF(batch);
        batch.end();

        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        continue_button.set_Position(350, viewport.getWorldHeight() - 150);
        restart_button.set_Position(150, viewport.getWorldHeight()-300);
        menu.set_Position(150, viewport.getWorldHeight()/2);
        Sound_button.set_Position(150, 100);
        music_button.set_Position(150, 300);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        launch_image.dispose();
        no_before.dispose();
        no_after.dispose();
        restartB.dispose();
        restartA.dispose();
        menuB.dispose();
        menuA.dispose();
        Sound_before.dispose();
        Sound_after.dispose();
        music_before.dispose();
        music_after.dispose();
    }
}
