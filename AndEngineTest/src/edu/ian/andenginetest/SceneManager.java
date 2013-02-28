package edu.ian.andenginetest;

import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

public class SceneManager {

    private static SceneManager instance;
    private MainActivity mainActivity;
    private BaseScene currentScene;

    protected BaseScene splashScene;
    protected BaseScene menuScene;
    protected BaseScene gameScene;

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

    public BaseScene getCurrentScene() {
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

    public void disposeMenuScene() {
        menuScene.disposeScene();
        menuScene = null;

    }

    public void createGameScene() {
        gameScene = new GameScene();
        setCurrentScene(gameScene);
        disposeMenuScene();
    }
}
