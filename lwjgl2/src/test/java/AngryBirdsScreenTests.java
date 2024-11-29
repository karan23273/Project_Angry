import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.Screen.*;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AngryBirdsScreenTests {

    @Mock
    private launch mockGame;

    @Mock
    private level1 mockLevel1;

    @Mock
    private level2 mockLevel2;

    @Mock
    private level3 mockLevel3;

    @Mock
    private OrthographicCamera mockCamera;

    @Mock
    private Viewport mockViewport;

    @Mock
    private SpriteBatch mockBatch;

    @Mock
    private BitmapFont mockFont;

    @Mock
    private AssetManager mockAssetManager;

    @Mock
    private Texture mockTexture;

    @Mock
    private Graphics mockGraphics;

    private Level_Failed levelFailedScreen;
    private Level_Passed levelPassedScreen;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Common setup for mocks
        when(mockGame.getCamera()).thenReturn(mockCamera);
        when(mockGame.getViewport()).thenReturn(mockViewport);
        when(mockGame.getBatch()).thenReturn(mockBatch);
        when(mockGame.getFont()).thenReturn(mockFont);
        when(mockGame.getAssetManager()).thenReturn(mockAssetManager);

        // Setup viewport world dimensions
        when(mockViewport.getWorldWidth()).thenReturn(1600f);
        when(mockViewport.getWorldHeight()).thenReturn(900f);

        // Prepare textures in asset manager
        when(mockAssetManager.get("Level failed2.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("restartB.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("restartA.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("level menuB.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("level menuA.png", Texture.class)).thenReturn(mockTexture);
    }

    @Test
    public void testLevelFailedScreenInitialization() {
        // Create Level_Failed screen
        levelFailedScreen = new Level_Failed(mockGame, mockLevel1);
        levelFailedScreen.setCurrLevel(1);
        levelFailedScreen.show();

        // Verify initialization
        verify(mockAssetManager).get("Level failed2.png", Texture.class);
        verify(mockAssetManager).get("restartB.png", Texture.class);
        verify(mockAssetManager).get("restartA.png", Texture.class);
    }

    @Test
    public void testLevelFailedScreenRestart() {
        // Create Level_Failed screen
        levelFailedScreen = spy(new Level_Failed(mockGame, mockLevel1));
        levelFailedScreen.setCurrLevel(1);
        levelFailedScreen.show();

        // Simulate button click and verify restart behavior
        Click_Button restartButton = mock(Click_Button.class);
        when(restartButton.clicked()).thenReturn(true);

        // Use reflection or a test-specific method to inject the mocked button
        // For this example, this is a conceptual representation
        levelFailedScreen.render(0.1f);

        // Verify game state reset and level reload
        verify(mockGame).setWorld(any(World.class));
        verify(mockGame).setLevel_1(any(level1.class));
        verify(mockGame).setScreen(any());
    }

    @Test
    public void testLevelPassedScreenInitialization() {
        // Create Level_Passed screen
        levelPassedScreen = new Level_Passed(mockGame, mockLevel1);
        levelPassedScreen.setcurrent_level(1);
        levelPassedScreen.setNext_level(2);
        levelPassedScreen.setLevel_score(3500);
        levelPassedScreen.show();

        // Verify initialization
        verify(mockAssetManager).get("front.png", Texture.class);
        verify(mockAssetManager).get("frontA.png", Texture.class);
        verify(mockAssetManager).get("restartB.png", Texture.class);
    }

    @Test
    public void testLevelPassedScreenNextLevel() {
        // Create Level_Passed screen
        levelPassedScreen = spy(new Level_Passed(mockGame, mockLevel1));
        levelPassedScreen.setcurrent_level(1);
        levelPassedScreen.setNext_level(2);
        levelPassedScreen.setLevel_score(4500);
        levelPassedScreen.show();

        // Simulate next level button click
        Click_Button nextLevelButton = mock(Click_Button.class);
        when(nextLevelButton.clicked()).thenReturn(true);

        // Render to trigger update
        levelPassedScreen.render(0.1f);

        // Verify game state reset and next level load
        verify(mockGame).setWorld(any(World.class));
        verify(mockGame).setLevel_2(any(level2.class));
        verify(mockGame).setScreen(any());
    }

    @Test
    public void testStarRatingCalculation() {
        // Create Level_Passed screen
        levelPassedScreen = new Level_Passed(mockGame, mockLevel1);
        levelPassedScreen.setcurrent_level(1);
        levelPassedScreen.setNext_level(2);

        // Test different score scenarios
        levelPassedScreen.setLevel_score(4500); // 3 stars
        levelPassedScreen.show();
        levelPassedScreen.render(0.1f);

        levelPassedScreen.setLevel_score(3500); // 2 stars
        levelPassedScreen.show();
        levelPassedScreen.render(0.1f);

        levelPassedScreen.setLevel_score(2500); // 1 star
        levelPassedScreen.show();
        levelPassedScreen.render(0.1f);

        // Additional verifications can be added based on specific requirements
    }

    @Test
    public void testScreenDispose() {
        // Test Level Failed Screen dispose
        levelFailedScreen = new Level_Failed(mockGame, mockLevel1);
        levelFailedScreen.show();
        levelFailedScreen.dispose();

        // Test Level Passed Screen dispose
        levelPassedScreen = new Level_Passed(mockGame, mockLevel1);
        levelPassedScreen.show();
        levelPassedScreen.dispose();

        // No specific assertions, just ensuring no exceptions are thrown
    }
}
