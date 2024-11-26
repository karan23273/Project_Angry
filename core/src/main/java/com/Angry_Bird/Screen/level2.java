package com.Angry_Bird.Screen;
import com.Angry_Bird.Birds.Bird_Black;
import com.Angry_Bird.Birds.Bird_Red;
import com.Angry_Bird.Birds.Bird_Yellow;
import com.Angry_Bird.Blocks.Block_Frame;
import com.Angry_Bird.Blocks.Block_Rectangle;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Queue;

public class level2 implements Screen{
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

    private Click_Button pause_Button;
    private Texture pause_before;
    private Texture pause_after;

    private Bird_Red birdRed1;
    private Bird_Red birdRed2;
    private Bird_Black birdBlack1;
    private Bird_Yellow birdYellow1;
    private Bird_Yellow birdYellow2;


    // Using in buttons only
    private Texture Black;
    private Texture Yellow;

    private InputMultiplexer inputMultiplexer;

    private Click_Button restart_button;
    private Texture restartB;
    private Texture restartA;

    private Texture baby_pig;


    private Baby_pig baby_pig1;


    private Baby_pig baby_pig2;
    private Baby_pig baby_pig3;

    private Texture adult_pig;

   ;
    private Adult_pig adult_pig1;


    private Adult_pig adult_pig2;


    private Adult_pig adult_pig3;
    // Modified pig positions
    private float Ap1_x = 1250;  // First adult pig - ground level
    private float Ap1_y = floorWidth + 200;

    private float Ap2_x = 1650;  // Second adult pig - ground level
    private float Ap2_y = floorWidth + 170;

//    private float Ap3_x = 1400;  // Third adult pig - middle level
//    private float Ap3_y = floorWidth + 250;

    private float Bp1_x = 1400;  // First baby pig
    private float Bp1_y = floorWidth + 10;

    private float Bp2_x = 1500;  // Second baby pig
    private float Bp2_y = floorWidth + 120;

    private float Kp1_x = 1400;  // King pig - top level
    private float Kp1_y = floorWidth + 200;



    private Texture king;

    private King_pig kingpin;

    private Block_Rectangle blockRectangle1;
    private Block_Rectangle blockRectangle2;
    private Block_Rectangle blockRectangle3;
    private Block_Rectangle blockRectangle4;
    private Block_Rectangle blockRectangle5;
    private Block_Rectangle blockRectangle6;
    private Block_Rectangle blockRectangle7;
//    private Block_Rectangle blockRectangle8;
//    private Block_Rectangle blockRectangle9;
//    private Block_Rectangle blockRectangle10;
//    private Block_Rectangle blockRectangle11;
//    private Block_Rectangle blockRectangle12;
//    private Block_Rectangle blockRectangle13;
//    private Block_Rectangle blockRectangle14;
//    private Block_Rectangle blockRectangle15;
//    private Block_Rectangle blockRectangle16;
//    private Block_Rectangle blockRectangle17;
//    private Block_Rectangle blockRectangle18;
//    private Block_Rectangle blockRectangle19;

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
    private boolean isPaused = false;
    private float start_time = 0;

