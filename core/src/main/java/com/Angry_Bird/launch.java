package com.Angry_Bird;

import com.Angry_Bird.Screen.Level_Passed;
import com.Angry_Bird.Screen.MainMenuScreen;
import com.Angry_Bird.Screen.level1;
import com.Angry_Bird.Screen.load_Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class launch extends Game {
    private SpriteBatch batch;
//    private AssetManager assetManager;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    static private Integer coin = 0;

    public void buy_coin(int sum){
        coin += sum;
    }

    public int getCoin(){
        return coin;
    }
    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public BitmapFont getFont() {
        return font;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
//        assetManager = new AssetManager();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920, 1080, camera);
        viewport.apply();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fnt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;

        parameter.shadowColor = new Color(0.2f, 0.2f, 0.2f, 1);

        parameter.borderWidth = 2;
        parameter.borderColor = Color.LIGHT_GRAY;

        font = generator.generateFont(parameter);
        generator.dispose();

        // could not use asset load
        this.setScreen(new load_Screen(this));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();

        getScreen().dispose();
//        assetManager.dispose();

    }


}


