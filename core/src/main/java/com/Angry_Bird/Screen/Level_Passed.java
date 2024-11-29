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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level_Passed implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;
    private SpriteBatch batch;
    private Texture launch_image;
    private BitmapFont font;

    private Click_Button nextLevelbutton;

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
    private level2 level2;
    private level3 level3;
    private Integer level_score;

    private int current_level;
    public void setcurrent_level(Integer current_level) {
        this.current_level = current_level;
    }

    private int next_level;

    public void setNext_level(int next_level) {
        this.next_level = next_level;
    }

    public void setLevel_score(Integer level_score) {
        this.level_score = level_score;
    }


    public Level_Passed(final launch game, level1 level) {
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
        if (nextLevelbutton.clicked()) {
            if (next_level == 2) {
                game.setWorld(new World(new Vector2(0, -9.8f),true));
                game.setLevel_2(new level2(game));
                game.setScreen(game.getLevel_2());
            } else if (next_level == 3) {
                game.setWorld(new World(new Vector2(0, -9.8f),true));
                game.setLevel_3(new level3(game));
                game.setScreen(game.getLevel_3());
            } else if (next_level == 1) {
                game.setWorld(new World(new Vector2(0, -9.8f),true));
                game.setLevel_1(new level1(game));
                game.setScreen(game.getLevel_1());
            }
        }
        if (restart_button.clicked()){
            if (next_level == 2){
                game.setWorld(new World(new Vector2(0, -9.8f),true));
                game.setLevel_1(new level1(game));
                game.setScreen(game.getLevel_1());
            }else if (next_level == 3){
                game.setWorld(new World(new Vector2(0, -9.8f),true));
                game.setLevel_2(new level2(game));
                game.setScreen(game.getLevel_2());
            }else{
                game.setWorld(new World(new Vector2(0, -9.8f),true));
                game.setLevel_3(new level3(game));
                game.setScreen(game.getLevel_3());
            }
        }
        if (menu.clicked()){
            game.setScreen(game.getMainMenuScreen());
        }
    }

    @Override
    public void show() {
//        this.launch_image = new Texture("pausePage.png");
        this.launch_image = new Texture("level clear 2.png");
//        this.launch_image = assetManager.get("Level clear.png", Texture.class);
        this.no_before = assetManager.get("front.png", Texture.class);
        this.no_after = assetManager.get("frontA.png", Texture.class);
        this.restartB = assetManager.get("restartB.png", Texture.class);
        this.restartA = assetManager.get("restartA.png", Texture.class);
        this.menuB = assetManager.get("level menuB.png", Texture.class);
        this.menuA = assetManager.get("level menuA.png", Texture.class);

        this.nextLevelbutton = new Click_Button(no_before, no_after, viewport.getWorldWidth()/2 + 200, 100, camera);
        nextLevelbutton.setInput(inputMultiplexer);
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

        batch.begin();
        batch.draw(new Texture("level clear 2.png"), 600, 0, 750, viewport.getWorldHeight());

            font.getData().setScale(0.9f);
        if (level_score >= 4000){
            batch.draw(new Texture("star 1.png"), 580,  640, 300,300);
            batch.draw(new Texture("star 2.png"), 820,  670, 280,280);
            batch.draw(new Texture("star 3.png"), 1050,  650, 300,300);
            font.draw(batch, level_score.toString(), 860, 670);
//            System.out.println("few");
        } else if (level_score < 4000 && level_score > 3000 ) {
            batch.draw(new Texture("star 1.png"), 580,  650, 300,300);
            batch.draw(new Texture("star 2.png"), 820,  670, 280,280);
            font.draw(batch, level_score.toString(), 860, 670);
        }else {
            batch.draw(new Texture("star 1.png"), 580,  650, 300,300);
            font.draw(batch, level_score.toString(), 860, 670);

        }

        nextLevelbutton.draw(batch);
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
        nextLevelbutton.set_Position(viewport.getWorldWidth()/2 + 200, 100);
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
