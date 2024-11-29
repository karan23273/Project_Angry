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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import static java.lang.System.exit;

public class Level_Screen implements Screen {
    private final launch game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;
    private SpriteBatch batch;
    private Texture launch_image;
    private BitmapFont font;

    public Click_Button no_Button;
    private Texture no_before;

    private Texture no_after;

    private InputMultiplexer inputMultiplexer;
    private MainMenuScreen mainMenuScreen;

    private Texture level_F;
    private Texture lock;

    private Texture levelB;
    private Texture levelA;

    public Click_Button level1_button;
    public Click_Button level2_button;
    public Click_Button level3_button;

    private Texture star_1;
    private Texture star_2;
    private Texture star_3;


    public Level_Screen(final launch game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.assetManager = game.getAssetManager();
        this.font = game.getFont();
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void update(float delta) {
        if (no_Button.clicked()) {
            game.setScreen(game.getMainMenuScreen());
        }
        if (level1_button.clicked()) {
            game.setWorld(new World(new Vector2(0, -9.8f),true));
            game.setLevel_1(new level1(game));
            game.setScreen(game.getLevel_1());
        }

        if (game.getLevel2_score(game.getLoginScreen().getUserid()) >=1 && level2_button.clicked()) {
            game.setWorld(new World(new Vector2(0, -9.8f),true));
            game.setLevel_2(new level2(game));
            game.setScreen(game.getLevel_2());
        }
        if (game.getLevel3_score(game.getLoginScreen().getUserid()) >=1 && level3_button.clicked()) {
            game.setWorld(new World(new Vector2(0, -9.8f),true));
            game.setLevel_3(new level3(game));
            game.setScreen(new level3(game));
        }
    }

    @Override
    public void show() {
        this.launch_image = assetManager.get("level.png", Texture.class);
        this.no_before = assetManager.get("back.png", Texture.class);
        this.no_after = assetManager.get("backA.png", Texture.class);
        this.level_F = assetManager.get("levelFrame.png", Texture.class);
        this.lock = assetManager.get("lock.png", Texture.class);
        this.levelB = assetManager.get("levelFrameB.png", Texture.class);
        this.levelA = assetManager.get("levelFrameA.png", Texture.class);
        this.star_1 =assetManager.get("star.png", Texture.class);
        this.star_2 =assetManager.get("star.png", Texture.class);
        this.star_3 =assetManager.get("star.png", Texture.class);


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

    public void drawStar(){
        float l1 = game.getLevel1_score(game.getLoginScreen().getUserid());
        float l2 = game.getLevel2_score(game.getLoginScreen().getUserid());
        float l3 = game.getLevel3_score(game.getLoginScreen().getUserid());

        if (l1 >= 4000){

            font.draw(batch, "1", 370, viewport.getWorldHeight() -115);
            level1_button.draw(batch);
            batch.draw(star_1, 300, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_2, 365, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_3, 430, viewport.getWorldHeight() -320, 70 ,58);

        } else if (l1 < 4000 && l1> 3000 ) {

            font.draw(batch, "1", 370, viewport.getWorldHeight() -115);
            level1_button.draw(batch);
            batch.draw(star_1, 300, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_2, 365, viewport.getWorldHeight() -320, 70 ,58);

        } else if (3000 >= l1 && l1 > 1) {

            font.draw(batch, "1", 370, viewport.getWorldHeight() -115);
            level1_button.draw(batch);
            batch.draw(star_1, 300, viewport.getWorldHeight() -320, 70 ,58);

        } else if (l1 == 0) {

            font.draw(batch, "1", 370, viewport.getWorldHeight() -115);
            level1_button.draw(batch);

        }

        if (l2 >= 4000){

            font.draw(batch, "2", 670, viewport.getWorldHeight() -115);
            level2_button.draw(batch);
            batch.draw(star_1, 600, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_2, 665, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_3, 730, viewport.getWorldHeight() -320, 70 ,58);

        } else if (l2 < 4000 && l2 > 3000 ) {

            font.draw(batch, "2", 670, viewport.getWorldHeight() -115);
            level2_button.draw(batch);
            batch.draw(star_1, 600, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_2, 665, viewport.getWorldHeight() -320, 70 ,58);

        } else if (3000 >= l2 && l2 >= 1000) {

            font.draw(batch, "2", 670, viewport.getWorldHeight() -115);
            level2_button.draw(batch);
            batch.draw(star_1, 600, viewport.getWorldHeight() -320, 70 ,58);

        } else if (l2 == 0) {

            batch.draw(level_F, 600,3*viewport.getWorldHeight()/4 -30 , 200, 200);
            batch.draw(lock, 645, 3*viewport.getWorldHeight() /4 , 101, 130 );
        } else if (l2 >= 1 && 1000 > l2) {
            font.draw(batch, "2", 670, viewport.getWorldHeight() -115);
            level2_button.draw(batch);
        }


        if (l3 >= 4000){

            font.draw(batch, "3", 970, viewport.getWorldHeight() -115);
            level3_button.draw(batch);
            batch.draw(star_1, 900, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_2, 965, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_3, 1030, viewport.getWorldHeight() -320, 70 ,58);

        } else if (l3 < 4000 && l3 > 3000 ) {

            font.draw(batch, "3", 970, viewport.getWorldHeight() -115);
            level3_button.draw(batch);
            batch.draw(star_1, 900, viewport.getWorldHeight() -320, 70 ,58);
            batch.draw(star_2, 965, viewport.getWorldHeight() -320, 70 ,58);

        } else if (3000 >= l3 && l3 >= 1000) {

            font.draw(batch, "3", 970, viewport.getWorldHeight() -115);
            level3_button.draw(batch);
            batch.draw(star_1, 900, viewport.getWorldHeight() -320, 70 ,58);

        } else if (l3 == 0) {

            batch.draw(level_F, 900,3*viewport.getWorldHeight()/4 -30 , 200, 200);
            batch.draw(lock, 945, 3*viewport.getWorldHeight() /4 , 101, 130 );
        } else if (l3 >= 1 && 1000 > l3) {
            font.draw(batch, "3", 970, viewport.getWorldHeight() -115);
            level3_button.draw(batch);
        }

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

        drawStar();
//        level1_button.draw(batch);
//        font.draw(batch, "1", 370, viewport.getWorldHeight() -115);
//        batch.draw(star_1, 300, viewport.getWorldHeight() -320, 70 ,58);
//        batch.draw(star_2, 365, viewport.getWorldHeight() -320, 70 ,58);
//        batch.draw(star_3, 430, viewport.getWorldHeight() -320, 70 ,58);
//        level2_button.draw(batch);
//        font.draw(batch, "2", 670, viewport.getWorldHeight() -115);
//        batch.draw(star_1, 600, viewport.getWorldHeight() -320, 70 ,58);
//        batch.draw(star_2, 665, viewport.getWorldHeight() -320, 70 ,58);
//        batch.draw(star_3, 730, viewport.getWorldHeight() -320, 70 ,58);
//
//        level3_button.draw(batch);
//        font.draw(batch, "3", 970, viewport.getWorldHeight() -115);
//        batch.draw(star_1, 900, viewport.getWorldHeight() -320, 70 ,58);
//        batch.draw(star_2, 965, viewport.getWorldHeight() -320, 70 ,58);
//        batch.draw(star_3, 1030, viewport.getWorldHeight() -320, 70 ,58);

//        level2_button.draw(batch);


//        level3_button.draw(batch);



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
        star_1.dispose();
        star_2.dispose();
        star_3.dispose();
    }
}
