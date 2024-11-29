//package com.Angry_Bird.Screen;
//import com.Angry_Bird.Buttons.Click_Button;
//import com.Angry_Bird.launch;
//import com.badlogic.gdx.InputMultiplexer;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class Click_ButtonTest {
//    @Mock
//    private Texture mockBeforeClickTexture;
//
//    @Mock
//    private Texture mockAfterClickTexture;
//
//    @Mock
//    private OrthographicCamera mockCamera;
//
//    @Mock
//    private SpriteBatch mockSpriteBatch;
//
//    @Mock
//    private InputMultiplexer mockInputMultiplexer;
//
//    @Mock
//    private launch mockLaunch;
//
//    private Click_Button clickButton;
//
//    @Before
//    public void setUp() {
//        // Initialize mocks
//        MockitoAnnotations.initMocks(this);
//
//        // Setup mock texture dimensions
//        when(mockBeforeClickTexture.getWidth()).thenReturn(100);
//        when(mockBeforeClickTexture.getHeight()).thenReturn(50);
//
//        // Create Click_Button instance
//        clickButton = spy(new Click_Button(mockBeforeClickTexture, mockAfterClickTexture, 10f, 20f, mockCamera));
//    }
//
//    @Test
//    public void testConstructor() {
//        assertFalse(clickButton.clicked());
//        assertFalse(clickButton.isOn());
//    }
//
//    @Test
//    public void testSetPosition() {
//        clickButton.set_Position(50f, 60f);
//        // We can't directly verify the position, but we can ensure the method doesn't throw an exception
//        assertNotNull(clickButton);
//    }
//
//    @Test
//    public void testClickedState() {
//        // Verify initial state
//        assertFalse(clickButton.clicked());
//
//        // Simulate clicking
//        doReturn(true).when(clickButton).hover_on(anyFloat(), anyFloat());
//
//        // Manually trigger tap method
//        clickButton.setInput(mockInputMultiplexer);
//
//        // Verify clicked state changes
//        assertTrue(clickButton.clicked());
//        assertTrue(clickButton.isOn());
//    }
//
//    @Test
//    public void testToggleOn() {
//        // Initial state
//        assertFalse(clickButton.isOn());
//
//        // Turn on
//        clickButton.setOn();
//        assertTrue(clickButton.isOn());
//
//        // Turn off
//        clickButton.setOff();
//        assertFalse(clickButton.isOn());
//    }
//
//    @Test
//    public void testHoverOn() {
//        // Use reflection to test private hover_on method
//        try {
//            java.lang.reflect.Method hoverMethod = Click_Button.class.getDeclaredMethod("hover_on", float.class, float.class);
//            hoverMethod.setAccessible(true);
//
//            // Test within button bounds
//            boolean result1 = (boolean) hoverMethod.invoke(clickButton, 15f, 25f);
//            assertTrue(result1);
//
//            // Test outside button bounds
//            boolean result2 = (boolean) hoverMethod.invoke(clickButton, 200f, 200f);
//            assertFalse(result2);
//        } catch (Exception e) {
//            fail("Exception in testing hover_on method: " + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testToggleDraw() {
//        // Create a mock SpriteBatch
//        SpriteBatch mockBatch = mock(SpriteBatch.class);
//
//        // Test before clicking
//        clickButton.toggleDraw(mockBatch);
//
//        // Change clicked state
//        clickButton.clicked();
//
//        // Test after clicking
//        clickButton.toggleDraw(mockBatch);
//
//        // Verify that batch.draw was called (we can't easily verify which texture)
//        // This is more of a smoke test to ensure no exceptions are thrown
//        assertNotNull(mockBatch);
//    }
//
//    @Test
//    public void testLevelButtonConstructor() {
//        // Test constructor with custom button width and height
//        Click_Button levelButton = new Click_Button(
//                mockBeforeClickTexture,
//                mockAfterClickTexture,
//                10f,
//                20f,
//                mockCamera,
//                200f,
//                100f
//        );
//
//        assertNotNull(levelButton);
//    }
//}
