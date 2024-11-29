package com.Angry_Bird;

import com.Angry_Bird.Birds.Bird_Red;
import com.Angry_Bird.Blocks.Block_Frame;
import com.Angry_Bird.Blocks.Block_Rectangle;
import com.Angry_Bird.Buttons.Catapult;
import com.Angry_Bird.Pig.Adult_pig;
import com.Angry_Bird.Pig.Baby_pig;
import com.Angry_Bird.Pig.King_pig;
import com.Angry_Bird.Screen.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import java.util.HashMap;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.*;
import java.util.ArrayList;



class Data implements Serializable {
    private static final long serialVersionUID = 1L;

    private String user_id;
    private String password;
    private float level1_score = 0;
    private float level2_score = 0;
    private float level3_score = 0;

    public Data(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getUser_id(){
        return user_id;
    }
    public String getPassword(){
        return password;
    }
    public float getL1_score(){
        return level1_score;
    }
    public float getL2_score(){
        return level2_score;
    }
    public float getL3_score(){
        return level3_score;
    }
    public void setL1_score(float level1_score){
        this.level1_score += level1_score;
    }
    public void setL2_score(float level2_score){
        this.level2_score += level2_score;
    }
    public void setL3_score(float level3_score){
        this.level3_score += level3_score;
    }
}



public class launch extends Game {

    private static HashMap<String, Data> data = new HashMap<>();

    private static final String FILE_NAME = "userdata.ser";

    private void ensureDataLoaded() {
        if (data == null || data.isEmpty()) {
            loadData();
        }
    }

    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(data);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            data = (HashMap<String, Data>) ois.readObject();
            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data file found. Starting fresh.");
            data = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean found_id(String id) {
        ensureDataLoaded();
        return data.containsKey(id);
    }

    public boolean found_password(String id, String password) {
        ensureDataLoaded();
        Data user = data.get(id);
        return user != null && user.getPassword().equals(password);
    }

    public void signUp(String id, String password) {
        ensureDataLoaded();
        data.put(id, new Data(id, password));
        saveData();
    }

    public void eraseData(String id) {
        ensureDataLoaded();
        data.remove(id);
        saveData();
    }

    public Data getData(String id) {
        ensureDataLoaded();
        return data.get(id);
    }

    public float getLevel1_score(String id) {
        ensureDataLoaded();
        return data.get(id).getL1_score();
    }

    public float getLevel2_score(String id) {
        ensureDataLoaded();
        return data.get(id).getL2_score();
    }

    public float getLevel3_score(String id) {
        ensureDataLoaded();
        return data.get(id).getL3_score();
    }

    public void setLevel1_score(String id, float level1_score) {
        ensureDataLoaded();
        data.get(id).setL1_score(level1_score);
        saveData();
    }

    public void setLevel2_score(String id, float level2_score) {
        ensureDataLoaded();
        data.get(id).setL2_score(level2_score);
        saveData();
    }

    public void setLevel3_score(String id, float level3_score) {
        ensureDataLoaded();
        data.get(id).setL3_score(level3_score);
        saveData(); // Save data after updating score
    }

    private SpriteBatch batch;
    private AssetManager assetManager;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    static private Integer coin = 0;

    // Sound and Music
    private static Music music;
    public Music getMusic() {
        return music;
    }
    public void setMusic(Music music){
        this.music = music;
    }
    public void playMusic(){
        music.play();
    }
    public void pauseMusic(){
        music.pause();
    }


    private static Sound sound;
    private static boolean sound_ON = true;

    public boolean is_sound_ON(){
        return sound_ON;
    }
    public Sound getSound() {
        return sound;
    }
    public void setSound(Sound sound) {
        this.sound = sound;
    }
    public void resumeSound(){
        sound_ON = true;
        sound.resume();
    }
    public void pauseSound(){
        sound_ON = false;
        sound.pause();
    }

    // Screens instance
    private static load_Screen loadScreen ;
    private static MainMenuScreen mainMenuScreen;
    private static LoginScreen loginScreen;
    private static Shop_Screen shopScreen;
    private static Exit_Screen exitScreen;
    private static Level_Screen levelScreen;
    private static level1 level_1;
    private static level2 level_2;
    private static level3 level_3;
    private static Level_Failed levelFailed;
    private static Level_Passed levelPassed;
    private static Pause_Screen pauseScreen;
    private static Setting_Screen settingScreen;

    public Setting_Screen getSettingScreen() {
        return settingScreen;
    }

