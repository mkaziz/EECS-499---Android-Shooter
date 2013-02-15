package edu.ian.andenginetest;

import org.andengine.entity.scene.Scene;

public class SceneManager {

    private static SceneManager instance;
    private MainActivity mainActivity;
    private Scene currentScene;

    private SceneManager() {
        mainActivity = MainActivity.getInstance();
    }

    public static SceneManager getInstance() {
        if (instance == null)
            instance = new SceneManager();

        return instance;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

}
