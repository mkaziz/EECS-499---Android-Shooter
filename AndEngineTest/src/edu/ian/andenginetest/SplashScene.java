package edu.ian.andenginetest;

import org.andengine.entity.sprite.Sprite;

import edu.ian.andenginetest.SceneManager.SceneType;

public class SplashScene extends BaseScene {

    private Sprite splashSprite;

    public SplashScene() {
        this.createScene();
    }

    @Override
    public void createScene() {
        AssetManager am = AssetManager.getInstance();
        splashSprite = new Sprite(0, 0, am.getTextureRegion("gfx/splash.png"),
                mainActivity.getVertexBufferObjectManager());
        this.attachChild(splashSprite);
    }

    @Override
    public void onBackKeyPressed() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disposeScene() {
        splashSprite.detachSelf();
        splashSprite.dispose();
        this.detachSelf();
        this.dispose();
        AssetManager am = AssetManager.getInstance();
        am.unloadTexture("splash.png");
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_SPLASH;
    }

}
