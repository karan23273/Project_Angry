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

public class Level_Passed implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private SpriteBatch batch;
    private Texture launch_image;
    private BitmapFont font;

    private Click_Button no_Button;

    private Texture no_before;
    private Texture no_after;


    private Click_Button restart_button;
    private Texture restartB;
    private Texture restartA;

    private Click_Button menu;
    private Texture menuB;
    private Texture menuA;


    private InputMultiplexer inputMultiplexer;

    private level1 level;



    public Level_Passed(final launch game, level1 level) {
        this.game = game;
        this.level = level;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.inputMultiplexer = new InputMultiplexer();

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void update(float delta) {
        if (no_Button.clicked()) {
//            game.setScreen(level);
        }
        if (restart_button.clicked()){
            game.setScreen(new level1(game));
        }
        if (menu.clicked()){
            game.setScreen(new Level_Screen(game, new MainMenuScreen(game)));
        }
    }

    @Override
    public void show() {
//        this.launch_image = new Texture("pausePage.png");
        this.launch_image = new Texture("Level clear.png");
        this.no_before = new Texture("front.png");
        this.no_after = new Texture("frontA.png");
        this.restartB = new Texture("restartB.png");
        this.restartA = new Texture("restartA.png");
        this.menuB = new Texture("level menuB.png");
        this.menuA = new Texture("level menuA.png");

        this.no_Button = new Click_Button(no_before, no_after, viewport.getWorldWidth()/2 + 200, 100, camera);
        no_Button.setInput(inputMultiplexer);
        this.restart_button = new Click_Button(restartB, restartA, viewport.getWorldWidth()/2 -50, 100, camera);
        restart_button.setInput(inputMultiplexer);
        this.menu = new Click_Button(menuB, menuA, viewport.getWorldWidth()/2 -300, 100, camera);
        menu.setInput(inputMultiplexer);


        Gdx.input.setInputProcessor(inputMultiplexer);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(new Texture("next level Bg.png"), 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
//        batch.draw(launch_image, 0, viewport.getWorldHeight()/2, viewport.getWorldWidth(), viewport.getWorldHeight()-82);
        batch.draw(launch_image, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight()-82);
        no_Button.draw(batch);
        restart_button.draw(batch);
        menu.draw(batch);
        batch.end();

        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        no_Button.set_Position(viewport.getWorldWidth()/2 + 200, 100);
        restart_button.set_Position(viewport.getWorldWidth()/2 - 50, 100);
        menu.set_Position(viewport.getWorldWidth()/2 -300, 100);
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
    }
}
