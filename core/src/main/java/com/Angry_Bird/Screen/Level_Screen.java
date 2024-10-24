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

import static java.lang.System.exit;

public class Level_Screen implements Screen {
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

    private Texture level_F;
    private Texture lock;

    private Texture levelB;
    private Texture levelA;

    private Click_Button level1_button;
    private Click_Button level2_button;
    private Click_Button level3_button;

    public Level_Screen(final launch game, MainMenuScreen mainMenuScreen) {
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
        if (level1_button.clicked()) {
            game.setScreen(new level1(game));
        }
//        if (level2_button.clicked()) {
//            game.setScreen(new level2(game));
//        }
//        if (level3_button.clicked()) {
//            game.setScreen(new level3(game));
//        }
    }

    @Override
    public void show() {
        this.launch_image = new Texture("level.png");
        this.no_before = new Texture("back.png");
        this.no_after = new Texture("backA.png");
        this.level_F = new Texture("levelFrame.png");
        this.lock = new Texture("lock.png");
        this.levelB = new Texture("levelFrameB.png");
        this.levelA = new Texture("levelFrameA.png");

        this.no_Button = new Click_Button(no_before, no_after, no_before.getWidth()+50, viewport.getWorldHeight() - 50 - no_before.getHeight(), camera);
        no_Button.setInput(inputMultiplexer);

        this.level1_button = new Click_Button(levelB, levelA, 300,3*viewport.getWorldHeight() /4 -30, camera);
        level1_button.setInput(inputMultiplexer);

        this.level2_button = new Click_Button(levelB, levelA, 600,3*viewport.getWorldHeight() /4 -30, camera);
        level2_button.setInput(inputMultiplexer);

        this.level3_button = new Click_Button(levelB, levelA, 900,3*viewport.getWorldHeight() /4 -30, camera);
        level3_button.setInput(inputMultiplexer);

        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
//        font.getData().setScale(0.9f);
        batch.draw(launch_image,0, 0,  1920, 1080);

        font.getData().setScale(1.4f);
        font.setColor(Color.WHITE);
//        batch.draw(level_F, 300,3*viewport.getWorldHeight() /4 -30 , 200, 200);
        level1_button.draw(batch);
        font.draw(batch, "1", 370, viewport.getWorldHeight() -115);
//        batch.draw(level_F, 600,3*viewport.getWorldHeight() /4 -30 , 200, 200);
        level2_button.draw(batch);
        font.draw(batch, "2", 670, viewport.getWorldHeight() -115);
//        batch.draw(level_F, 900,3*viewport.getWorldHeight() /4 -30 , 200, 200);
        level3_button.draw(batch);
        font.draw(batch, "3", 970, viewport.getWorldHeight() -115);

        batch.draw(level_F, 1200,3*viewport.getWorldHeight()/4 -30 , 200, 200);
        batch.draw(lock, 1245, 3*viewport.getWorldHeight() /4 , 101, 130 );
        batch.draw(level_F, 1500,3*viewport.getWorldHeight()/4 -30 , 200, 200);
        batch.draw(lock, 1545, 3*viewport.getWorldHeight() /4 , 101, 130 );
        // 45 30 101 130
        batch.draw(level_F, 300,3*viewport.getWorldHeight() /4 -330, 200, 200);
        batch.draw(lock, 345, 3*viewport.getWorldHeight() /4 - 300, 101, 130 );
        batch.draw(level_F, 600,3*viewport.getWorldHeight() /4 -330, 200, 200);
        batch.draw(lock, 645, 3*viewport.getWorldHeight() /4 - 300, 101, 130 );
        batch.draw(level_F, 900,3*viewport.getWorldHeight() /4 -330, 200, 200);
        batch.draw(lock, 945, 3*viewport.getWorldHeight() /4 - 300, 101, 130 );
        batch.draw(level_F, 1200,3*viewport.getWorldHeight()/4 -330, 200, 200);
        batch.draw(lock, 1245, 3*viewport.getWorldHeight() /4 - 300, 101, 130 );
        batch.draw(level_F, 1500,3*viewport.getWorldHeight()/4 -330, 200, 200);
        batch.draw(lock, 1545, 3*viewport.getWorldHeight() /4 - 300, 101, 130 );


        batch.draw(level_F, 300,3*viewport.getWorldHeight() /4 -630, 200, 200);
        batch.draw(lock, 345, 3*viewport.getWorldHeight() /4 - 600, 101, 130 );
        batch.draw(level_F, 600,3*viewport.getWorldHeight() /4 -630, 200, 200);
        batch.draw(lock, 645, 3*viewport.getWorldHeight() /4 - 600, 101, 130 );
        batch.draw(level_F, 900,3*viewport.getWorldHeight() /4 -630, 200, 200);
        batch.draw(lock, 945, 3*viewport.getWorldHeight() /4 - 600, 101, 130 );
        batch.draw(level_F, 1200,3*viewport.getWorldHeight()/4 -630, 200, 200);
        batch.draw(lock, 1245, 3*viewport.getWorldHeight() /4 - 600, 101, 130 );
        batch.draw(level_F, 1500,3*viewport.getWorldHeight()/4 -630, 200, 200);
        batch.draw(lock, 1545, 3*viewport.getWorldHeight() /4 - 600, 101, 130 );
//        font.draw(batch, "ARE YOU SURE YOU ", viewport.getWorldWidth()/2 -400, viewport.getWorldHeight()/2+200);
//        font.draw(batch, "WANT TO QUIT?", viewport.getWorldWidth()/2 -310, viewport.getWorldHeight()/2+80);
        no_Button.draw(batch);
        font.setColor(Color.WHITE);
        batch.end();
        update(v);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
        camera.position.set(viewport.getWorldWidth() / 2 , viewport.getWorldHeight() / 2, 0);
        camera.update();

        no_Button.set_Position(50, viewport.getWorldHeight() - 40 - no_before.getHeight());
        level1_button.set_Position(300,3*viewport.getWorldHeight() /4 -30);
        level2_button.set_Position(600,3*viewport.getWorldHeight() /4 -30);
        level3_button.set_Position(900,3*viewport.getWorldHeight() /4 -30);
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
        level_F.dispose();
        lock.dispose();
    }
}
