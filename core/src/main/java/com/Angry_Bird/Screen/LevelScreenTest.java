//package com.Angry_Bird.Screen;
//
//import com.Angry_Bird.Screen.Level_Screen;
//import com.Angry_Bird.Screen.MainMenuScreen;
//import com.Angry_Bird.Screen.level1;
//import com.Angry_Bird.Screen.level2;
//import com.Angry_Bird.Screen.level3;
//import com.Angry_Bird.launch;
//import com.Angry_Bird.Buttons.Click_Button;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Graphics;
//import com.badlogic.gdx.InputMultiplexer;
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.utils.viewport.Viewport;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//public class LevelScreenTest {
//    @Mock
//    private launch mockGame;
//
//    @Mock
//    private MainMenuScreen mockMainMenuScreen;
//
//    @Mock
//    private OrthographicCamera mockCamera;
//
//    @Mock
//    private Viewport mockViewport;
//
//    @Mock
//    private SpriteBatch mockBatch;
//
//    @Mock
//    private AssetManager mockAssetManager;
//
//    @Mock
//    private BitmapFont mockFont;
//
//    @Mock
//    private Texture mockTexture;
//
//    private Level_Screen levelScreen;
//
//    @Before
//    public void setUp() {
//        // Initialize Mockito annotations
//        MockitoAnnotations.initMocks(this);
//
//        // Set up common mock behaviors
//        when(mockGame.getCamera()).thenReturn(mockCamera);
//        when(mockGame.getViewport()).thenReturn(mockViewport);
//        when(mockGame.getBatch()).thenReturn(mockBatch);
//        when(mockGame.getAssetManager()).thenReturn(mockAssetManager);
//        when(mockGame.getFont()).thenReturn(mockFont);
//
//        // Mock viewport world dimensions
//        when(mockViewport.getWorldWidth()).thenReturn(1920f);
//        when(mockViewport.getWorldHeight()).thenReturn(1080f);
//
//        // Mock textures for asset loading
//        when(mockAssetManager.get(anyString(), eq(Texture.class))).thenReturn(mockTexture);
//        when(mockTexture.getWidth()).thenReturn(100);
//        when(mockTexture.getHeight()).thenReturn(100);
//
//        // Create the Level_Screen instance
//        levelScreen = new Level_Screen(mockGame, mockMainMenuScreen);
//    }
//
//    @Test
//    public void testConstructor() {
//        assertNotNull(levelScreen);
//    }
//
//    @Test
//    public void testShow() {
//        // Call show method
//        levelScreen.show();
//
//        // Verify asset loading
//        verify(mockAssetManager).get("level.png", Texture.class);
//        verify(mockAssetManager).get("back.png", Texture.class);
//        verify(mockAssetManager).get("backA.png", Texture.class);
//        verify(mockAssetManager).get("levelFrame.png", Texture.class);
//        verify(mockAssetManager).get("lock.png", Texture.class);
//        verify(mockAssetManager).get("levelFrameB.png", Texture.class);
//        verify(mockAssetManager).get("levelFrameA.png", Texture.class);
//        verify(mockAssetManager, times(3)).get("star.png", Texture.class);
//    }
//
//    @Test
//    public void testResize() {
//        // Call resize method
//        levelScreen.resize(800, 600);
//
//        // Verify viewport update is called
//        verify(mockViewport).update(800, 600);
//
//        // Verify camera position is set
//        verify(mockCamera).position.set(anyFloat(), anyFloat(), anyFloat());
//        verify(mockCamera).update();
//    }
//
//    @Test
//    public void testButtonNavigation() throws Exception {
//        // Mock show to set up buttons
//        levelScreen.show();
//
//        // Use reflection to access private buttons
//        java.lang.reflect.Field noButtonField = Level_Screen.class.getDeclaredField("no_Button");
//        noButtonField.setAccessible(true);
//        Click_Button noButton = (Click_Button) noButtonField.get(levelScreen);
//
//        java.lang.reflect.Field level1ButtonField = Level_Screen.class.getDeclaredField("level1_button");
//        level1ButtonField.setAccessible(true);
//        Click_Button level1Button = (Click_Button) level1ButtonField.get(levelScreen);
//
//        java.lang.reflect.Field level2ButtonField = Level_Screen.class.getDeclaredField("level2_button");
//        level2ButtonField.setAccessible(true);
//        Click_Button level2Button = (Click_Button) level2ButtonField.get(levelScreen);
//
//        java.lang.reflect.Field level3ButtonField = Level_Screen.class.getDeclaredField("level3_button");
//        level3ButtonField.setAccessible(true);
//        Click_Button level3Button = (Click_Button) level3ButtonField.get(levelScreen);
//
//        // Simulate button clicks
//        when(noButton.clicked()).thenReturn(true);
//        when(level1Button.clicked()).thenReturn(true);
//        when(level2Button.clicked()).thenReturn(true);
//        when(level3Button.clicked()).thenReturn(true);
//
//        // Call render to trigger update
//        java.lang.reflect.Method renderMethod = Level_Screen.class.getDeclaredMethod("update", float.class);
//        renderMethod.setAccessible(true);
//        renderMethod.invoke(levelScreen, 0.1f);
//
//        // Verify navigation
//        verify(mockGame).setScreen(mockMainMenuScreen);
//        verify(mockGame).setScreen(any(level1.class));
//        verify(mockGame).setScreen(any(level2.class));
//        verify(mockGame).setScreen(any(level3.class));
//    }
//
//    @Test
//    public void testDispose() {
//        // Mock show to load textures
//        levelScreen.show();
//
//        // Call dispose
//        levelScreen.dispose();
//
//        // Verify textures are disposed
//        verify(mockTexture, atLeast(7)).dispose(); // At least 7 different textures
//    }
//}
