package edu.ian.andenginetest;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import edu.ian.andenginetest.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements
        IOnMenuItemClickListener {

    private Sprite backgroundSprite;
    private Sprite gameLogoSprite;
    private MenuScene menuChildScene;

    private final int MENU_PLAY = 0;
    private final int MENU_OPTIONS = 1;

    public MainMenuScene() {

        this.createScene();
    }

    private void createBackground() {
        AssetManager am = AssetManager.getInstance();

        backgroundSprite = new Sprite(0, 0,
                am.getTextureRegion("gfx/background.png"),
                mainActivity.getVertexBufferObjectManager());
        this.attachChild(backgroundSprite);
    }

    private void createGameLogo() {
        AssetManager am = AssetManager.getInstance();

        ITextureRegion gameLogoTr = am.getTextureRegion("gfx/gamelogo.png");

        gameLogoSprite = new Sprite(MainActivity.CAMERA_WIDTH / 2
                - gameLogoTr.getWidth() / 2,
                (MainActivity.CAMERA_HEIGHT / 3 - gameLogoTr.getHeight()),
                gameLogoTr, mainActivity.getVertexBufferObjectManager());
        this.attachChild(gameLogoSprite);
    }

    private void createMenuChildScene() {
        menuChildScene = new MenuScene();
        menuChildScene.setCamera(mainActivity.mCamera);
        menuChildScene.setPosition(MainActivity.CAMERA_WIDTH / 4,
                MainActivity.CAMERA_HEIGHT / 2);

        AssetManager am = AssetManager.getInstance();

        ITextureRegion playButtonTr = am.getTextureRegion("gfx/play.png");
        ITextureRegion optionsButtonTr = am.getTextureRegion("gfx/options.png");

        IMenuItem playItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
                MENU_PLAY, playButtonTr,
                mainActivity.getVertexBufferObjectManager()), 1.2f, 1);
        IMenuItem optionsItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
                MENU_OPTIONS, optionsButtonTr,
                mainActivity.getVertexBufferObjectManager()), 1.2f, 1);

        menuChildScene.addMenuItem(playItem);
        menuChildScene.addMenuItem(optionsItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        playItem.setPosition(0, 0);
        optionsItem.setPosition(0, playItem.getY() + playItem.getHeight() + 10);

        menuChildScene.setOnMenuItemClickListener(this);

        this.setChildScene(menuChildScene);

    }

    @Override
    public void createScene() {
        createBackground();
        createGameLogo();
        createMenuChildScene();

    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);

    }

    @Override
    public void disposeScene() {
        backgroundSprite.detachSelf();
        backgroundSprite.dispose();
        gameLogoSprite.detachSelf();
        gameLogoSprite.dispose();
        menuChildScene.detachSelf();
        menuChildScene.dispose();

    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_MENU;
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
            float pMenuItemLocalX, float pMenuItemLocalY) {
        // TODO Auto-generated method stub

        switch (pMenuItem.getID()) {
        case MENU_PLAY:
            SceneManager.getInstance().createGameScene();
            return true;
        case MENU_OPTIONS:
            return true;
        default:
            return false;
        }

    }
}
