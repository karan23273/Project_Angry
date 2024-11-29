import com.Angry_Bird.Screen.LoginScreen;
import com.Angry_Bird.Screen.MainMenuScreen;
import com.Angry_Bird.launch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginScreenTest {
    private launch mockGame;
    private MainMenuScreen mockMainMenuScreen;
    private LoginScreen loginScreen;
    private HeadlessApplication application;

    // Concrete ApplicationListener for testing
    private static class TestApplicationListener implements ApplicationListener {
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
    }

    @Before
    public void setUp() {
        // Setup headless LibGDX application with concrete implementation
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new TestApplicationListener(), config);

        // Mock dependencies
        mockGame = mock(launch.class);
        mockMainMenuScreen = mock(MainMenuScreen.class);

        // Mock camera, viewport, and other required dependencies
        OrthographicCamera mockCamera = mock(OrthographicCamera.class);
        Viewport mockViewport = mock(Viewport.class);
        SpriteBatch mockBatch = mock(SpriteBatch.class);
        BitmapFont mockFont = mock(BitmapFont.class);
        com.badlogic.gdx.assets.AssetManager mockAssetManager = mock(com.badlogic.gdx.assets.AssetManager.class);

        // Configure mocks
        when(mockGame.getCamera()).thenReturn(mockCamera);
        when(mockGame.getViewport()).thenReturn(mockViewport);
        when(mockViewport.getWorldWidth()).thenReturn(800f);
        when(mockViewport.getWorldHeight()).thenReturn(600f);
        when(mockGame.getBatch()).thenReturn(mockBatch);
        when(mockGame.getFont()).thenReturn(mockFont);
        when(mockGame.getAssetManager()).thenReturn(mockAssetManager);

        // Simulate asset loading
        Texture mockTexture = mock(Texture.class);
        when(mockAssetManager.get(anyString(), eq(Texture.class))).thenReturn(mockTexture);

        // Create LoginScreen instance
        loginScreen = new LoginScreen(mockGame, mockMainMenuScreen);

        // Initialize buttons
        loginScreen.login_button = mock(com.Angry_Bird.Buttons.Click_Button.class);
        loginScreen.sign_in_button = mock(com.Angry_Bird.Buttons.Click_Button.class);

        // Ensure button states are properly mocked
        when(loginScreen.login_button.isOn()).thenReturn(false);
        when(loginScreen.sign_in_button.isOn()).thenReturn(false);
    }

    @After
    public void tearDown() {
        if (application != null) {
            application.exit();
        }
    }

    @Test
    public void testInitialLoginStatus() {
        // Initially, login status should be false
        assertFalse("Initial login status should be false", loginScreen.login_status());
    }

    @Test
    public void testUpdateStatus() {
        // Set login status to true first
        when(mockGame.found_id(anyString())).thenReturn(true);
        when(mockGame.found_password(anyString(), anyString())).thenReturn(true);

        // Simulate login
        loginScreen.usernameField.setText("testuser");
        loginScreen.passwordField.setText("testpassword");

        // Update status should reset login status
        loginScreen.updateStatus();
        assertFalse("Login status should be false after update", loginScreen.login_status());
    }

    @Test
    public void testGetUserId() {
        // Set username
        loginScreen.usernameField.setText("testuser");

        // Check if getUserid returns the correct username
        assertEquals("getUserid should return correct username",
            "testuser",
            loginScreen.getUserid());
    }

    @Test
    public void testLoginFailureUserNotFound() {
        // Mock game to simulate user not found
        when(mockGame.found_id(anyString())).thenReturn(false);

        // Set username and password
        loginScreen.usernameField.setText("nonexistentuser");
        loginScreen.passwordField.setText("anypassword");

        // Mock login button
        loginScreen.login_button = mock(com.Angry_Bird.Buttons.Click_Button.class);
        when(loginScreen.login_button.isOn()).thenReturn(true);

        // Simulate login process
        loginScreen.update(0.5f);

        // Verify login was not successful
        assertFalse("Login should fail for non-existent user", loginScreen.login_status());
    }

    @Test
    public void testLoginFailureWrongPassword() {
        // Mock game to simulate user exists but wrong password
        when(mockGame.found_id(anyString())).thenReturn(true);
        when(mockGame.found_password(anyString(), anyString())).thenReturn(false);

        // Set username and password
        loginScreen.usernameField.setText("existinguser");
        loginScreen.passwordField.setText("wrongpassword");

        // Mock login button
        loginScreen.login_button = mock(com.Angry_Bird.Buttons.Click_Button.class);
        when(loginScreen.login_button.isOn()).thenReturn(true);

        // Simulate login process
        loginScreen.update(0.5f);

        // Verify login was not successful
        assertFalse("Login should fail for wrong password", loginScreen.login_status());
    }

    @Test
    public void testSignUpEmptyDetails() {
        // Set empty username and password
        loginScreen.usernameField.setText("");
        loginScreen.passwordField.setText("");

        // Mock sign in button to be on
        loginScreen.sign_in_button = mock(com.Angry_Bird.Buttons.Click_Button.class);
        when(loginScreen.sign_in_button.isOn()).thenReturn(true);

        // Simulate sign up process
        loginScreen.update(0.5f);

        // Verify sign up was not successful
        assertFalse("Sign up should fail for empty details", loginScreen.login_status());
    }

    @Test
    public void testSignUpExistingUser() {
        // Mock game to simulate existing user
        when(mockGame.found_id(anyString())).thenReturn(true);

        // Set username and password
        loginScreen.usernameField.setText("existinguser");
        loginScreen.passwordField.setText("somepassword");

        // Mock sign in button to be on
        loginScreen.sign_in_button = mock(com.Angry_Bird.Buttons.Click_Button.class);
        when(loginScreen.sign_in_button.isOn()).thenReturn(true);

        // Simulate sign up process
        loginScreen.update(0.5f);

        // Verify sign up was not successful
        assertFalse("Sign up should fail for existing user", loginScreen.login_status());
    }
}
