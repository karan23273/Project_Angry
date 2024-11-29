package com.Angry_Bird.Screen;

import com.Angry_Bird.Birds.Bird_Black;
import com.Angry_Bird.Birds.Bird_Red;
import com.Angry_Bird.Birds.Bird_Yellow;
import com.Angry_Bird.Blocks.Block_Frame;
import com.Angry_Bird.Blocks.Block_Rectangle;
import com.Angry_Bird.BodyData;
import com.Angry_Bird.BodyState;
import com.Angry_Bird.Buttons.Catapult;
import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.Pig.Adult_pig;
import com.Angry_Bird.Pig.Baby_pig;
import com.Angry_Bird.Pig.King_pig;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class level1 implements Screen , Serializable {
    private static final long serialVersionUID = 1L;
    private Integer score = 0;
    private final launch game;
    private final float floorWidth = 196;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;

    private SpriteBatch batch;
    private Texture level_image;

    private Texture cata;
    private BitmapFont font;

    Click_Button pause_Button;
    private Texture pause_before;
    private Texture pause_after;

    private Bird_Red birdRed1;
    private Bird_Red birdRed2;
    private Bird_Black birdBlack1;
    private Bird_Yellow birdYellow1;


    // Using in buttons only
    private Texture Black;
    private Texture Yellow;

    private InputMultiplexer inputMultiplexer;

    private Click_Button restart_button;
    private Texture restartB;
    private Texture restartA;

    private Texture baby_pig;
    private float Bp1_x = 1455 + 49;
    private float Bp1_y = floorWidth + 99 + 20 + 200 + 25 + 35;

    private Baby_pig baby_pig1;
    private float Bp2_x = 1055 + 49;
    private float Bp2_y = floorWidth + 99 + 20 + 200 + 25 + 35;

    private Baby_pig baby_pig2;

    private Texture adult_pig;

    private float Ap1_x = 1050+30 + 20;
    private float Ap1_y = floorWidth +99+20 + 20 +10;
    private Adult_pig adult_pig1;

    private float Ap2_x = 1450+30 + 20;
    private float Ap2_y = floorWidth +99+20 + 20 +10;
    private Adult_pig adult_pig2;

    private float Ap3_x = 1455 +40;
    private float Ap3_y = floorWidth +99+20 + 55 + 25 + 25 + 80 +50;
    private Adult_pig adult_pig3;


    private Texture king;
    private float Kp1_x = 1055 + 40;
    private float Kp1_y =  floorWidth +99+20 + 55 + 25 + 25 + 80 + 50;
    private King_pig kingpin;

    private Block_Rectangle blockRectangle1;
    private Block_Rectangle blockRectangle2;
    private Block_Rectangle blockRectangle3;
    private Block_Rectangle blockRectangle4;
    private Block_Rectangle blockRectangle5;
    private Block_Rectangle blockRectangle6;
    private Block_Rectangle blockRectangle7;
    private Block_Rectangle blockRectangle8;
    private Block_Rectangle blockRectangle9;
    private Block_Rectangle blockRectangle10;
    private Block_Rectangle blockRectangle11;
    private Block_Rectangle blockRectangle12;
    private Block_Rectangle blockRectangle13;
    private Block_Rectangle blockRectangle14;
    private Block_Rectangle blockRectangle15;
    private Block_Rectangle blockRectangle16;
    private Block_Rectangle blockRectangle17;
    private Block_Rectangle blockRectangle18;
    private Block_Rectangle blockRectangle19;

    private Block_Frame blockFrame1;
    private Block_Frame blockFrame2;

    private Body floor;
    private BodyDef floor_body;
    private FixtureDef floor_fixture;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private Body catapult;
    private BodyDef catapult_body;
    private FixtureDef catapult_fixture;

    private Catapult catapult_drag;
    private float PPM;

    private ArrayList<Body> birds;
    private ArrayList<Body> destroyBody;
    private int kills;
    private float wait = 0;
    private float start_time = 0;
    private boolean pause = false;


    public boolean isPaused() {
        return pause;
    }
    public void setPaused(boolean f) { pause = f; }

    private Click_Button savebutton;
    private Click_Button loadbutton;

    public level1(final launch game) {
        this.PPM = game.getPPM();
        this.game = game;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.world = game.getWorld();
        this.assetManager = game.getAssetManager();
        this.inputMultiplexer = new InputMultiplexer();

        this.box2DDebugRenderer = new Box2DDebugRenderer();
        // floor
        this.floor_body = new BodyDef();
        this.floor_fixture = new FixtureDef();
        kills =4;
        // catapult
        this.catapult_body = new BodyDef();
        this.catapult_fixture = new FixtureDef();
        this.birds = new ArrayList<>();
        this.destroyBody = game.getDestorybody();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public Click_Button getPauseButton() {return pause_Button;}

    private void update(float delta) {
        if (savebutton.clicked()){
            Savegame();
        }
        if (loadbutton.clicked()){
            Loadgame();
            loadbutton.setOff();
        }
        if (pause_Button.isOn()) {

            pause = true;
            game.getLevel_3().setPaused(false);
            game.getLevel_2().setPaused(false);
            game.getPauseScreen().setCurrLevel(1);
            game.getPauseScreen().render(delta);


        }

        if (!pause && restart_button.clicked()){
            game.setWorld(new World(new Vector2(0, -9.8f),true));
            game.setLevel_1(new level1(game));
            game.setScreen(game.getLevel_1());
        }


        if (kills<=0) {
            wait+=delta;
            if (wait>1){
                game.setLevel2_score(game.getLoginScreen().getUserid(), 1);
                game.setLevel1_score(game.getLoginScreen().getUserid(), score);
                game.getLevelPassed().setNext_level(2);
                game.getLevelPassed().setLevel_score(score);
                game.setScreen(game.getLevelPassed());
                wait = 0;
            }

        }
        else if(kills>0 && catapult_drag.birdsLeft()){
            game.getLevelFailed().setCurrLevel(1);
            game.setScreen(game.getLevelFailed());

        }
    }

    @Override
    public void show() {
        this.level_image = assetManager.get("A2.png", Texture.class);
        this.cata = assetManager.get("catapult.png", Texture.class);
        this.pause_before = assetManager.get("pauseB.png", Texture.class);
        this.pause_after = assetManager.get("pauseA.png", Texture.class);
//        this.Red = assetManager.get("red40.png", Texture.class);
        this.Black = assetManager.get("black.png", Texture.class);
        this.Yellow = assetManager.get("yellowA.png", Texture.class);
        this.restartB = assetManager.get("restartB.png", Texture.class);
        this.restartA = assetManager.get("restartA.png", Texture.class);
        this.adult_pig = assetManager.get("AdultpigA.png", Texture.class);
        this.baby_pig = assetManager.get("BabyPigA.png", Texture.class);
        this.king = assetManager.get("kingPinA.png", Texture.class);

        this.pause_Button = new Click_Button(pause_before, pause_after, 15/PPM, (viewport.getWorldHeight()-170)/PPM, camera, 180/PPM, 180/PPM);
        pause_Button.setInput(inputMultiplexer);

        this.restart_button = new Click_Button(restartB, restartA, 200/PPM, (viewport.getWorldHeight()-170)/PPM, camera, 180/PPM, 180/PPM);
        restart_button.setInput(inputMultiplexer);

        this.savebutton = new Click_Button(new Texture("saveGame.png"),new Texture("saveGameA.png"),1250/PPM, (viewport.getWorldHeight()-100)/PPM, camera, 300/PPM, 100/PPM );
        savebutton.setInput(inputMultiplexer);

        this.loadbutton = new Click_Button(new Texture("loadGame.png"), new Texture("loadGameA.png"),900/PPM, (viewport.getWorldHeight()-100)/PPM, camera, 300/PPM, 100/PPM  );
        loadbutton.setInput(inputMultiplexer);
//        birds.add(new Bird_Red(game, 300/PPM, (floorWidth+190)/PPM).getBody());

        if (!pause){

            this.birdRed1 = new Bird_Red(game, 300 / PPM, (floorWidth + 25) / PPM);
            this.birdRed2 = new Bird_Red(game, 260 / PPM, (floorWidth + 25) / PPM);
            this.birdYellow1 = new Bird_Yellow(game, 200 / PPM, (floorWidth + 25) / PPM);
            this.birdBlack1 = new Bird_Black(game, 120 / PPM, (floorWidth + 25) / PPM);
            birds.add(birdRed1.getBody());
            birds.add(birdYellow1.getBody());
            birds.add(birdBlack1.getBody());
            birds.add(birdRed2.getBody());

//        this.baby_pig1 = new Baby_pig(game, Bp1_x, Bp1_y);
//        this.baby_pig2 = new Baby_pig(game, Bp2_x, Bp2_y);
            this.kingpin = new King_pig(game, Kp1_x, Kp1_y);
            this.adult_pig1 = new Adult_pig(game, Ap1_x, Ap1_y);
            this.adult_pig2 = new Adult_pig(game, Ap2_x, Ap2_y);
            this.adult_pig3 = new Adult_pig(game, Ap3_x, Ap3_y);
            this.blockRectangle1 = new Block_Rectangle(game, "wood", 1200, floorWidth + 5, 30, 100);
            this.blockRectangle2 = new Block_Rectangle(game, "wood", 1000, floorWidth +5, 30, 100);


            this.blockRectangle4 = new Block_Rectangle(game, "wood", 1600, floorWidth + 5, 30, 100);
            this.blockRectangle5 = new Block_Rectangle(game, "wood", 1400, floorWidth + 5, 30, 100);

            this.blockRectangle3 = new Block_Rectangle(game, "rock", 1000 + 100, floorWidth + 105 + 10, 230, 20);
            this.blockRectangle6 = new Block_Rectangle(game, "rock", 1400 + 100, floorWidth + 105 + 10, 230, 20);

            this.blockRectangle8 = new Block_Rectangle(game, "wood", 1045, floorWidth + 150 + 20 + 15, 25, 100);
            this.blockRectangle9 = new Block_Rectangle(game, "wood", 1160, floorWidth + 150 + 20 + 15, 25, 100);
            this.blockRectangle7 = new Block_Rectangle(game, "wood", 1440, floorWidth + 150 + 20 + 15, 25, 100);
            this.blockRectangle10 = new Block_Rectangle(game, "wood", 1560, floorWidth + 150 + 20 + 15, 25, 100);

            this.blockRectangle15 = new Block_Rectangle(game, "wood", 1000+20, floorWidth + 150 + 50 + 50 + 25, 25, 300);
            this.blockRectangle17 = new Block_Rectangle(game, "wood", 1200-20, floorWidth + 150 + 50 + 50 + 25, 25, 300);
            this.blockRectangle16 = new Block_Rectangle(game, "wood", 1400+20, floorWidth + 150 + 50 + 50 + 25, 25, 300);
            this.blockRectangle18 = new Block_Rectangle(game, "wood", 1600-20, floorWidth + 150 + 50 + 50 + 25, 25, 300);

            this.blockRectangle11 = new Block_Rectangle(game, "wood", 1045 + (1160-1045)/2,floorWidth + 150 + 20 + 55 + 20, 140, 25);
            this.blockRectangle12 = new Block_Rectangle(game, "wood", 1440 + (1560-1440)/2,floorWidth + 150 + 20 + 55 + 20, 140, 25);

            this.blockRectangle13 = new Block_Rectangle(game, "rock", 1045 + (1160-1045)/2, floorWidth + 150 + 20 + 55 + 30 + 30, 140, 25);
            this.blockRectangle14 = new Block_Rectangle(game, "rock", 1440 + (1160-1045)/2, floorWidth + 150 + 20 + 55 + 30 + 30, 140, 25);

            this.blockRectangle19 = new Block_Rectangle(game, "rock", 1000 + 300, floorWidth + 150 + 20 + 55 + 30 + 150 + 35, 650, 25);

//            this.blockFrame1 = new Block_Frame(game,"Square", "wood",1055 , (floorWidth + 99 + 20 + 200 + 25) , 60);
//            this.blockFrame2 = new Block_Frame(game,"Triangle", "rock",1455 , (floorWidth + 99 + 20 + 200 + 25), 60);
            drawfloor();
            drawcatapult(350, 20);
            drawcatapult(1950, 100);
            this.catapult_drag = new Catapult(game,birds,335/PPM, (floorWidth+190)/PPM);

            game.getPauseScreen().setInputMultiplexer(inputMultiplexer);
            game.getPauseScreen().show();

        }else {

            Gdx.input.setInputProcessor(inputMultiplexer);

        }
//

    }
    public void drawfloor(){
        floor_body.type = BodyDef.BodyType.StaticBody;
        floor_body.position.set(0,0);
        floor = world.createBody(floor_body);
        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(viewport.getWorldWidth()/PPM, floorWidth/PPM);
        floor_fixture.shape = floorShape;
        floor_fixture.friction = 10f;
        floor_fixture.restitution = 0.5f;
        floor.createFixture(floor_fixture);
        floor.setUserData(this);
        floorShape.dispose();

    }

    public void drawcatapult(float x, float height){
        catapult_body.type = BodyDef.BodyType.StaticBody;
        catapult_body.position.set(x/PPM, (floorWidth+20)/PPM);
        PolygonShape catapultShape = new PolygonShape();
        catapultShape.setAsBox(10/PPM, height/PPM);
        catapult_fixture.shape = catapultShape;
        catapult_fixture.friction = 2f;
        catapult_fixture.restitution = 0.5f;
        catapult = world.createBody(catapult_body);
        catapult.createFixture(catapult_fixture);
        catapult.setUserData(this);
        catapultShape.dispose();
    }

    public void Savegame(){
        List<BodyData> blocks = new ArrayList<>();
        int cp = catapult_drag.getIndex();
        List<BodyData> Ap = new ArrayList<>();
        List<BodyData> Bp = new ArrayList<>();
        List<BodyData> kp = new ArrayList<>();
        blocks.add(new BodyData(blockRectangle1.getBody().getPosition().x, blockRectangle1.getBody().getPosition().y, blockRectangle1.getBody().getLinearVelocity().x, blockRectangle1.getBody().getLinearVelocity().y, blockRectangle1.getBody().getAngle(), blockRectangle1.getBody().getAngularVelocity(), blockRectangle1.isDestroyed()));
        blocks.add(new BodyData(blockRectangle2.getBody().getPosition().x, blockRectangle2.getBody().getPosition().y, blockRectangle2.getBody().getLinearVelocity().x, blockRectangle2.getBody().getLinearVelocity().y, blockRectangle2.getBody().getAngle(), blockRectangle2.getBody().getAngularVelocity(), blockRectangle2.isDestroyed()));
        blocks.add(new BodyData(blockRectangle3.getBody().getPosition().x, blockRectangle3.getBody().getPosition().y, blockRectangle3.getBody().getLinearVelocity().x, blockRectangle3.getBody().getLinearVelocity().y, blockRectangle3.getBody().getAngle(), blockRectangle3.getBody().getAngularVelocity(), blockRectangle3.isDestroyed()));
        blocks.add(new BodyData(blockRectangle4.getBody().getPosition().x, blockRectangle4.getBody().getPosition().y, blockRectangle4.getBody().getLinearVelocity().x, blockRectangle4.getBody().getLinearVelocity().y, blockRectangle4.getBody().getAngle(), blockRectangle4.getBody().getAngularVelocity(), blockRectangle4.isDestroyed()));
        blocks.add(new BodyData(blockRectangle5.getBody().getPosition().x, blockRectangle5.getBody().getPosition().y, blockRectangle5.getBody().getLinearVelocity().x, blockRectangle5.getBody().getLinearVelocity().y, blockRectangle5.getBody().getAngle(), blockRectangle5.getBody().getAngularVelocity(), blockRectangle5.isDestroyed()));
        blocks.add(new BodyData(blockRectangle6.getBody().getPosition().x, blockRectangle6.getBody().getPosition().y, blockRectangle6.getBody().getLinearVelocity().x, blockRectangle6.getBody().getLinearVelocity().y, blockRectangle6.getBody().getAngle(), blockRectangle6.getBody().getAngularVelocity(), blockRectangle6.isDestroyed()));
        blocks.add(new BodyData(blockRectangle7.getBody().getPosition().x, blockRectangle7.getBody().getPosition().y, blockRectangle7.getBody().getLinearVelocity().x, blockRectangle7.getBody().getLinearVelocity().y, blockRectangle7.getBody().getAngle(), blockRectangle7.getBody().getAngularVelocity(), blockRectangle7.isDestroyed()));
        blocks.add(new BodyData(blockRectangle8.getBody().getPosition().x, blockRectangle8.getBody().getPosition().y, blockRectangle8.getBody().getLinearVelocity().x, blockRectangle8.getBody().getLinearVelocity().y, blockRectangle8.getBody().getAngle(), blockRectangle8.getBody().getAngularVelocity(), blockRectangle8.isDestroyed()));
        blocks.add(new BodyData(blockRectangle9.getBody().getPosition().x, blockRectangle9.getBody().getPosition().y, blockRectangle9.getBody().getLinearVelocity().x, blockRectangle9.getBody().getLinearVelocity().y, blockRectangle9.getBody().getAngle(), blockRectangle9.getBody().getAngularVelocity(), blockRectangle9.isDestroyed()));
        blocks.add(new BodyData(blockRectangle10.getBody().getPosition().x, blockRectangle10.getBody().getPosition().y, blockRectangle10.getBody().getLinearVelocity().x, blockRectangle10.getBody().getLinearVelocity().y, blockRectangle10.getBody().getAngle(), blockRectangle10.getBody().getAngularVelocity(), blockRectangle10.isDestroyed()));
        blocks.add(new BodyData(blockRectangle11.getBody().getPosition().x, blockRectangle11.getBody().getPosition().y, blockRectangle11.getBody().getLinearVelocity().x, blockRectangle11.getBody().getLinearVelocity().y, blockRectangle11.getBody().getAngle(), blockRectangle11.getBody().getAngularVelocity(), blockRectangle11.isDestroyed()));
        blocks.add(new BodyData(blockRectangle12.getBody().getPosition().x, blockRectangle12.getBody().getPosition().y, blockRectangle12.getBody().getLinearVelocity().x, blockRectangle12.getBody().getLinearVelocity().y, blockRectangle12.getBody().getAngle(), blockRectangle12.getBody().getAngularVelocity(), blockRectangle12.isDestroyed()));
        blocks.add(new BodyData(blockRectangle13.getBody().getPosition().x, blockRectangle13.getBody().getPosition().y, blockRectangle13.getBody().getLinearVelocity().x, blockRectangle13.getBody().getLinearVelocity().y, blockRectangle13.getBody().getAngle(), blockRectangle13.getBody().getAngularVelocity(), blockRectangle13.isDestroyed()));
        blocks.add(new BodyData(blockRectangle14.getBody().getPosition().x, blockRectangle14.getBody().getPosition().y, blockRectangle14.getBody().getLinearVelocity().x, blockRectangle14.getBody().getLinearVelocity().y, blockRectangle14.getBody().getAngle(), blockRectangle14.getBody().getAngularVelocity(), blockRectangle14.isDestroyed()));
        blocks.add(new BodyData(blockRectangle15.getBody().getPosition().x, blockRectangle15.getBody().getPosition().y, blockRectangle15.getBody().getLinearVelocity().x, blockRectangle15.getBody().getLinearVelocity().y, blockRectangle15.getBody().getAngle(), blockRectangle15.getBody().getAngularVelocity(), blockRectangle15.isDestroyed()));
        blocks.add(new BodyData(blockRectangle16.getBody().getPosition().x, blockRectangle16.getBody().getPosition().y, blockRectangle16.getBody().getLinearVelocity().x, blockRectangle16.getBody().getLinearVelocity().y, blockRectangle16.getBody().getAngle(), blockRectangle16.getBody().getAngularVelocity(), blockRectangle16.isDestroyed()));
        blocks.add(new BodyData(blockRectangle17.getBody().getPosition().x, blockRectangle17.getBody().getPosition().y, blockRectangle17.getBody().getLinearVelocity().x, blockRectangle17.getBody().getLinearVelocity().y, blockRectangle17.getBody().getAngle(), blockRectangle17.getBody().getAngularVelocity(), blockRectangle17.isDestroyed()));
        blocks.add(new BodyData(blockRectangle18.getBody().getPosition().x, blockRectangle18.getBody().getPosition().y, blockRectangle18.getBody().getLinearVelocity().x, blockRectangle18.getBody().getLinearVelocity().y, blockRectangle18.getBody().getAngle(), blockRectangle18.getBody().getAngularVelocity(), blockRectangle18.isDestroyed()));
        blocks.add(new BodyData(blockRectangle19.getBody().getPosition().x, blockRectangle19.getBody().getPosition().y, blockRectangle19.getBody().getLinearVelocity().x, blockRectangle19.getBody().getLinearVelocity().y, blockRectangle19.getBody().getAngle(), blockRectangle19.getBody().getAngularVelocity(), blockRectangle19.isDestroyed()));


        kp.add(new BodyData(kingpin.getBody().getPosition().x, kingpin.getBody().getPosition().y, kingpin.getBody().getLinearVelocity().x, kingpin.getBody().getLinearVelocity().y, kingpin.getBody().getAngle(), kingpin.getBody().getAngularVelocity(), kingpin.isDestroyed()));
        Ap.add(new BodyData(adult_pig1.getBody().getPosition().x, adult_pig1.getBody().getPosition().y, adult_pig1.getBody().getLinearVelocity().x, adult_pig1.getBody().getLinearVelocity().y, adult_pig1.getBody().getAngle(), adult_pig1.getBody().getAngularVelocity(), adult_pig1.isDestroyed()));
        Ap.add(new BodyData(adult_pig2.getBody().getPosition().x, adult_pig2.getBody().getPosition().y, adult_pig2.getBody().getLinearVelocity().x, adult_pig2.getBody().getLinearVelocity().y, adult_pig2.getBody().getAngle(), adult_pig2.getBody().getAngularVelocity(), adult_pig2.isDestroyed()));
        Ap.add(new BodyData(adult_pig3.getBody().getPosition().x, adult_pig3.getBody().getPosition().y, adult_pig3.getBody().getLinearVelocity().x, adult_pig3.getBody().getLinearVelocity().y, adult_pig3.getBody().getAngle(), adult_pig3.getBody().getAngularVelocity(), adult_pig3.isDestroyed()));

        SaveState gameState = new SaveState(blocks, cp, Ap, kp, Bp);
        GameStateManager.saveGameState(gameState, "Level1_state.ser");

    }

    public void Loadgame() {
        SaveState loadedGameState = GameStateManager.loadGameState("Level1_state.ser");
        if (loadedGameState != null) {
            List<BodyData> loadedBlocks = loadedGameState.getBlocks();
            int cp = loadedGameState.getCatapults();
            List<BodyData> Ap = loadedGameState.getAdultPigs();
            List<BodyData> kp = loadedGameState.getKingPigs();
            List<BodyData> bp = loadedGameState.getPigs();
            catapult_drag.setIndex(cp);

            if (!loadedBlocks.get(0).isDestroyed()) blockRectangle1.load(loadedBlocks.get(0));
            else {
                blockRectangle1.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(1).isDestroyed()) blockRectangle2.load(loadedBlocks.get(1));
            else {
                blockRectangle2.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(2).isDestroyed()) blockRectangle3.load(loadedBlocks.get(2));
            else {
                blockRectangle3.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(3).isDestroyed()) blockRectangle4.load(loadedBlocks.get(3));
            else {
                blockRectangle4.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(4).isDestroyed()) blockRectangle5.load(loadedBlocks.get(4));
            else {
                blockRectangle5.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(5).isDestroyed()) blockRectangle6.load(loadedBlocks.get(5));
            else {
                blockRectangle6.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(6).isDestroyed()) blockRectangle7.load(loadedBlocks.get(6));
            else {
                blockRectangle7.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(7).isDestroyed()) blockRectangle8.load(loadedBlocks.get(7));
            else {
                blockRectangle8.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(8).isDestroyed()) blockRectangle9.load(loadedBlocks.get(8));
            else {
                blockRectangle9.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(9).isDestroyed()) blockRectangle10.load(loadedBlocks.get(9));
            else {
                blockRectangle10.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(10).isDestroyed()) blockRectangle11.load(loadedBlocks.get(10));
            else {
                blockRectangle11.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(11).isDestroyed()) blockRectangle12.load(loadedBlocks.get(11));
            else {
                blockRectangle12.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(12).isDestroyed()) blockRectangle13.load(loadedBlocks.get(12));
            else {
                blockRectangle13.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(13).isDestroyed()) blockRectangle14.load(loadedBlocks.get(13));
            else {
                blockRectangle14.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(14).isDestroyed()) blockRectangle15.load(loadedBlocks.get(14));
            else {
                blockRectangle15.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(15).isDestroyed()) blockRectangle16.load(loadedBlocks.get(15));
            else {
                blockRectangle16.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(16).isDestroyed()) blockRectangle17.load(loadedBlocks.get(16));
            else {
                blockRectangle17.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(17).isDestroyed()) blockRectangle18.load(loadedBlocks.get(17));
            else {
                blockRectangle18.destroy();
                score += 200;
            }
            if (!loadedBlocks.get(18).isDestroyed()) blockRectangle19.load(loadedBlocks.get(18));
            else {
                blockRectangle19.destroy();
                score += 200;
            }


            if (!Ap.get(0).isDestroyed()){
                adult_pig1.load(Ap.get(0));
            }else {
                adult_pig1.destroy();
                kills--;
                score +=500;
            }
            if (!Ap.get(1).isDestroyed()){
                adult_pig2.load(Ap.get(1));
            }
            else{
                adult_pig2.destroy();
                kills--;
                score +=500;
            }
            if (!Ap.get(2).isDestroyed()){
                adult_pig3.load(Ap.get(2));
            } else{
                adult_pig3.destroy();
                kills--;
                score +=500;
            }
            if (!kp.get(0).isDestroyed()){
                kingpin.load(kp.get(0));
            } else{
                kingpin.destroy();
                kills--;
                score +=2000;
            }
        }
    }

    private boolean f = false;
    @Override
    public void render(float deltaTime) {

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.setToOrtho(false, viewport.getWorldWidth()/(PPM), viewport.getWorldHeight()/(PPM));

        camera.zoom = 1;
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        start_time+=deltaTime;
        if (start_time>=1) {
            world.setContactListener(game.getContactListener());
        }

        if (!pause){
            catapult_drag.setCatapultInput(deltaTime);
            if (f){
                blockRectangle19.getState();
                blockRectangle18.getState();
                blockRectangle17.getState();
                blockRectangle16.getState();
                blockRectangle15.getState();
                blockRectangle14.getState();
                blockRectangle13.getState();
                blockRectangle12.getState();
                blockRectangle11.getState();
                blockRectangle10.getState();
                blockRectangle9.getState();
                blockRectangle8.getState();
                blockRectangle7.getState();
                blockRectangle6.getState();
                blockRectangle5.getState();
                blockRectangle4.getState();
                blockRectangle3.getState();
                blockRectangle2.getState();
                blockRectangle1.getState();

                kingpin.getState();
                adult_pig1.getState();
                adult_pig2.getState();
                adult_pig3.getState();


                f = false;

            }
        }else {
            f = true;
            blockRectangle19.setState();
            blockRectangle18.setState();
            blockRectangle17.setState();
            blockRectangle16.setState();
            blockRectangle15.setState();
            blockRectangle14.setState();
            blockRectangle13.setState();
            blockRectangle12.setState();
            blockRectangle11.setState();
            blockRectangle10.setState();
            blockRectangle9.setState();
            blockRectangle8.setState();
            blockRectangle7.setState();
            blockRectangle6.setState();
            blockRectangle5.setState();
            blockRectangle5.setState();
            blockRectangle4.setState();
            blockRectangle3.setState();
            blockRectangle2.setState();
            blockRectangle1.setState();

            kingpin.setState();
            adult_pig1.setState();
            adult_pig2.setState();
            adult_pig3.setState();


        }

        world.step(deltaTime,6,2);
        box2DDebugRenderer.render(world, camera.combined);

        for(Body b: destroyBody){
            if(b!= null && !destroyBody.isEmpty()){
                if (b.getUserData() instanceof Baby_pig){
                    score += 200;
                }
                if (b.getUserData() instanceof Block_Rectangle){
                    score +=200;
                }
                if (b.getUserData() instanceof Adult_pig){
                    score += 500;
                    kills--;
                }
                if (b.getUserData() instanceof King_pig){
                    score += 2000;
                    kills--;
                }
                world.destroyBody(b);
            }
        }
        destroyBody.clear();

        batch.begin();

        batch.draw(level_image,0, 0,  viewport.getWorldWidth()/PPM, viewport.getWorldHeight()/PPM);
        batch.draw(cata, 300/PPM, floorWidth/PPM, 80/PPM, 200/PPM);
        birdRed1.draw_object(batch);
        birdRed2.draw_object(batch);
        birdBlack1.draw_object(batch);
        birdYellow1.draw_object(batch);
//        baby_pig1.draw_Pig(deltaTime, batch);
//        baby_pig2.draw_Pig(deltaTime, batch);
        adult_pig1.draw_Pig(deltaTime, batch);
        adult_pig2.draw_Pig(deltaTime, batch);
        adult_pig3.draw_Pig(deltaTime, batch);
        kingpin.draw_Pig(deltaTime, batch);
        pause_Button.draw(batch);
        restart_button.draw(batch);
        font.getData().setScale((float) (0.9/PPM));
        savebutton.draw(batch);
        loadbutton.draw(batch);

        font.draw(batch, "Sc o re", (viewport.getWorldWidth()-300)/PPM, (viewport.getWorldHeight()-10)/PPM);
        float s =score.toString().length()/PPM;
        font.getData().setScale((float) (0.9));
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, score.toString());
        font.getData().setScale((float) (0.9/PPM));


        font.draw(batch, score.toString() , (viewport.getWorldWidth())/PPM - glyphLayout.width/PPM, (viewport.getWorldHeight()-80)/PPM);


        blockRectangle1.draw_Block(batch);
        blockRectangle2.draw_Block(batch);
        blockRectangle3.draw_Block( batch);
        blockRectangle4.draw_Block( batch);
        blockRectangle5.draw_Block( batch);
        blockRectangle6.draw_Block( batch);
        blockRectangle7.draw_Block( batch);
        blockRectangle8.draw_Block( batch);
        blockRectangle9.draw_Block( batch);
        blockRectangle10.draw_Block( batch);
        blockRectangle11.draw_Block( batch);
        blockRectangle12.draw_Block( batch);
        blockRectangle13.draw_Block( batch);
        blockRectangle14.draw_Block( batch);
        blockRectangle15.draw_Block( batch);
        blockRectangle16.draw_Block( batch);
        blockRectangle17.draw_Block( batch);
        blockRectangle18.draw_Block( batch);

        blockRectangle19.draw_Block( batch);

//        blockFrame1.draw_Block(deltaTime, batch);
//        blockFrame2.draw_Block(deltaTime, batch);
        birdYellow1.draw_object(batch);
        batch.draw(cata, 300, floorWidth, 1000, 1000);  // useless
        catapult_drag.renderCatapult();
        batch.end();
        update(deltaTime);

    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);

        camera.position.set(viewport.getWorldWidth() / 2 , viewport.getWorldHeight() / 2, 0);
        camera.update();

        pause_Button.set_Position(15/PPM, (viewport.getWorldHeight()-190)/PPM);
        restart_button.set_Position(210/PPM,(viewport.getWorldHeight()-190)/PPM);
        savebutton.set_Position(1250/PPM, (viewport.getWorldHeight()-130)/PPM);
        loadbutton.set_Position(900/PPM, (viewport.getWorldHeight()-130)/PPM);

        birdRed1.set_bird(300/PPM, (floorWidth+25)/PPM);

        birdRed2.set_bird(260/PPM, (floorWidth+25)/PPM);

        birdBlack1.set_bird(120/PPM, (floorWidth+25)/PPM);

        birdYellow1.set_bird(200/PPM, (floorWidth+25)/PPM);


//        baby_pig1.setPig(Bp1_x, Bp1_y);
//        baby_pig2.setPig(Bp2_x,Bp2_y);
        adult_pig1.setPig(Ap1_x,Ap1_y);
        adult_pig2.setPig(Ap2_x, Ap2_y);
        adult_pig3.setPig(Ap3_x,Ap3_y);
        kingpin.setPig(Kp1_x,Kp1_y);
        // 5
        blockRectangle1.setblock(1200, floorWidth + 5 + 40);
        blockRectangle2.setblock(1000, floorWidth + 5 + 40);
        blockRectangle4.setblock(1600, floorWidth + 5 + 40);
        blockRectangle5.setblock(1400, floorWidth + 5 + 40);
        // 10
        blockRectangle3.setblock(1000 + 100, floorWidth + 105 + 10 + 15);
        blockRectangle6.setblock(1400 + 100, floorWidth + 105 + 10 + 15);
        // 15
        blockRectangle7.setblock(1440, floorWidth + 150 + 20 + 15 + 20);
        blockRectangle10.setblock(1560, floorWidth + 150 + 20 + 15 + 20);
        blockRectangle8.setblock(1045, floorWidth + 150 + 20 + 15 + 20);
        blockRectangle9.setblock(1160, floorWidth + 150 + 20 + 15 + 20);
        // 20
        blockRectangle11.setblock(1045 + (1160-1045)/2,floorWidth + 150 + 20 + 55 + 20 +25);
        blockRectangle12.setblock(1440 + (1560-1440)/2,floorWidth + 150 + 20 + 55 + 20 +25);
        // 25
        blockRectangle15.setblock(1000+20, floorWidth + 150 + 50 + 50 + 25 + 30);
        blockRectangle16.setblock(1400+20, floorWidth + 150 + 50 + 50 + 25 + 30);
        blockRectangle17.setblock(1200-20, floorWidth + 150 + 50 + 50 + 25 + 30);
        blockRectangle18.setblock(1600-20, floorWidth + 150 + 50 + 50 + 25 + 30);
        // 30
        blockRectangle13.setblock(1045 + (1160-1045)/2, floorWidth + 150 + 20 + 55 + 30 + 30 + 35);
        blockRectangle14.setblock(1440 + (1160-1045)/2, floorWidth + 150 + 20 + 55 + 30 + 30 + 35);
        // 35
        blockRectangle19.setblock(1000 + 300, floorWidth + 150 + 20 + 55 + 30 + 150 + 35 + 40);

//        blockFrame1.setblock(1055 , floorWidth + 99 + 20 + 200 + 25);

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
//        level_image.dispose();
//        cata.dispose();
//        pause_before.dispose();
//        pause_after.dispose();
//        birdRed1.dispose();
//        birdRed2.dispose();
//        restartA.dispose();
//        restartB.dispose();
//        birdBlack1.dispose();
//        Black.dispose();
//        Yellow.dispose();
//        adult_pig.dispose();
//        adult_pig2.dispose();
//        adult_pig3.dispose();
//        kingpin.dispose();
//        baby_pig.dispose();
//        baby_pig2.dispose();
//        baby_pig1.dispose();
//        king.dispose();
//        birdRed1.dispose();
//        birdRed2.dispose();
//        adult_pig1.dispose();
//        world.dispose();
//        box2DDebugRenderer.dispose();
    }
}

