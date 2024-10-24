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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class Shop_Screen implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;

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

    public Shop_Screen(final launch game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void update(float delta) {
        if (no_Button.clicked()) {
            game.setScreen(mainMenuScreen);
        }
        if (buy2000.clicked()){
            game.buy_coin(2000);
        }
        if(buy10000.clicked()){
            game.buy_coin(10000);
        }
    }

    @Override
    public void show() {
        this.launch_image = new Texture("210869.jpg");
        this.no_before = new Texture("4B.png");
        this.no_after = new Texture("4A.png");
        this.coins_shop = new Texture("coins shop.png");
        this.head = new Texture("heading.png");
        this.buttonA = new Texture("buttonA.png");
        this.buttonB = new Texture("buttonB.png");


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
        buy2000.set_Position(180, 110);
        buy10000.set_Position(780, 110);
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
