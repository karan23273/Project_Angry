package com.Angry_Bird.Screen;

import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private Stage stage;

    private MainMenuScreen main_menu_screen;

    private Click_Button login_button;
    private Texture login_before;
    private Texture login_after;

    private Click_Button sign_in_button;
    private Texture sign_in_before;
    private Texture sign_in_after;

    private Texture bar;
    private TextField usernameField;
    private TextField passwordField;
    private Skin skin;
    TextFieldStyle textFieldStyle ;

    public LoginScreen(final launch game, MainMenuScreen main_menu_screen) {
        this.game = game;
        this.main_menu_screen = main_menu_screen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.font2 = game.getFont();
        this.textFieldStyle = new TextFieldStyle();
        this.input_multiplexer = new InputMultiplexer();
        this.stage = new Stage(viewport, batch);
        input_multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(input_multiplexer);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        setupTextFields();
    }

    private void setupTextFields() {

        textFieldStyle.font = font2;
        textFieldStyle.font.getData().setScale(0.5f);
        textFieldStyle.fontColor = new Color(Color.GRAY);
        Texture cursor = new Texture("cursor.png");
        textFieldStyle.cursor = new TextureRegionDrawable(new TextureRegion(cursor));
        textFieldStyle.cursor.setBottomHeight(0);
        textFieldStyle.cursor.setTopHeight(0);
        textFieldStyle.cursor.setRightWidth(0);
        textFieldStyle.cursor.setLeftWidth(0);

        textFieldStyle.cursor.setMinWidth(15);
        textFieldStyle.cursor.setMinHeight(15);
        usernameField = new TextField("",textFieldStyle);

        usernameField.setStyle(textFieldStyle);
//
        usernameField.setPosition(viewport.getWorldWidth() / 2 +30, viewport.getWorldHeight() / 2 + 45);
        usernameField.setSize(350, 80);

        passwordField = new TextField("", textFieldStyle);
        passwordField.setPasswordMode(true);
        passwordField.setStyle(textFieldStyle);
        passwordField.setPosition(viewport.getWorldWidth() / 2 +30, viewport.getWorldHeight() / 2 - 95);
        passwordField.setSize(350, 80);

        stage.addActor(usernameField);
        stage.addActor(passwordField);
        textFieldStyle.fontColor = new Color(Color.WHITE);
    }

    private void update(float delta) {
        if (cross_button.clicked()) {
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
        this.bar = new Texture("bar.png");
        this.cross_button = new Click_Button(cross_before, cross_after, viewport.getWorldWidth() / 2 + 200, viewport.getWorldHeight() / 2 + 300, camera);
        cross_button.setInput(input_multiplexer);

        this.login_before = new Texture("loginB.png");
        this.login_after = new Texture("loginA.png");
        this.login_button = new Click_Button(login_before, login_after, viewport.getWorldWidth() / 2 - 450, viewport.getWorldHeight() / 2 - 300, camera);
        login_button.setInput(input_multiplexer);

        this.sign_in_before = new Texture("signB.png");
        this.sign_in_after = new Texture("signA.png");
        this.sign_in_button = new Click_Button(sign_in_before, sign_in_after, viewport.getWorldWidth() / 2 + 50, viewport.getWorldHeight() / 2 - 300, camera);
        sign_in_button.setInput(input_multiplexer);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(launch_image, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        font.getData().setScale(0.8f);
        font.draw(batch, "LOGIN", viewport.getWorldWidth() / 2 - 140, viewport.getWorldHeight() / 2 + 220);

        font2.getData().setScale(0.6f);
        font2.draw(batch, "User name", viewport.getWorldWidth() / 2 - 400, viewport.getWorldHeight() / 2 + 100);
        font2.draw(batch, "Password", viewport.getWorldWidth() / 2 - 400, viewport.getWorldHeight() / 2 - 35);

        batch.draw(bar, viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2 + 40, 400, 70);
        batch.draw(bar, viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2 - 100, 400, 70);

        cross_button.draw(batch);
        login_button.draw(batch);
        sign_in_button.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime()); // Update stage and text fields
        stage.draw(); // Draw stage and text fields

        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();

        cross_button.set_Position(viewport.getWorldWidth() / 2 + 450, viewport.getWorldHeight() / 2 + 200);
        login_button.set_Position(viewport.getWorldWidth() / 2 - 450, viewport.getWorldHeight() / 2 - 300);
        sign_in_button.set_Position(viewport.getWorldWidth() / 2 + 50, viewport.getWorldHeight() / 2 - 300);
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
        cross_after.dispose();
        cross_before.dispose();
        login_after.dispose();
        login_before.dispose();
        sign_in_after.dispose();
        sign_in_before.dispose();
        bar.dispose();
        stage.dispose(); // Dispose of the stage to prevent memory leaks
    }
}
