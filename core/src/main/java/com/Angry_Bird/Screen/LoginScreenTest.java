//package com.Angry_Bird.Screen;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import com.Angry_Bird.Buttons.Click_Button;
//import com.Angry_Bird.launch;
//import com.badlogic.gdx.scenes.scene2d.ui.TextField;
//import org.junit.Before;
//import org.junit.Test;
//
//public class LoginScreenTest {
//
//    private launch mockGame;
//    private MainMenuScreen mockMainMenuScreen;
//    private LoginScreen loginScreen;
//
//    @Before
//    public void setUp() {
//        // Create mock objects
//        mockGame = mock(launch.class);
//        mockMainMenuScreen = mock(MainMenuScreen.class);
//
//        // Setup mock behaviors for game authentication methods
//        when(mockGame.found_id("validUser")).thenReturn(true);
//        when(mockGame.found_id("invalidUser")).thenReturn(false);
//        when(mockGame.found_password("validUser", "correctPassword")).thenReturn(true);
//        when(mockGame.found_password("validUser", "wrongPassword")).thenReturn(false);
//
//        // Create the LoginScreen with mocked dependencies
//        loginScreen = spy(new LoginScreen(mockGame, mockMainMenuScreen));
//    }
//
//    @Test
//    public void testInvalidUsername() {
//        // Set invalid username
//        loginScreen.usernameField.setText("invalidUser");
//        loginScreen.passwordField.setText("anyPassword");
//
//        // Simulate update
//        loginScreen.update(0.5f);
//
//        // Assert login status is false
//        assertFalse(loginScreen.login_status());
//    }
//
//    @Test
//    public void testInvalidPassword() {
//        // Set valid username with wrong password
//        loginScreen.usernameField.setText("validUser");
//        loginScreen.passwordField.setText("wrongPassword");
//
//        // Simulate update
//        loginScreen.update(0.5f);
//
//        // Assert login status is false
//        assertFalse(loginScreen.login_status());
//    }
//
//    @Test
//    public void testValidCredentials() {
//        // Set valid username and password
//        loginScreen.usernameField.setText("validUser");
//        loginScreen.passwordField.setText("correctPassword");
//
//        // Simulate update
//        loginScreen.update(0.5f);
//
//        // Assert login status is true
//        assertTrue(loginScreen.login_status());
//    }
//
//    @Test
//    public void testEmptyUsername() {
//        // Set empty username
//        loginScreen.usernameField.setText("");
//        loginScreen.passwordField.setText("anyPassword");
//
//        // Simulate update
//        loginScreen.update(0.5f);
//
//        // Assert login status is false
//        assertFalse(loginScreen.login_status());
//    }
//
//    @Test
//    public void testEmptyPassword() {
//        // Set username with empty password
//        loginScreen.usernameField.setText("validUser");
//        loginScreen.passwordField.setText("");
//
//        // Simulate update
//        loginScreen.update(0.5f);
//
//        // Assert login status is false
//        assertFalse(loginScreen.login_status());
//    }
//
//    @Test
//    public void testSignUp() {
//        // Set a new username that doesn't exist
//        loginScreen.usernameField.setText("newUser");
//        loginScreen.passwordField.setText("newPassword");
//
//        // Mock the found_id to return false for the new user
//        when(mockGame.found_id("newUser")).thenReturn(false);
//
//        // Simulate sign-in button activation
//        loginScreen.sign_in_button = mock(Click_Button.class);
//        when(loginScreen.sign_in_button.isOn()).thenReturn(true);
//
//        // Simulate update
//        loginScreen.update(0.5f);
//
//        // Verify sign up method is called
//        verify(mockGame).signUp("newUser", "newPassword");
//
//        // Assert sign-in success
//        assertTrue(loginScreen.login_status());
//    }
//
//    @Test
//    public void testDuplicateUsername() {
//        // Set an existing username
//        loginScreen.usernameField.setText("validUser");
//        loginScreen.passwordField.setText("anyPassword");
//
//        // Simulate sign-in button activation
//        loginScreen.sign_in_button = mock(Click_Button.class);
//        when(loginScreen.sign_in_button.isOn()).thenReturn(true);
//
//        // Simulate update
//        loginScreen.update(0.5f);
//
//        // Assert sign-in fails for duplicate username
//        assertFalse(loginScreen.login_status());
//    }
//
//    @Test
//    public void testGetUserId() {
//        // Set a username
//        loginScreen.usernameField.setText("testUser");
//
//        // Verify getUserId returns the correct username
//        assertEquals("testUser", loginScreen.getUserid());
//    }
//
//    @Test
//    public void testUpdateStatus() {
//        // Set login to true
//        loginScreen.usernameField.setText("validUser");
//        loginScreen.passwordField.setText("correctPassword");
//        loginScreen.update(0.5f);
//
//        assertTrue(loginScreen.login_status());
//
//        // Reset status
//        loginScreen.updateStatus();
//
//        // Verify status is reset
//        assertFalse(loginScreen.login_status());
//    }
//}