    public level2(final launch game) {
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



    private void update(float delta) {

        if (pause_Button.clicked()) {
//            red1.destroy();
//            wait += delta;
//            if (wait >0.5f){
//                isPaused = true;
//                pause
            game.getPauseScreen().setCurrLevel(2);
            game.setScreen(game.getPauseScreen());

//                wait = 0;
//            }
        }
        if (restart_button.clicked()){
            game.setWorld(new World(new Vector2(0, -9.8f),true));
            game.setLevel_2(new level2(game));
            game.setScreen(game.getLevel_2());
        }


        if (kills<=0) {
            wait+=delta;
            if (wait>1){
                game.setLevel3_score(game.getLoginScreen().getUserid(), 1);
                game.setLevel2_score(game.getLoginScreen().getUserid(), score);
                game.getLevelPassed().setNext_level(3);
                game.getLevelPassed().setLevel_score(score);
                game.setScreen(game.getLevelPassed());
                wait = 0;
            }

        }
        else if(kills>0 && catapult_drag.birdsLeft()){
            game.getLevelFailed().setCurrLevel(2);
            game.setScreen(game.getLevelFailed());
//            System.out.printf("Fial");
        }
    }

    public Integer getScore(){
        return score;
    }
    public void setScore(Integer score){
        this.score = score;
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

//        birds.add(new Bird_Red(game, 300/PPM, (floorWidth+190)/PPM).getBody());
        this.birdRed1 = new Bird_Red(game, 360/PPM, (floorWidth+25)/PPM);
        birds.add(birdRed1.getBody());
        this.birdRed2 = new Bird_Red(game, 300/PPM, (floorWidth+25)/PPM);
        birds.add(birdRed2.getBody());
        this.birdYellow1 = new Bird_Yellow(game, 260/PPM, (floorWidth+25)/PPM);
        birds.add(birdYellow1.getBody());
        this.birdYellow2=new Bird_Yellow(game,180/PPM,(floorWidth+25)/PPM);
        birds.add(birdYellow2.getBody());
        this.birdBlack1 = new Bird_Black(game, 120/PPM, (floorWidth+25)/PPM);
        birds.add(birdBlack1.getBody());


        // pig
//        this.baby_pig1 = new Baby_pig(game, Bp1_x, Bp1_y);
//        this.baby_pig2 = new Baby_pig(game, Bp2_x, Bp2_y);
        this.adult_pig1 = new Adult_pig(game, Ap1_x, Ap1_y);
        this.adult_pig2 = new Adult_pig(game, Ap2_x, Ap2_y);
//        this.adult_pig3 = new Adult_pig(game, Ap3_x, Ap3_y);
        this.kingpin = new King_pig(game, Kp1_x, Kp1_y);
        this.baby_pig1 = new Baby_pig(game, Bp1_x, Bp1_y);
//        this.baby_pig2 = new Baby_pig(game, Bp2_x, Bp2_y);
//        this.adult_pig1 = new Adult_pig(game, Ap1_x, Ap1_y);
//        this.adult_pig2 = new Adult_pig(game, Ap2_x, Ap2_y);
//        this.baby_pig3 = new Baby_pig(game, Kp1_x, Kp1_y - 200);
//        this.adult_pig3 = new Adult_pig(game, Ap3_x, Ap3_y);
//        this.kingpin = new King_pig(game, Kp1_x, Kp1_y);

        // Base level - wide foundation
        this.blockRectangle1 = new Block_Rectangle(game, "rock", 1200, floorWidth, 80, 120); // Wider and taller base
        this.blockRectangle2 = new Block_Rectangle(game, "rock", 1600, floorWidth, 80, 120); // Wider and taller base

        this.blockRectangle3 = new Block_Rectangle(game, "rock", 1230, floorWidth + 120, 50, 120); // Centered on blockRectangle1
        this.blockRectangle4 = new Block_Rectangle(game, "rock", 1550, floorWidth + 120, 50, 120); // Centered on blockRectangle2


//         First level platforms - thick and stable
        this.blockRectangle5 = new Block_Rectangle(game, "rock", 1200, floorWidth + 200, 200, 30); // Slightly reduced width
        this.blockRectangle6 = new Block_Rectangle(game, "rock", 1460, floorWidth + 200 +5, 300, 30); // Slightly reduced width
//
//        // Vertical supports - thicker for stability
        this.blockRectangle7 = new Block_Rectangle(game, "rock", 1700, floorWidth, 80, 120);
//        this.blockRectangle8 = new Block_Rectangle(game, "rock", 1500, floorWidth + 130, 40, 120);
//
//        // Middle platform - single wide platform for better stability
//        this.blockRectangle9 = new Block_Rectangle(game, "rock", 1350, floorWidth + 230, 300, 30);
//
//        // Top structure
//        this.blockRectangle10 = new Block_Rectangle(game, "rock", 1400, floorWidth + 260, 40, 120);  // Central support
//        this.blockRectangle11 = new Block_Rectangle(game, "rock", 1350, floorWidth + 360, 100, 30);  // Top platform
//
//        // Additional wooden cross beams for stability
//        this.blockRectangle12 = new Block_Rectangle(game, "wood", 1250, floorWidth + 150, 150, 20);  // Left cross beam
//        this.blockRectangle13 = new Block_Rectangle(game, "wood", 1500, floorWidth + 150, 150, 20);  // Right cross beam
//        this.blockRectangle14 = new Block_Rectangle(game, "wood", 1350, floorWidth + 280, 150, 20);  // Middle cross beam
//        this.blockRectangle15 = new Block_Rectangle(game, "wood", 1450, floorWidth + 280, 150, 20);  // Middle cross beam
//
//        // Extra support blocks
//        this.blockRectangle16 = new Block_Rectangle(game, "wood", 1300, floorWidth + 50, 100, 20);  // Bottom support
//        this.blockRectangle17 = new Block_Rectangle(game, "wood", 1500, floorWidth + 50, 100, 20);  // Bottom support
//        this.blockRectangle18 = new Block_Rectangle(game, "rock", 1400, floorWidth + 180, 40, 80);  // Middle support
//        this.blockRectangle19 = new Block_Rectangle(game, "rock", 1400, floorWidth + 320, 40, 40);  // Top support

//        this.blockFrame1 = new Block_Frame(game,"Square", "wood",1055 , (floorWidth + 99 + 20 + 200 + 25) , 60);
//        this.blockFrame2 = new Block_Frame(game,"Triangle", "rock",1455 , (floorWidth + 99 + 20 + 200 + 25), 60);

        Gdx.input.setInputProcessor(inputMultiplexer);
        drawfloor();
        drawcatapult(350, 20);
        drawcatapult(1950, 100);
        this.catapult_drag = new Catapult(game,birds,335/PPM, (floorWidth+190)/PPM);
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
    @Override
    public void render(float deltaTime) {
        if (isPaused){
            return;
        }

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.setToOrtho(false, viewport.getWorldWidth()/(PPM), viewport.getWorldHeight()/(PPM));

        camera.zoom = 1;
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        start_time += deltaTime;
        catapult_drag.setCatapultInput(deltaTime);
        if (start_time >=1){
            world.setContactListener(game.getContactListener());

        }

        world.step(deltaTime,6,2);
        box2DDebugRenderer.render(world, camera.combined);

        for(Body b: destroyBody){
            if(b!= null && !destroyBody.isEmpty()){
                if (b.getUserData() instanceof Baby_pig){
                    score += 200;
                    kills--;
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
        birdYellow2.draw_object(batch);

        baby_pig1.draw_Pig(deltaTime, batch);
//        baby_pig2.draw_Pig(deltaTime, batch);
//        baby_pig1.draw_Pig(deltaTime, batch);
//        baby_pig2.draw_Pig(deltaTime, batch);
        adult_pig1.draw_Pig(deltaTime, batch);
        adult_pig2.draw_Pig(deltaTime, batch);
//        adult_pig3.draw_Pig(deltaTime, batch);
        kingpin.draw_Pig(deltaTime, batch);
//        baby_pig3.draw_Pig(deltaTime, batch);

        pause_Button.draw(batch);
        restart_button.draw(batch);

        font.getData().setScale((float) (0.9/PPM));
//        font.getData().setSpacing(2f);

        font.draw(batch, "Score", (viewport.getWorldWidth()-300)/PPM, (viewport.getWorldHeight()-10)/PPM);
        float s =score.toString().length()/PPM;
        font.getData().setScale((float) (0.9));
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, score.toString());
        font.getData().setScale((float) (0.9/PPM));


        font.draw(batch, score.toString() , (viewport.getWorldWidth())/PPM - glyphLayout.width/PPM, (viewport.getWorldHeight()-80)/PPM);


        blockRectangle1.draw_Block(deltaTime, batch);
        blockRectangle2.draw_Block(deltaTime, batch);
        blockRectangle3.draw_Block(deltaTime, batch);
        blockRectangle4.draw_Block(deltaTime, batch);

        // First level platforms
        blockRectangle5.draw_Block(deltaTime, batch);
        blockRectangle6.draw_Block(deltaTime, batch);
//
        // Vertical supports
        blockRectangle7.draw_Block(deltaTime, batch);
//        blockRectangle8.draw_Block(deltaTime, batch);
//
//        // Middle platform
//        blockRectangle9.draw_Block(deltaTime, batch);
//
//        // Top structure
//        blockRectangle10.draw_Block(deltaTime, batch);
//        blockRectangle11.draw_Block(deltaTime, batch);
//
//        // Cross beams and additional supports
//        blockRectangle12.draw_Block(deltaTime, batch);
//        blockRectangle13.draw_Block(deltaTime, batch);
//        blockRectangle14.draw_Block(deltaTime, batch);
//        blockRectangle15.draw_Block(deltaTime, batch);
//        blockRectangle16.draw_Block(deltaTime, batch);
//        blockRectangle17.draw_Block(deltaTime, batch);
//        blockRectangle18.draw_Block(deltaTime, batch);
//        blockRectangle19.draw_Block(deltaTime, batch);

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
        birdRed1.set_bird(360/PPM, (floorWidth+25)/PPM);
        birdRed2.set_bird(300/PPM, (floorWidth+25)/PPM);
        birdYellow1.set_bird(260/PPM, (floorWidth+25)/PPM);
        birdYellow2.set_bird(180/PPM,(floorWidth+25)/PPM);
        birdBlack1.set_bird(115/PPM, (floorWidth+25)/PPM);



        baby_pig1.setPig(Bp1_x, Bp1_y);
//        baby_pig2.setPig(Bp2_x,Bp2_y);
        adult_pig1.setPig(Ap1_x, Ap1_y);
        adult_pig2.setPig(Ap2_x, Ap2_y);
//        adult_pig3.setPig(Ap3_x, Ap3_y);
        kingpin.setPig(Kp1_x, Kp1_y);
//        baby_pig3.setPig(Kp1_x, Kp1_y - 200);
        // 5
        blockRectangle1.setblock(1200, Bp1_y+50);
        blockRectangle2.setblock(1600, Bp1_y+50);
        blockRectangle3.setblock(1300, Bp1_y+50);
        blockRectangle4.setblock(1500, Bp1_y+50);
        blockRectangle5.setblock(1200, Bp1_y + 120);
        blockRectangle6.setblock(1450, Bp1_y + 120);
        blockRectangle7.setblock(1700, Bp1_y+50);
//        blockRectangle8.setblock(1500, floorWidth + 130);
//        blockRectangle9.setblock(1350, floorWidth + 230);
//        blockRectangle10.setblock(1400, floorWidth + 260);
//        blockRectangle11.setblock(1350, floorWidth + 360);
//        blockRectangle12.setblock(1250, floorWidth + 150);
//        blockRectangle13.setblock(1500, floorWidth + 150);

//        blockFrame1.setblock(1055 , floorWidth + 99 + 20 + 200 + 25);

    }

    @Override
    public void pause() {
//        baby_pig1.setPig(baby_pig1.getBody().getPosition().x, baby_pig1.getBody().getPosition().y);
    }
    @Override
    public void resume() {
//        baby_pig1.setPig(baby_pig1.getBody().getPosition().x, baby_pig1.getBody().getPosition().y);
    }

    @Override
    public void hide() {


    }

    @Override
    public void dispose() {
        level_image.dispose();
        cata.dispose();
        pause_before.dispose();
        pause_after.dispose();
        birdRed1.dispose();
        birdRed2.dispose();
        restartA.dispose();
        restartB.dispose();
        birdBlack1.dispose();
        Black.dispose();
        Yellow.dispose();
        adult_pig.dispose();
        adult_pig2.dispose();
        adult_pig3.dispose();
        kingpin.dispose();
        baby_pig.dispose();
        baby_pig2.dispose();
        baby_pig1.dispose();
        king.dispose();
        birdRed1.dispose();
        birdRed2.dispose();
        birdYellow2.dispose();
        adult_pig1.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}
