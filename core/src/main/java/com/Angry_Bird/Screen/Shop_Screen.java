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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class Shop_Screen implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;

    private SpriteBatch batch;
    private Texture launch_image;
    private BitmapFont font;

    private Click_Button no_Button;

    private Texture no_before;

    private Texture no_after;

    private InputMultiplexer inputMultiplexer;
    private MainMenuScreen mainMenuScreen;

    private Texture coins_shop;

    private Texture head;

    private Click_Button buy2000;
    private Click_Button buy10000;

    private Texture buttonB;
    private Texture buttonA;

    private  Texture buyBird;
    public Shop_Screen(final launch game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.assetManager = game.getAssetManager();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void update(float delta) {
        if (no_Button.clicked()) {
            game.setScreen(mainMenuScreen);
        }
        if (buy2000.isOn()){
            game.buy_coin(2000);
            buy2000.setOff();
        }
        if(buy10000.isOn()){
            game.buy_coin(10000);
            buy10000.setOff();
        }
    }

    @Override
    public void show() {
        this.launch_image = assetManager.get("210869.jpg", Texture.class);
        this.no_before = assetManager.get("4B.png", Texture.class);
        this.no_after = assetManager.get("4A.png", Texture.class);
        this.coins_shop = assetManager.get("coins shop.png", Texture.class);
        this.head = assetManager.get("heading.png", Texture.class);
        this.buttonA = assetManager.get("buttonA.png", Texture.class);
        this.buttonB = assetManager.get("buttonB.png", Texture.class);
        this.buyBird = assetManager.get("buy bird.png", Texture.class);

        this.no_Button = new Click_Button(no_before, no_after, viewport.getWorldWidth() - 150, viewport.getWorldHeight()-150, camera);
        no_Button.setInput(inputMultiplexer);

        this.buy2000 = new Click_Button(buttonB, buttonB, 200, 150, camera);
        buy2000.setInput(inputMultiplexer);
        this.buy10000 = new Click_Button(buttonB, buttonB, 800, 150, camera);
        buy10000.setInput(inputMultiplexer);
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
        batch.draw(head, 10, viewport.getWorldHeight() - 300, viewport.getWorldWidth()/2, viewport.getWorldHeight()/4);
        batch.draw(coins_shop, 0,0, 3*viewport.getWorldWidth()/4 , 3*viewport.getWorldHeight()/4);
        font.setColor(Color.WHITE);
        font.draw(batch, "SHOP NOW ", 200, viewport.getWorldHeight() - 115);
//        buy2000.draw(batch);
//        buy10000.draw(batch);
        font.getData().setScale(0.7f);
        font.setColor(247f,202f,22f,0.8f);
        font.setColor(Color.GOLDENROD);
        font.draw(batch, " 2000", 300, 194);
        font.draw(batch, " 10000", 930, 194);
        no_Button.draw(batch);
        batch.draw(buyBird, 1300, 80, 500, 500);
        batch.end();
        font.setColor(Color.WHITE);
        update(v);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();

        no_Button.set_Position(viewport.getWorldWidth()-150, viewport.getWorldHeight()-150);
        buy2000.set_Position(200, 150);
        buy10000.set_Position(800, 150);
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
        no_before.dispose();
        no_after.dispose();
        coins_shop.dispose();
        head.dispose();
        buttonA.dispose();
        buttonB.dispose();
    }
}
