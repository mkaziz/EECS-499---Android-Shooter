package edu.ian.andenginetest;

import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.modifier.ease.EaseBackInOut;

import edu.ian.andenginetest.SceneManager.SceneType;

public class SplashScene extends BaseScene {

    private Sprite backgroundSprite;
    private Sprite gameLogoSprite;
    private Sprite namesSprite;

    public SplashScene() {
        this.createScene();
    }

    private void createBackground() {
        AssetManager am = AssetManager.getInstance();

        backgroundSprite = new Sprite(0, 0,
                am.getTextureRegion("gfx/splash.png"),
                mainActivity.getVertexBufferObjectManager());
        this.attachChild(backgroundSprite);
    }

    private void createChildren() {
        AssetManager am = AssetManager.getInstance();

        ITextureRegion gameLogoTr = am.getTextureRegion("gfx/gamelogo.png");

        gameLogoSprite = new Sprite(-gameLogoTr.getWidth(),
                (mainActivity.CAMERA_HEIGHT / 2 - gameLogoTr.getHeight() / 2),
                gameLogoTr, mainActivity.getVertexBufferObjectManager());
        this.attachChild(gameLogoSprite);

        ITextureRegion namesTr = am.getTextureRegion("gfx/names.png");

        namesSprite = new Sprite(mainActivity.CAMERA_WIDTH,
                (mainActivity.CAMERA_HEIGHT / 2 + namesTr.getHeight() / 2),
                namesTr, mainActivity.getVertexBufferObjectManager());
        this.attachChild(namesSprite);

        gameLogoSprite.registerEntityModifier(new MoveXModifier(2,
                gameLogoSprite.getX(), mainActivity.CAMERA_WIDTH / 2
                        - gameLogoSprite.getWidth() / 2, EaseBackInOut
                        .getInstance()));

        namesSprite.registerEntityModifier(new MoveXModifier(2, namesSprite
                .getX(), mainActivity.CAMERA_WIDTH / 2 - namesSprite.getWidth()
                / 2, EaseBackInOut.getInstance()));

    }

    @Override
    public void createScene() {

        createBackground();
        createChildren();

    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public void disposeScene() {
        backgroundSprite.detachSelf();
        backgroundSprite.dispose();
        namesSprite.detachSelf();
        namesSprite.dispose();
        gameLogoSprite.detachSelf();
        gameLogoSprite.dispose();
        this.detachSelf();
        this.dispose();

    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_SPLASH;
    }

}
