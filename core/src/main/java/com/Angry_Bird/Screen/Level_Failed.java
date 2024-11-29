package com.Angry_Bird.Screen;

import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level_Failed implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;

    private SpriteBatch batch;
    private Texture launch_image;
    private BitmapFont font;

    private Click_Button restart_button;
    private Texture restartB;
    private Texture restartA;

    private Click_Button menu;
    private Texture menuB;
    private Texture menuA;

    private int CurrLevel;
    public void setCurrLevel(int level) {
        CurrLevel = level;
    }


    private InputMultiplexer inputMultiplexer;

    private level1 level;



    public Level_Failed(final launch game, level1 level) {
        this.game = game;
        this.level = level;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.assetManager = game.getAssetManager();
        this.inputMultiplexer = new InputMultiplexer();

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void update(float delta) {
        if (restart_button.clicked()){
            game.setWorld(new World(new Vector2(0, -9.8f), true));
            if (CurrLevel == 1) {
                game.setLevel_1(new level1(game));
                game.setScreen(game.getLevel_1());
            } else if (CurrLevel == 2) {
                game.setLevel_2(new level2(game));
                game.setScreen(game.getLevel_2());
            }
            else if (CurrLevel == 3) {
                game.setLevel_3(new level3(game));
                game.setScreen(game.getLevel_3());
            }
        }
        if (menu.clicked()){
            game.setScreen(new Level_Screen(game, new MainMenuScreen(game)));
        }
    }

    @Override
    public void show() {
//        this.launch_image = new Texture("pausePage.png");
        this.launch_image = assetManager.get("Level failed2.png", Texture.class);
//        this.launch_image = new Texture("Level faild (1).png");
        this.restartB = assetManager.get("restartB.png", Texture.class);
        this.restartA = assetManager.get("restartA.png", Texture.class);
        this.menuB = assetManager.get("level menuB.png", Texture.class);
        this.menuA = assetManager.get("level menuA.png", Texture.class);

        this.restart_button = new Click_Button(restartB, restartA, viewport.getWorldWidth()/2 -50, 100, camera);
        restart_button.setInput(inputMultiplexer);
        this.menu = new Click_Button(menuB, menuA, viewport.getWorldWidth()/2 -300, 100, camera);
        menu.setInput(inputMultiplexer);


        Gdx.input.setInputProcessor(inputMultiplexer);


    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
//        Gdx.gl.glEnable(GL20.GL_BLEND);
//        game.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
//        game.getShapeRenderer().setColor(0, 0, 0, 0.4f);
//        game.getShapeRenderer().rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
//        game.getShapeRenderer().end();
//        Gdx.gl.glDisable(GL20.GL_BLEND);
//        batch.setColor(1, 1, 1, 0.2f);
//        Gdx.graphics.setContinuousRendering(false);
        batch.begin();
//        batch.draw(new Texture("Level failed Bg.png"), 600, 0, 750, viewport.getWorldHeight());
//        batch.draw(launch_image, 0, viewport.getWorldHeight()/2, viewport.getWorldWidth(), viewport.getWorldHeight()-82);
//        batch.setColor(1, 1, 1, 1f);
        batch.draw(launch_image,  600, 0, 750, viewport.getWorldHeight());
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
    }
}
