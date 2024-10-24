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

public class MainMenuScreen implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;
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

    private BitmapFont font;

    private Texture coin;
    public MainMenuScreen(final launch game) {
        this.game = game;
        camera = game.getCamera();
        viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.input_multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(input_multiplexer);
    }

    @Override
    public void show() {
//        this.batch = new SpriteBatch();
        this.launch_image = new Texture("main.png");
        this.play_before = new Texture("1B.png");
        this.play_after = new Texture("1D.png");
        this.setting_before = new Texture("2B.png");
        this.setting_after = new Texture("2A.png");
        this.exit_before = new Texture("4B.png");
        this.exit_after = new Texture("4A.png");
        this.shop_before = new Texture("shopB.png");
        this.shop_after = new Texture("shopA.png");
        this.coin = new Texture("coinB.png");
        this.fame = new Texture("frame.png");

        this.play_button = new Click_Button(play_before, play_after, (viewport.getWorldWidth() - play_before.getWidth())/2, (viewport.getWorldHeight()-play_before.getHeight())/2 ,camera);
        play_button.setInput(input_multiplexer);
        this.setting_button = new Click_Button(setting_before, setting_after, 10, 10, camera);
        setting_button.setInput(input_multiplexer);
        this.exit_button = new Click_Button(exit_before, exit_after, viewport.getWorldWidth() - exit_before.getWidth() - 10, viewport.getWorldHeight() - 150, camera);
        exit_button.setInput(input_multiplexer);
        this.shop_button = new Click_Button(shop_before, shop_after, viewport.getWorldWidth() - 450, 10, camera);
        shop_button.setInput(input_multiplexer);

        Gdx.input.setInputProcessor(input_multiplexer); /// VIP
    }

    public void update(float delta) {
        if (play_button.clicked()){
            game.setScreen(new LoginScreen(game, this));
        }
        if (setting_button.clicked()){
            game.setScreen(new Setting_Screen(game, this));
        }
        if (exit_button.clicked()){
            game.setScreen(new Exit_Screen(game, this));
        }
        if (shop_button.clicked()){
            game.setScreen(new Shop_Screen(game, this));
        }
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(launch_image,0, 0,  viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.draw(fame, 10, viewport.getWorldHeight() - 90, 260 ,80);
        batch.draw(coin, 20, viewport.getWorldHeight() - 75, 55 ,55);

        // this coin will be teken from date
        Integer c = game.getCoin();
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
        coin.dispose();

    }
}
