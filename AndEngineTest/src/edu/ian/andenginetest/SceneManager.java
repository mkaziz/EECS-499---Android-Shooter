package edu.ian.andenginetest;

import org.andengine.entity.scene.Scene;

public class SceneManager {

    private static SceneManager instance;
    private MainActivity mainActivity;
    private BaseScene currentScene;

    public enum SceneType {
        SCENE_SPLASH, SCENE_MENU, SCENE_GAME, SCENE_LOADING
    }

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

    public void setCurrentScene(BaseScene currentScene) {
        this.currentScene = currentScene;
    }

    public SceneType getCurrentSceneType() {
        return this.currentScene.getSceneType();
    }

}
