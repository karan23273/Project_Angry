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

public class LoginScreen implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private SpriteBatch batch;
    private Texture launch_image;

    private BitmapFont font;
    private BitmapFont font2;

    private Click_Button cross_button;
    private Texture cross_before;
    private Texture cross_after;

    private InputMultiplexer input_multiplexer;

    private MainMenuScreen main_menu_screen;

    private Click_Button login_button;
    private Texture login_before;
    private Texture login_after;

    private Click_Button sign_in_button;
    private Texture sign_in_before;
    private Texture sign_in_after;


    public LoginScreen(final launch game, MainMenuScreen main_menu_screen) {
        this.game = game;
        this.main_menu_screen = main_menu_screen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.font2 = game.getFont();
        this.input_multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(input_multiplexer);
    }
    private void update(float delta) {
        if (cross_button.clicked()){
            game.setScreen(main_menu_screen);
        } else if (login_button.clicked()) {
            game.setScreen(new Level_Screen(game, main_menu_screen));
        } else if (sign_in_button.clicked()) {
            game.setScreen(new Level_Screen(game, main_menu_screen));
        }
    }

    @Override
    public void show() {
        this.launch_image = new Texture("template.png");

        this.cross_before = new Texture("4B.png");
        this.cross_after = new Texture("4A.png");
        this.cross_button = new Click_Button(cross_before, cross_after, viewport.getWorldWidth() / 2 + 200, viewport.getWorldHeight() / 2+300, camera);
        cross_button.setInput(input_multiplexer);

        this.login_before = new Texture("loginB.png");
        this.login_after = new Texture("loginA.png");
        this.login_button = new Click_Button(login_before, login_after, viewport.getWorldWidth() / 2 - 450, viewport.getWorldHeight() / 2 - 300, camera);
        login_button.setInput(input_multiplexer);

        this.sign_in_before = new Texture("signB.png");
        this.sign_in_after = new Texture("signA.png");
        this.sign_in_button = new Click_Button(sign_in_before, sign_in_after, viewport.getWorldWidth() / 2 + 50, viewport.getWorldHeight() / 2 - 300, camera);
        sign_in_button.setInput(input_multiplexer);

        Gdx.input.setInputProcessor(input_multiplexer);

    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(launch_image,0, 0,  viewport.getWorldWidth(), viewport.getWorldHeight());
        font.getData().setScale(0.8f);
        font.draw(batch, "LOGIN", viewport.getWorldWidth() / 2-140, viewport.getWorldHeight() / 2+220);
        font2.getData().setScale(0.6f);
        font2.draw(batch, "User name",viewport.getWorldWidth() / 2 - 400, viewport.getWorldHeight() / 2 + 100);
        font2.draw(batch, "Password",viewport.getWorldWidth() / 2 - 400, viewport.getWorldHeight() / 2 - 35);

        cross_button.draw(batch);
        login_button.draw(batch);
        sign_in_button.draw(batch);

        batch.end();

        update(v);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        cross_button.set_Position(viewport.getWorldWidth() / 2 + 450, viewport.getWorldHeight() / 2 + 200);

        login_button.set_Position(viewport.getWorldWidth() / 2 - 450, viewport.getWorldHeight() / 2 - 300);

        sign_in_button.set_Position(viewport.getWorldWidth() / 2 + 50, viewport.getWorldHeight() / 2 - 300);
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
        login_after.dispose();
        login_before.dispose();
        sign_in_after.dispose();
        sign_in_before.dispose();

    }
}
