package com.Angry_Bird.Screen;

import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
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

    private Click_Button senstivity_button;
    private Texture senstivity_before;
    private Texture senstivity_after;

    public Setting_Screen(final launch game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.input_multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(input_multiplexer);

    }

    private void update(float delta) {
        if (cross_button.clicked()){
            game.setScreen(mainMenuScreen);
        }else if (Sound_button.clicked()){
            //
        } else if (music_button.clicked()) {
            //
        }
    }

    @Override
    public void show() {
        this.launch_image = new Texture("template.png");

        this.cross_before = new Texture("4B.png");
        this.cross_after = new Texture("4A.png");
        this.cross_button = new Click_Button(cross_before, cross_after, viewport.getWorldWidth() / 2 + 200, viewport.getWorldHeight() / 2+300, camera);
        cross_button.setInput(input_multiplexer);

        this.Sound_before = new Texture("5B.png");
        this.Sound_after = new Texture("5A.png");
        this.Sound_button = new Click_Button(Sound_before, Sound_after, viewport.getWorldWidth()/2-400, viewport.getWorldHeight()/2-70, camera);
        Sound_button.setInput(input_multiplexer);

        this.music_before = new Texture("6B.png");
        this.music_after = new Texture("6A.png");
        this.music_button = new Click_Button(music_before, music_after, viewport.getWorldWidth()/2-200, viewport.getWorldHeight()/2-70, camera);
        music_button.setInput(input_multiplexer);

        this.senstivity_before = new Texture("senstivityB.png");
        this.senstivity_after = new Texture("senstivityA.png");
        this.senstivity_button = new Click_Button(senstivity_before, senstivity_after, viewport.getWorldWidth()/2 + 50, viewport.getWorldHeight()/2 -30, camera);
        this.senstivity_button.setInput(input_multiplexer);

        this.erase_before = new Texture("eraseB.png");
        this.erase_after = new Texture("eraseA.png");
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
        Sound_button.toggleDraw(batch);
        music_button.toggleDraw(batch);
        senstivity_button.draw(batch);
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
        senstivity_button.set_Position(viewport.getWorldWidth() / 2 + 50, viewport.getWorldHeight() / 2 - 30);
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
        senstivity_after.dispose();
        senstivity_before.dispose();
        erase_after.dispose();
        erase_before.dispose();


    }
}