    public void setSettingScreen(Setting_Screen settingScreen) {
        this.settingScreen = settingScreen;
    }

    public Pause_Screen getPauseScreen() {
        return pauseScreen;
    }

    public void setPauseScreen(Pause_Screen pauseScreen) {
        this.pauseScreen = pauseScreen;
    }

    public Level_Passed getLevelPassed() {
        return levelPassed;
    }

    public void setLevelPassed(Level_Passed levelPassed) {
        this.levelPassed = levelPassed;
    }

    public Level_Failed getLevelFailed() {
        return levelFailed;
    }

    public void setLevelFailed(Level_Failed levelFailed) {
        this.levelFailed = levelFailed;
    }

    public level1 getLevel_1() {
        return level_1;
    }

    public level2 getLevel_2() { return level_2;}

    public level3 getLevel_3() { return level_3 ; }

    public void setLevel_1(level1 level_1) {
        this.level_1 = level_1;
    }

    public void setLevel_2(level2 level_2) { this.level_2 = level_2;}

    public void setLevel_3(level3 level_3) { this.level_3 = level_3;}

    public Level_Screen getLevelScreen() {
        return levelScreen;
    }

    public void setLevelScreen(Level_Screen levelScreen) {
        this.levelScreen = levelScreen;
    }

    public Exit_Screen getExitScreen() {
        return exitScreen;
    }

    public void setExitScreen(Exit_Screen exitScreen) {
        this.exitScreen = exitScreen;
    }

    public Shop_Screen getShopScreen() {
        return shopScreen;
    }

    public void setShopScreen(Shop_Screen shopScreen) {
        this.shopScreen = shopScreen;
    }

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }

    public void setLoginScreen(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
    }

    public load_Screen getLoadScreen() {
        return loadScreen;
    }

    public void setLoadScreen(load_Screen loadScreen) {
        this.loadScreen = loadScreen;
    }

    public AssetManager getAssetManager(){
        return assetManager;
    }

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

    private static World world;

    public World getWorld() {
        return world;
    }
    public void setWorld(World world){ this.world = world;}
    private static float PPM;

    public float getPPM() {
        return PPM;
    }

    private ShapeRenderer shapeRenderer;

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    private static ContactListener contactListener;
    private static ArrayList<Body> destorybody = new ArrayList<>();

    public  ContactListener getContactListener() {
        return contactListener;
    }
    public  ArrayList<Body> getDestorybody() {
        return destorybody;
    }
    public void setDestorybody(ArrayList<Body> destorybody) {
        launch.destorybody = destorybody;
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1920, 1080, camera);
        viewport.apply();
        PPM = 30f;
        world = new World(new Vector2(0, -9.8f), true);

