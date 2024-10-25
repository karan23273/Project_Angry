package com.Angry_Bird.lwjgl2;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Angry_Bird.launch;

/** Launches the desktop (LWJGL2) application. */
public class Lwjgl2Launcher {
    public static void main(String[] args) {
        createApplication();
    }

    private static LwjglApplication createApplication() {
        return new LwjglApplication(new launch(), getDefaultConfiguration());
    }

    private static LwjglApplicationConfiguration getDefaultConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "Angry Bird ";
        configuration.width = 1920;
        configuration.height = 1080;
        //// This prevents a confusing error that would appear after exiting normally.
        configuration.forceExit = false;

//        configuration.addIcon("final.png", FileType.Internal);
//        configuration.addIcon("loadbg25.png", FileType.Internal);
//        configuration.addIcon("loadfg25.png", FileType.Internal);
//        for (int size : new int[] { 128, 64, 32, 16 }) {
//
//        }
        return configuration;
    }
}
