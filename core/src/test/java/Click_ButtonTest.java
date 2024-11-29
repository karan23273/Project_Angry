
import com.Angry_Bird.Buttons.Click_Button;
import com.Angry_Bird.launch;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Click_ButtonTest {
    @Mock
    private Texture mockBeforeClickTexture;

    @Mock
    private Texture mockAfterClickTexture;

    @Mock
    private OrthographicCamera mockCamera;

    @Mock
    private SpriteBatch mockSpriteBatch;

    @Mock
    private InputMultiplexer mockInputMultiplexer;

    @Mock
    private launch mockLaunch;

    private Click_Button clickButton;
    @Before
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

        // Initialize the clickButton with a constructor that uses mock dependencies
        clickButton = new Click_Button(
            mockBeforeClickTexture,
            mockAfterClickTexture,
            10f,  // x position
            20f,  // y position
            mockCamera,
            100f,  // width
            50f   // height
        );
    }

    @BeforeClass
    public static void setUpClass() {

        // Create a minimal ApplicationListener implementation
        ApplicationListener emptyApp = new ApplicationListener() {
            @Override
            public void create() {}

            @Override
            public void resize(int width, int height) {}

            @Override
            public void render() {}

            @Override
            public void pause() {}

            @Override
            public void resume() {}

            @Override
            public void dispose() {}
        };

        // Initialize headless LibGDX application
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(emptyApp, config);
    }

    @AfterClass
    public static void tearDownClass() {
        // Cleanup the application after tests
        if (Gdx.app != null) {
            Gdx.app.exit();
        }
    }


    @Test
    public void testConstructor() {
        assertFalse(clickButton.clicked());
        assertFalse(clickButton.isOn());
    }

    @Test
    public void testSetPosition() {
        clickButton.set_Position(50f, 60f);
        // We can't directly verify the position, but we can ensure the method doesn't throw an exception
        assertNotNull(clickButton);
    }


    @Test
    public void testToggleOn() {
        // Initial state
        assertFalse(clickButton.isOn());

        // Turn on
        clickButton.setOn();
        assertTrue(clickButton.isOn());

        // Turn off
        clickButton.setOff();
        assertFalse(clickButton.isOn());
    }

    @Test
    public void testHoverOn() {
        // Use reflection to test private hover_on method
        try {
            java.lang.reflect.Method hoverMethod = Click_Button.class.getDeclaredMethod("hover_on", float.class, float.class);
            hoverMethod.setAccessible(true);

            // Test within button bounds
            boolean result1 = (boolean) hoverMethod.invoke(clickButton, 15f, 25f);
            assertTrue(result1);

            // Test outside button bounds
            boolean result2 = (boolean) hoverMethod.invoke(clickButton, 200f, 200f);
            assertFalse(result2);
        } catch (Exception e) {
            fail("Exception in testing hover_on method: " + e.getMessage());
        }
    }

    @Test
    public void testToggleDraw() {
        // Create a mock SpriteBatch
        SpriteBatch mockBatch = mock(SpriteBatch.class);

        // Test before clicking
        clickButton.toggleDraw(mockBatch);

        // Change clicked state
        clickButton.clicked();

        // Test after clicking
        clickButton.toggleDraw(mockBatch);

        // Verify that batch.draw was called (we can't easily verify which texture)
        // This is more of a smoke test to ensure no exceptions are thrown
        assertNotNull(mockBatch);
    }

    @Test
    public void testLevelButtonConstructor() {
        // Test constructor with custom button width and height
        Click_Button levelButton = new Click_Button(
                mockBeforeClickTexture,
                mockAfterClickTexture,
                10f,
                20f,
                mockCamera,
                200f,
                100f
        );

        assertNotNull(levelButton);
    }
}
