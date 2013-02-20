package edu.ian.andenginetest;

import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class MainActivity extends SimpleBaseGameActivity {

    static final int CAMERA_WIDTH = 800;
    static final int CAMERA_HEIGHT = 480;

    public Font mFont;
    public Camera mCamera;

    public static MainActivity instance;

    private ITextureRegion mBackgroundTextureRegion;
    private ITextureRegion mShipTextureRegion;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        // TODO Auto-generated method stub
        instance = this;
        mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
    }

    @Override
    protected void onCreateResources() {
        // mFont = FontFactory.create(this.getFontManager(),
        // this.getTextureManager(), 256, 256,
        // Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
        //
        // mFont.load();

        AssetManager am = AssetManager.getInstance();

        this.mBackgroundTextureRegion = am
                .getTextureRegion("gfx/background.png");
        this.mShipTextureRegion = am.getTextureRegion("gfx/ship.png");
    }

    @Override
    protected Scene onCreateScene() {

        mEngine.registerUpdateHandler(new FPSLogger());

        BaseScene s = new SplashScene();
        /*
         * Sprite backgroundSprite = new Sprite(0, 0,
         * this.mBackgroundTextureRegion, getVertexBufferObjectManager());
         * s.attachChild(backgroundSprite);
         * 
         * Sprite shipSprite = new Sprite(0, 0, this.mShipTextureRegion,
         * getVertexBufferObjectManager()); s.attachChild(shipSprite);
         */

        SceneManager.getInstance().setCurrentScene(s);

        return s;
    }

    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions) {
        return new FixedStepEngine(pEngineOptions, 60);
    }

}
