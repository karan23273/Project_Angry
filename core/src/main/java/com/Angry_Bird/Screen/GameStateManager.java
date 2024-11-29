//package com.Angry_Bird.Screen;
//
//import java.io.*;
//
//public class GameStateManager implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    // Save a GameState object to a file
//    public static void saveGameState(SaveState gameState, String filename) {
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
//            out.writeObject(gameState); // Serialize and save the GameState object
//            System.out.println("Game state saved successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Load a GameState object from a file
//    public static SaveState loadGameState(String filename) {
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
//            SaveState gameState = (SaveState) in.readObject(); // Deserialize and load the GameState object
//            System.out.println("Game state loaded successfully.");
//            return gameState;
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
package com.Angry_Bird.Screen;

import java.io.*;

public class GameStateManager implements Serializable {
    private static final long serialVersionUID = 1L;

    // Save a SaveState object to a file
    public static void saveGameState(SaveState gameState, String filename) {
        if (gameState == null) {
            System.err.println("GameState cannot be null. Save operation aborted.");
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(gameState); // Serialize and save the SaveState object
            System.out.println("Game state saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Load a SaveState object from a file
    public static SaveState loadGameState(String filename) {
        File file = new File(filename);

        if (!file.exists() || !file.isFile()) {
            System.err.println("File not found: " + filename);
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            SaveState gameState = (SaveState) in.readObject(); // Deserialize and load the SaveState object
            System.out.println("Game state loaded successfully from " + filename);
            return gameState;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game state: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
