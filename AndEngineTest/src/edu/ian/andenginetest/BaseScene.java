package edu.ian.andenginetest;

import org.andengine.entity.scene.Scene;

import edu.ian.andenginetest.SceneManager.SceneType;

public abstract class BaseScene extends Scene {

    MainActivity mainActivity;

    public BaseScene() {
        this.mainActivity = MainActivity.getInstance();
    }

    public abstract void createScene();

    public abstract void onBackKeyPressed();

    public abstract void disposeScene();

    public abstract SceneType getSceneType();
}
