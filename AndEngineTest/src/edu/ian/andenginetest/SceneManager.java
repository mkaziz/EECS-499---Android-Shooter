package edu.ian.andenginetest;

import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

public class SceneManager {

    private static SceneManager instance;
    private MainActivity mainActivity;
    private BaseScene currentScene;

    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;

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
        mainActivity.getEngine().setScene(currentScene);
    }

    public SceneType getCurrentSceneType() {
        return this.currentScene.getSceneType();
    }

    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        splashScene = new SplashScene();
        setCurrentScene(splashScene);
        pOnCreateSceneCallback.onCreateSceneFinished(currentScene);
    }

    private void disposeSplashScene() {
        splashScene.disposeScene();
        splashScene = null;
    }

    public void createMenuScene() {
        menuScene = new MainMenuScene();
        setCurrentScene(menuScene);
        disposeSplashScene();

    }
}
