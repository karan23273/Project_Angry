import com.Angry_Bird.Screen.*;
import com.Angry_Bird.launch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LevelScreenTest {
    @Mock
    private launch mockGame;

    @Mock
    private MainMenuScreen mockMainMenuScreen;

    @Mock
    private OrthographicCamera mockCamera;

    @Mock
    private Viewport mockViewport;

    @Mock
    private SpriteBatch mockBatch;

    @Mock
    private AssetManager mockAssetManager;

    @Mock
    private BitmapFont mockFont;

    @Mock
    private Texture mockTexture;

    private Level_Screen levelScreen;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Setup mock game behavior
        when(mockGame.getCamera()).thenReturn(mockCamera);
        when(mockGame.getViewport()).thenReturn(mockViewport);
        when(mockGame.getBatch()).thenReturn(mockBatch);
        when(mockGame.getAssetManager()).thenReturn(mockAssetManager);
        when(mockGame.getFont()).thenReturn(mockFont);

        // Setup mock viewport behavior
        when(mockViewport.getWorldWidth()).thenReturn(1920f);
        when(mockViewport.getWorldHeight()).thenReturn(1080f);

        // Setup asset manager texture mocking
        when(mockAssetManager.get("level.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("back.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("backA.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("levelFrame.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("lock.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("levelFrameB.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("levelFrameA.png", Texture.class)).thenReturn(mockTexture);
        when(mockAssetManager.get("star.png", Texture.class)).thenReturn(mockTexture);

        // Create the Level_Screen with mocks
        levelScreen = spy(new Level_Screen(mockGame, mockMainMenuScreen));
    }

    @Test
    public void testShowMethod() {
        // Call the show method
        levelScreen.show();

        // Verify asset loading
        verify(mockAssetManager).get("level.png", Texture.class);
        verify(mockAssetManager).get("back.png", Texture.class);
        verify(mockAssetManager).get("backA.png", Texture.class);

        // Add more specific verifications as needed
    }

    @Test
    public void testLevelUnlockConditions() {
        // Mock login screen and user interactions
        when(mockGame.getLoginScreen()).thenReturn((LoginScreen) mock(Object.class));
        when(mockGame.getLevel2_score(any())).thenReturn(1500f); // Enough to unlock level 2
        when(mockGame.getLevel3_score(any())).thenReturn(2000f); // Enough to unlock level 3

        // Test level 2 unlock
        levelScreen.show();
        levelScreen.update(0.1f); // Simulate an update
    }

    @Test
    public void testDisposeMethod() {
        levelScreen.show(); // Ensure textures are loaded first
        levelScreen.dispose();

        // You might want to add specific verifications here depending on the exact dispose logic
    }

    @Test
    public void testResizeMethod() {
        levelScreen.show(); // Ensure buttons are initialized
        levelScreen.resize(800, 600);

        // Verify camera update
        verify(mockCamera).position.set(anyFloat(), anyFloat(), anyFloat());
        verify(mockCamera).update();
    }

    // This is a private method, so we're testing its behavior indirectly
    @Test
    public void testStarDrawingConditions() {
        // Setup mock game to return different scores
        when(mockGame.getLoginScreen()).thenReturn((LoginScreen) mock(Object.class));

        // Test different score scenarios for star drawing
        when(mockGame.getLevel1_score(any())).thenReturn(4500f); // 3 stars
        when(mockGame.getLevel2_score(any())).thenReturn(3500f); // 2 stars
        when(mockGame.getLevel3_score(any())).thenReturn(2500f); // 1 star

        levelScreen.show();
        // You might need to modify this to properly test drawStar method
    }
}
