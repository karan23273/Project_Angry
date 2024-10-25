package com.Angry_Bird.Screen;

import com.Angry_Bird.Birds.Bird_Black;
import com.Angry_Bird.Birds.Bird_Red;
import com.Angry_Bird.Birds.Bird_Yellow;
import com.Angry_Bird.Blocks.Block_Frame;
import com.Angry_Bird.Blocks.Block_Rectangle;
import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.Pig.Adult_pig;
import com.Angry_Bird.Pig.Baby_pig;
import com.Angry_Bird.Pig.King_pig;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class level1 implements Screen {
    private final Integer score = 0;
    private final launch game;
    private final float floor = 196;
    private OrthographicCamera camera;
    private Viewport viewport;

    private SpriteBatch batch;
    private Texture level_image;

    private Texture catapult;
    private BitmapFont font;

    private Click_Button pause_Button;
    private Texture pause_before;
    private Texture pause_after;

    private Bird_Red birdRed1;
    private Bird_Red birdRed2;
    private Bird_Black birdBlack1;
    private Bird_Yellow birdYellow1;

    private Click_Button destroy_r1;
    private Click_Button destroy_r2;
    private Click_Button destroy_b1;
    private Click_Button destroy_y1;

    // Using in buttons only
    private Texture Red;
    private Texture Black;
    private Texture Yellow;

    private InputMultiplexer inputMultiplexer;

    private Click_Button restart_button;
    private Texture restartB;
    private Texture restartA;

    private Texture baby_pig;
    private float Bp1_x = 1455 + 49;
    private float Bp1_y = floor + 99 + 20 + 200 + 25 + 35;

    private Baby_pig baby_pig1;
    private Click_Button destroy_baby1;
    private float Bp2_x = 1055 + 49;
    private float Bp2_y = floor + 99 + 20 + 200 + 25 + 35;

    private Baby_pig baby_pig2;
    private Click_Button destroy_baby2;

    private Texture adult_pig;

    private float Ap1_x = 1050+30;
    private float Ap1_y = floor+99+20;
    private Adult_pig adult_pig1;
    private Click_Button destroy_adult1;

    private float Ap2_x = 1450+30;
    private float Ap2_y = floor+99+20;
    private Adult_pig adult_pig2;
    private Click_Button destroy_adult2;

    private float Ap3_x = 1455;
    private float Ap3_y = floor+99+20 + 55 + 25 + 25;
    private Adult_pig adult_pig3;
    private Click_Button destroy_adult3;


    private Texture king;
    private float Kp1_x = 1055;
    private float Kp1_y = floor + 99 + 20 + 55 + 25 + 25;
    private King_pig kingpin;
    private Click_Button destroy_king;


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

//    private Stage stage;

    public level1(final launch game) {
        this.game = game;
        this.camera = game.getCamera();
        this.viewport = game.getViewport();
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }



    private void update(float delta) {
        int birds = 4, pigs  = 6;

        if (pause_Button.clicked()) {
//            red1.destroy();
            game.setScreen(new Pause_Screen(game, this));
        }
        if (restart_button.clicked()){
            game.setScreen(new level1(game));
        }
        if (destroy_r1.clicked()) {
            birdRed1.destroy();
            birds--;
        }
        if (destroy_r2.clicked()) {
            birdRed2.destroy();
            birds--;
        }
        if (destroy_b1.clicked()) {
            birdBlack1.destroy();
            birds--;
        }
        if (destroy_y1.clicked()) {
            birdYellow1.destroy();
            birds--;
        }
        if (destroy_adult1.clicked()){
            adult_pig1.destroy();
//            score += 500;
            pigs--;
        }
        if (destroy_adult2.clicked()){
            adult_pig2.destroy();
//            score += 500;
            pigs--;
        }
        if (destroy_adult3.clicked()){
            adult_pig3.destroy();
//            score += 500;
            pigs--;
        }
        if (destroy_baby1.clicked()){
            baby_pig1.destroy();
//            score += 250;
            pigs--;
        }
        if (destroy_baby2.clicked()){
            baby_pig2.destroy();
//            score += 250;
            pigs--;
        }
        if (destroy_king.clicked()){
            kingpin.destroy();
//            score += 2000;
            pigs--;
        }

        if(birds == 0 && pigs > 0){
            game.setScreen(new Level_Failed(game, this));
        } else if (pigs == 0) {
            game.setScreen(new Level_Passed(game,this));
        }
    }

    @Override
    public void show() {
        this.level_image = new Texture("A2.png");
        this.catapult = new Texture("catapult.png");
        this.pause_before = new Texture("pauseB.png");
        this.pause_after = new Texture("pauseA.png");
        this.Red = new Texture("red40.png");
        this.Black = new Texture("black.png");
        this.Yellow = new Texture("yellowA.png");
        this.restartB = new Texture("restartB.png");
        this.restartA = new Texture("restartA.png");
        this.adult_pig = new Texture("AdultpigA.png");
        this.baby_pig = new Texture("BabyPigA.png");
        this.king = new Texture("kingPinA.png");


        this.pause_Button = new Click_Button(pause_before, pause_after, 15, viewport.getWorldHeight()-165, camera);
        pause_Button.setInput(inputMultiplexer);

        this.restart_button = new Click_Button(restartB, restartA, 200, viewport.getWorldHeight()-165, camera);
        restart_button.setInput(inputMultiplexer);

        this.birdRed1 = new Bird_Red(game);
        this.birdRed2 = new Bird_Red(game);
        this.birdBlack1 = new Bird_Black(game);
        this.birdYellow1 = new Bird_Yellow(game);
        // pig
        this.baby_pig1 = new Baby_pig(game);
        this.baby_pig2 = new Baby_pig(game);
        this.kingpin = new King_pig(game);
        this.adult_pig1 = new Adult_pig(game);
        this.adult_pig2 = new Adult_pig(game);
        this.adult_pig3 = new Adult_pig(game);
        this.blockRectangle1 = new Block_Rectangle("wood", 1200 ,floor ,20, 100);
        this.blockRectangle2 = new Block_Rectangle("wood", 1000,floor,20, 100);


        this.blockRectangle4 = new Block_Rectangle("wood", 1600,floor,20, 100);
        this.blockRectangle5 = new Block_Rectangle("wood", 1400,floor,20, 100);

        this.blockRectangle3 = new Block_Rectangle("rock", 1000,floor + 99,220, 20);
        this.blockRectangle6 = new Block_Rectangle("rock", 1400,floor + 99,220, 20f);

        this.blockRectangle8 = new Block_Rectangle("wood", 1055,floor + 99 + 20,25, 55);
        this.blockRectangle9 = new Block_Rectangle("wood", 1140,floor + 99 + 20,25, 55);
        this.blockRectangle7 = new Block_Rectangle("wood", 1455,floor + 99 + 20,25, 55);
        this.blockRectangle10 = new Block_Rectangle("wood", 1540,floor + 99 + 20,25, 55);

        this.blockRectangle15 = new Block_Rectangle("wood", 1000+20,floor + 99 + 20,25, 200);
        this.blockRectangle17 = new Block_Rectangle("wood", 1200-20,floor + 99 + 20,25, 200);
        this.blockRectangle16 = new Block_Rectangle("wood", 1400+20,floor + 99 + 20,25, 200);
        this.blockRectangle18 = new Block_Rectangle("wood", 1600-20,floor + 99 + 20,25, 200);

        this.blockRectangle11 = new Block_Rectangle("wood", 1055, floor + 99 + 20 + 55,110, 25);
        this.blockRectangle12 = new Block_Rectangle("wood", 1455,floor + 99 + 20 + 55,110, 25);

        this.blockRectangle13 = new Block_Rectangle("rock", 1055,floor + 99 + 20 + 55 + 25,110, 25);
        this.blockRectangle14 = new Block_Rectangle("rock", 1455,floor + 99 + 20 + 55 + 25,110, 25);


        this.blockRectangle19 = new Block_Rectangle("rock", 1000, floor + 99 + 20 + 200,620, 25);

        this.blockFrame1 = new Block_Frame("Triangle", "rock",1055 ,floor + 99 + 20 + 200 + 25 , 140);
        this.blockFrame2 = new Block_Frame("Triangle", "rock",1455 ,floor + 99 + 20 + 200 + 25 , 140);


        destroy_r1 = new Click_Button(Red,Red, 200,floor,camera);
        destroy_r2 = new Click_Button(Red,Red, 160,floor,camera);
        destroy_b1 = new Click_Button(Black,Black, 20,floor,camera);
        destroy_y1 = new Click_Button(Yellow,Yellow, 100,floor,camera);

        destroy_baby1 = new Click_Button(baby_pig,baby_pig, Bp1_x,Bp1_y,camera);
        destroy_baby2 = new Click_Button(baby_pig,baby_pig, Bp2_x,Bp2_y,camera);

        destroy_adult1 = new Click_Button(adult_pig, adult_pig, Ap1_x, Ap1_y,camera);//--------------
        destroy_adult2 = new Click_Button(adult_pig, adult_pig, Ap2_x, Ap2_y,camera);//
        destroy_adult3 = new Click_Button(adult_pig, adult_pig, Ap3_x, Ap3_y,camera);

        destroy_king = new Click_Button(king,king, Kp1_x, Kp1_y,camera);

        destroy_r1.setInput(inputMultiplexer);
        destroy_r2.setInput(inputMultiplexer);
        destroy_b1.setInput(inputMultiplexer);
        destroy_y1.setInput(inputMultiplexer);

        destroy_baby1.setInput(inputMultiplexer);
        destroy_baby2.setInput(inputMultiplexer);
        destroy_adult1.setInput(inputMultiplexer);
        destroy_adult2.setInput(inputMultiplexer);
        destroy_adult3.setInput(inputMultiplexer);
        destroy_king.setInput(inputMultiplexer);

        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
//        font.getData().setScale(0.9f);
        batch.draw(level_image,0, 0,  viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.draw(catapult, 300, floor, 80, 200);
        birdRed1.draw_object(batch);
        birdRed2.draw_object(batch);
        birdBlack1.draw_object(batch);
        birdYellow1.draw_object(batch);
        baby_pig1.draw_Pig(batch);
        baby_pig2.draw_Pig(batch);
        adult_pig1.draw_Pig(batch);
        adult_pig2.draw_Pig(batch);
        adult_pig3.draw_Pig(batch);
        kingpin.draw_Pig(batch);
        pause_Button.draw(batch);
        restart_button.draw(batch);
        font.getData().setScale(0.9f);

        font.draw(batch, "Score", viewport.getWorldWidth()-250, viewport.getWorldHeight()-10);
        int s =score.toString().length();
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, score.toString());

        font.draw(batch, score.toString(), viewport.getWorldWidth()-glyphLayout.width, viewport.getWorldHeight()-glyphLayout.height);

        blockRectangle1.draw_obstacle(batch);
        blockRectangle2.draw_obstacle(batch);
        blockRectangle3.draw_obstacle(batch);
        blockRectangle4.draw_obstacle(batch);
        blockRectangle5.draw_obstacle(batch);
        blockRectangle6.draw_obstacle(batch);
        blockRectangle7.draw_obstacle(batch);
        blockRectangle8.draw_obstacle(batch);
        blockRectangle9.draw_obstacle(batch);
        blockRectangle10.draw_obstacle(batch);
        blockRectangle11.draw_obstacle(batch);
        blockRectangle12.draw_obstacle(batch);
        blockRectangle13.draw_obstacle(batch);
        blockRectangle14.draw_obstacle(batch);
        blockRectangle15.draw_obstacle(batch);
        blockRectangle16.draw_obstacle(batch);
        blockRectangle17.draw_obstacle(batch);
        blockRectangle18.draw_obstacle(batch);
        blockRectangle19.draw_obstacle(batch);

        blockFrame1.draw_frame(batch);
        blockFrame2.draw_frame(batch);
        batch.end();
        update(v);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
        camera.position.set(viewport.getWorldWidth() / 2 , viewport.getWorldHeight() / 2, 0);
        camera.update();

        pause_Button.set_Position(15, viewport.getWorldHeight()-165);
        restart_button.set_Position(200, viewport.getWorldHeight()-165);
        birdRed1.set_bird(200, floor);
        destroy_r1.set_Position(200,floor);

        birdRed2.set_bird(160, floor);
        destroy_r2.set_Position(160,floor);

        birdBlack1.set_bird(20, floor);
        destroy_b1.set_Position(10,floor);

        birdYellow1.set_bird(100, floor);
        destroy_y1.set_Position(100,floor);

        // PIGS
        baby_pig1.setPig(Bp1_x, Bp1_y);
        destroy_baby1.set_Position(Bp1_x, Bp1_y);

        baby_pig2.setPig(Bp2_x,Bp2_y);
        destroy_baby2.set_Position(Bp2_x,Bp2_y);

        adult_pig1.setPig(Ap1_x,Ap1_y);//------------------
        destroy_adult1.set_Position(Ap1_x,Ap1_y);//------------
        adult_pig2.setPig(Ap2_x, Ap2_y);//---------------
        destroy_adult2.set_Position(Ap2_x, Ap2_y);//--------------
        adult_pig3.setPig(Ap3_x,Ap3_y);
        destroy_adult3.set_Position(Ap3_x,Ap3_y);
        kingpin.setPig(Kp1_x,Kp1_y);
        destroy_king.set_Position(Kp1_x,Kp1_y);

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
        level_image.dispose();
        catapult.dispose();
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
        adult_pig1.dispose();

    }
}
