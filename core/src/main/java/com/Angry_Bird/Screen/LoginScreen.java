package com.Angry_Bird.Screen;

import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
    private AssetManager assetManager;

    private BitmapFont font;
    private BitmapFont font2;

    private Click_Button cross_button;
    private Texture cross_before;
    private Texture cross_after;

    private InputMultiplexer input_multiplexer;
    private Stage stage;

    private MainMenuScreen main_menu_screen;

    public Click_Button login_button;
    private Texture login_before;
    private Texture login_after;

    public Click_Button sign_in_button;
    private Texture sign_in_before;
    private Texture sign_in_after;

    private Texture bar;
    public TextField usernameField;
    public TextField passwordField;
    private Skin skin;
    TextFieldStyle textFieldStyle ;

    private float renderDuration = 0;
    private boolean login_success = false;
    private boolean sign_in_success = false;

    public boolean login_status(){
        return login_success || sign_in_success;
    }
    public void updateStatus(){
        this.login_success = false;
        this.sign_in_success = false;
    }
    public String getUserid(){
        return usernameField.getText();
    }


    public LoginScreen(final launch game, MainMenuScreen main_menu_screen) {
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        this.game = game;
        this.main_menu_screen = main_menu_screen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.assetManager = game.getAssetManager();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.font2 = new BitmapFont();
        font2.setColor(Color.GRAY);
        this.textFieldStyle = new TextFieldStyle();
        this.input_multiplexer = new InputMultiplexer();
        this.stage = new Stage(viewport, batch);
        input_multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(input_multiplexer);

        setupTextFields();
    }

    private void setupTextFields() {

        textFieldStyle.font = font2;
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.font.getData().setScale(3f);
        textFieldStyle.fontColor = new Color(Color.WHITE);
        Texture cursor = new Texture("cursor.png");
        textFieldStyle.cursor = new TextureRegionDrawable(new TextureRegion(cursor));

        textFieldStyle.cursor.setMinWidth(15);
        textFieldStyle.cursor.setMinHeight(15);
        usernameField = new TextField("",textFieldStyle);

        usernameField.setStyle(textFieldStyle);
        usernameField.setPosition(viewport.getWorldWidth() / 2 +30, viewport.getWorldHeight() / 2 + 40);
        usernameField.setSize(350, 80);
//        usernameField.setMessageText("Username");


        passwordField = new TextField("", textFieldStyle);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        passwordField.setStyle(textFieldStyle);
        passwordField.setPosition(viewport.getWorldWidth() / 2 +30, viewport.getWorldHeight() / 2 - 100);
        passwordField.setSize(350, 80);
//        passwordField.setMessageText("Enter Password");

        stage.addActor(usernameField);
        stage.addActor(passwordField);
    }
    private void login(float delta){
        if (!game.found_id(usernameField.getText())){
            font.setColor(Color.RED);
            font.getData().setScale(0.4f);
            renderDuration += delta;
//            System.out.printf("%f, %f\n",delta, renderDuration);
            if (renderDuration <= 1){
                font.draw(batch, "user not found", viewport.getWorldWidth() / 2 - 150, viewport.getWorldHeight() / 2-110);
            }
            font.setColor(Color.WHITE);

        }else{
            if (!game.found_password(usernameField.getText(), passwordField.getText())){
                font.setColor(Color.RED);
                font.getData().setScale(0.4f);
                renderDuration += delta;
                if (renderDuration <= 1){
                    font.draw(batch, "wrong password", viewport.getWorldWidth() / 2 - 150, viewport.getWorldHeight() / 2-110);
                }
                font.setColor(Color.WHITE);


            }else {
                login_success = true;
                game.setScreen(game.getLevelScreen());
            }
        }
    }

    private void signUp(float delta){

        if (usernameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty()) {
            font.setColor(Color.RED);
            font.getData().setScale(0.4f);
            renderDuration += delta;
            if (renderDuration <= 1){
                font.draw(batch, "Details cannot be empty", viewport.getWorldWidth() / 2 - 150, viewport.getWorldHeight() / 2-110);
            }
            font.setColor(Color.WHITE);
            if (renderDuration >= 1){
                sign_in_button.setOff();
                renderDuration = 0;
            }
            return;
        }

        if (game.found_id(usernameField.getText())) {
                font.setColor(Color.RED);
                font.getData().setScale(0.4f);
                renderDuration += delta;
                System.out.printf("%f %f\n", delta, renderDuration);
                if (renderDuration <= 1){
                    font.draw(batch, "username exist", viewport.getWorldWidth() / 2 - 150, viewport.getWorldHeight() / 2-110);
                }
                font.setColor(Color.WHITE);


            }else {
                sign_in_success = true;
                game.signUp(usernameField.getText(), passwordField.getText());
                game.setScreen(game.getLevelScreen());
            }

    }

    public void update(float delta) {
        batch.begin();
        if (cross_button.clicked()) {
            game.setScreen(game.getMainMenuScreen());
        }
        if (login_button.isOn()) {
            login(delta);
            if (renderDuration >= 1 && !login_success){
                login_button.setOff();
                renderDuration = 0;
            }
        }
        if (sign_in_button.isOn()) {
            signUp(delta);
            game.signUp(usernameField.getText(), passwordField.getText());
            if (renderDuration >= 1 && !sign_in_success){
                sign_in_button.setOff();
                renderDuration = 0;
            }
        }
        batch.end();
    }

    @Override
    public void show() {
        this.launch_image = assetManager.get("template.png", Texture.class);

        this.cross_before = assetManager.get("4B.png", Texture.class);
        this.cross_after = assetManager.get("4A.png", Texture.class);
        this.bar = assetManager.get("bar.png", Texture.class);
        this.cross_button = new Click_Button(cross_before, cross_after, viewport.getWorldWidth() / 2 + 200, viewport.getWorldHeight() / 2 + 300, camera);
        cross_button.setInput(input_multiplexer);

        this.login_before = assetManager.get("loginB.png", Texture.class);
        this.login_after = assetManager.get("loginA.png", Texture.class);
        this.login_button = new Click_Button(login_before, login_after, viewport.getWorldWidth() / 2 - 450, viewport.getWorldHeight() / 2 - 300, camera);
        login_button.setInput(input_multiplexer);

        this.sign_in_before = assetManager.get("signB.png", Texture.class);
        this.sign_in_after = assetManager.get("signA.png", Texture.class);
        this.sign_in_button = new Click_Button(sign_in_before, sign_in_after, viewport.getWorldWidth() / 2 + 50, viewport.getWorldHeight() / 2 - 300, camera);
        sign_in_button.setInput(input_multiplexer);

        Gdx.input.setInputProcessor(input_multiplexer);
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

        font.getData().setScale(0.6f);
        font.draw(batch, "User name", viewport.getWorldWidth() / 2 - 400, viewport.getWorldHeight() / 2 + 100);
        font.draw(batch, "Password", viewport.getWorldWidth() / 2 - 400, viewport.getWorldHeight() / 2 - 35);

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

