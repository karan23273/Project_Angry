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
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;
    private SpriteBatch batch;

    private Texture launch_image;
    private Texture play_before;
    private Texture play_after;
    private Click_Button play_button;
    private Click_Button setting_button;
    private Texture setting_before;
    private Texture setting_after;
    private InputMultiplexer input_multiplexer;
    private Click_Button exit_button;
    private Texture exit_before;
    private Texture exit_after;

    private Click_Button shop_button;
    private Texture shop_before;
    private Texture shop_after;

    private Texture fame;
    private Texture usernameBg;

    private BitmapFont font;

    private Texture coin;

    public MainMenuScreen(final launch game) {
        this.game = game;
        camera = game.getCamera();
        viewport = game.getViewport();
        this.batch = game.getBatch();
        this.assetManager = game.getAssetManager();
        this.font = game.getFont();

        this.input_multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(input_multiplexer);
    }

    @Override
    public void show() {

        this.launch_image = assetManager.get("main.png", Texture.class);
        this.play_before = assetManager.get("1B.png", Texture.class);
        this.play_after = assetManager.get("1D.png", Texture.class);
        this.setting_before = assetManager.get("2B.png", Texture.class);
        this.setting_after = assetManager.get("2A.png", Texture.class);
        this.exit_before = assetManager.get("4B.png", Texture.class);
        this.exit_after = assetManager.get("4A.png", Texture.class);
        this.shop_before = assetManager.get("shopB.png", Texture.class);
        this.shop_after = assetManager.get("shopA.png", Texture.class);
        this.coin = assetManager.get("coinB.png", Texture.class);
        this.fame = assetManager.get("frame.png", Texture.class);
        this.usernameBg = assetManager.get("frame.png", Texture.class);


        this.play_button = new Click_Button(play_before, play_after, (viewport.getWorldWidth() - play_before.getWidth())/2, (viewport.getWorldHeight()-play_before.getHeight())/2 ,camera);
        play_button.setInput(input_multiplexer);
        this.setting_button = new Click_Button(setting_before, setting_after, 10, 10, camera);
        setting_button.setInput(input_multiplexer);
        this.exit_button = new Click_Button(exit_before, exit_after, viewport.getWorldWidth() - exit_before.getWidth() - 10, viewport.getWorldHeight() - 150, camera);
        exit_button.setInput(input_multiplexer);
        this.shop_button = new Click_Button(shop_before, shop_after, viewport.getWorldWidth() - 450, 10, camera);
        shop_button.setInput(input_multiplexer);

        Gdx.input.setInputProcessor(input_multiplexer);
    }

    public void update(float delta) {
        if (play_button.clicked()){
            if (game.getLoginScreen().login_status()){
                game.setScreen(game.getLevelScreen());
            }else {
                game.setScreen(game.getLoginScreen());
            }
        }
        if (setting_button.clicked()){
            game.setScreen(game.getSettingScreen());
        }
        if (exit_button.clicked()){
            game.setScreen(game.getExitScreen());
        }
        if (shop_button.clicked()){
            game.setScreen(game.getShopScreen());
        }
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(launch_image,0, 0,  viewport.getWorldWidth(), viewport.getWorldHeight());

        if (game.getLoginScreen().login_status()) {
            GlyphLayout usernameLayout = new GlyphLayout();
            usernameLayout.setText(font, "User: " + game.getLoginScreen().getUserid());
            batch.draw(usernameBg, 10, viewport.getWorldHeight() - 180, usernameLayout.width, 80);
            font.getData().setScale(0.4f);
            font.draw(batch, "User: " + game.getLoginScreen().getUserid(), 30, viewport.getWorldHeight() - 120);
        }

        // this coin will be taken from date
        Integer c = game.getCoin();
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, c.toString());

        batch.draw(fame, 10, viewport.getWorldHeight() - 90, glyphLayout.width + 150 ,80);
        batch.draw(coin, 20, viewport.getWorldHeight() - 75, 55 ,55);
        font.getData().setScale(0.5f);
        font.draw(batch, c.toString(), 100 , viewport.getWorldHeight() - 25);
        play_button.draw(batch);
        setting_button.draw(batch);
        exit_button.draw(batch);
        shop_button.draw(batch);
        batch.end();
        update(v);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update( i, i1);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
        camera.update();
        play_button.set_Position((viewport.getWorldWidth() - play_before.getWidth())/2, (viewport.getWorldHeight() - play_before.getHeight())/2);
        setting_button.set_Position(10,10);
        exit_button.set_Position(viewport.getWorldWidth() - exit_before.getWidth() - 10, viewport.getWorldHeight() - 150);
        shop_button.set_Position(viewport.getWorldWidth() - 450, 10);
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
        play_before.dispose();
        play_after.dispose();
        setting_before.dispose();
        setting_after.dispose();
        exit_before.dispose();
        exit_after.dispose();
        shop_before.dispose();
        shop_after.dispose();
        fame.dispose();
        coin.dispose();
        usernameBg.dispose();


    }
}