        contactListener = new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {
                float impulse = 0;
                for (int i = 0; i < contactImpulse.getCount(); i++) {
                    impulse += contactImpulse.getNormalImpulses()[i];
                }

                float impulseThreshold = 20f;
                if (impulse > impulseThreshold) {
//                    System.out.println("Collision detected with impulse: " + impulse);
//                    for (Contact i: world.getContactList()){
//
//                    }
                    // king pig to red bird
                    if (contact.getFixtureA().getBody().getUserData() instanceof King_pig){
                        King_pig kingPig = (King_pig) contact.getFixtureA().getBody().getUserData();
                        float damage = (impulse);
                        kingPig.reducehealth(damage);
                        if (kingPig.isDestroyed() && !destorybody.contains(contact.getFixtureA().getBody())){
                            destorybody.add(contact.getFixtureA().getBody());
                        }
                    }
                    if (contact.getFixtureB().getBody().getUserData() instanceof King_pig){
                        King_pig kingPig = (King_pig) contact.getFixtureB().getBody().getUserData();
                        float damage = (impulse);
                        kingPig.reducehealth(damage);
                        if (kingPig.isDestroyed() && !destorybody.contains(contact.getFixtureB().getBody())){
                            destorybody.add(contact.getFixtureB().getBody());
                        }
                    }

                    if (contact.getFixtureA().getBody().getUserData() instanceof Adult_pig){
                        Adult_pig adultPig = (Adult_pig) contact.getFixtureA().getBody().getUserData();
                        float damage = (impulse);
                        adultPig.reducehealth(damage);
                        if (adultPig.isDestroyed() && !destorybody.contains(contact.getFixtureA().getBody())){
                            destorybody.add(contact.getFixtureA().getBody());
                        }
                    }
                    if (contact.getFixtureB().getBody().getUserData() instanceof Adult_pig){
                        Adult_pig adultPig = (Adult_pig) contact.getFixtureB().getBody().getUserData();
                        float damage = (impulse);
                        adultPig.reducehealth(damage);
                        if (adultPig.isDestroyed() && !destorybody.contains(contact.getFixtureB().getBody())){
                            destorybody.add(contact.getFixtureB().getBody());
                        }
                    }
                    if (contact.getFixtureA().getBody().getUserData() instanceof Baby_pig){
                        Baby_pig babyPig = (Baby_pig) contact.getFixtureA().getBody().getUserData();
                        float damage = (impulse);
                        babyPig.reducehealth(damage);
                        if (babyPig.isDestroyed() && !destorybody.contains(contact.getFixtureA().getBody())){
                            destorybody.add(contact.getFixtureA().getBody());
                        }
                    }
                    if (contact.getFixtureB().getBody().getUserData() instanceof Baby_pig){
                        Baby_pig babyPig = (Baby_pig) contact.getFixtureB().getBody().getUserData();
                        float damage = (impulse);
                        babyPig.reducehealth(damage);
                        if (babyPig.isDestroyed() && !destorybody.contains(contact.getFixtureB().getBody())){
                            destorybody.add(contact.getFixtureB().getBody());
                        }
                    }
                    if (contact.getFixtureB().getBody().getUserData() instanceof Block_Frame){
                        Block_Frame blockFrame = (Block_Frame) contact.getFixtureB().getBody().getUserData();
                        float damage = (impulse);
                        blockFrame.reducehealth(damage);
                        if (blockFrame.isDestroyed() && !destorybody.contains(contact.getFixtureB().getBody())){
                            destorybody.add(contact.getFixtureB().getBody());
                        }
                    }
                    if (contact.getFixtureA().getBody().getUserData() instanceof Block_Frame){
                        Block_Frame blockFrame = (Block_Frame) contact.getFixtureA().getBody().getUserData();
                        float damage = (impulse);
                        blockFrame.reducehealth(damage);
                        if (blockFrame.isDestroyed() && !destorybody.contains(contact.getFixtureA().getBody())){
                            destorybody.add(contact.getFixtureA().getBody());
                        }
                    }
                    if (contact.getFixtureA().getBody().getUserData() instanceof Block_Rectangle){
                        Block_Rectangle blockRectangle = (Block_Rectangle) contact.getFixtureA().getBody().getUserData();
                        float damage = (impulse);

                        blockRectangle.reducehealth(damage);
                        if (blockRectangle.isDestroyed() && !destorybody.contains(contact.getFixtureA().getBody())){
                            destorybody.add(contact.getFixtureA().getBody());
                        }
                    }
                    if (contact.getFixtureB().getBody().getUserData() instanceof Block_Rectangle){
                        Block_Rectangle blockRectangle = (Block_Rectangle) contact.getFixtureB().getBody().getUserData();
                        float damage = (impulse);

                        blockRectangle.reducehealth(damage);
                        if (blockRectangle.isDestroyed() && !destorybody.contains(contact.getFixtureB().getBody())){
                            destorybody.add(contact.getFixtureB().getBody());
                        }
                    }


                }
            }
        };
        world.setContactListener(getContactListener());
        data = new HashMap<>();

        //----------------font type casting
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
        //--------------------

        music = Gdx.audio.newMusic(Gdx.files.internal("music1.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        music.pause();//----------------

        sound = Gdx.audio.newSound(Gdx.files.internal("click1.mp3"));
        sound.resume();
        pauseSound();//----------------

        loadScreen = new load_Screen(this);
        mainMenuScreen = new MainMenuScreen(this);
        loginScreen = new LoginScreen(this, mainMenuScreen);
        shopScreen = new Shop_Screen(this, mainMenuScreen);
        settingScreen = new Setting_Screen(this, mainMenuScreen);
        exitScreen = new Exit_Screen(this, mainMenuScreen);
        levelScreen = new Level_Screen(this, mainMenuScreen);
        level_1 = new level1(this);
        level_2 = new level2(this);
        level_3 = new level3(this);
        levelFailed = new Level_Failed(this, level_1);
        levelPassed = new Level_Passed(this, level_1);
        pauseScreen = new Pause_Screen(this);

        this.setScreen(loadScreen);
//        this.setScreen(new Catapult(this,0,0));
//        this.setScreen(new load_Screen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override

    public void dispose() {
        batch.dispose();
        font.dispose();
        getScreen().dispose();
        assetManager.dispose();
        music.dispose();
        sound.dispose();
        super.dispose();
    }
}
