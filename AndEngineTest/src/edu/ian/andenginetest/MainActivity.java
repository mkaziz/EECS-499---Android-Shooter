package edu.ian.andenginetest;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.BaseActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.graphics.Typeface;

public class MainActivity extends SimpleBaseGameActivity {

    static final int CAMERA_WIDTH = 800;
    static final int CAMERA_HEIGHT = 480;

    public Font mFont;
    public Camera mCamera;

    public Scene mCurrentScene;
    public static BaseActivity instance;

    private ITextureRegion mBackgroundTextureRegion;
    private ITextureRegion mShipTextureRegion;

    public static BaseActivity getSharedInstance() {
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
        mFont = FontFactory.create(this.getFontManager(),
                this.getTextureManager(), 256, 256,
                Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);

        mFont.load();

        // try {
        // ITexture backgroundTexture = new BitmapTexture(
        // this.getTextureManager(), new IInputStreamOpener() {
        // @Override
        // public InputStream open() throws IOException {
        // return getAssets().open("gfx/background.png");
        // }
        // });
        // backgroundTexture.load();
        // this.mBackgroundTextureRegion = TextureRegionFactory
        // .extractFromTexture(backgroundTexture);
        // } catch (IOException e) {
        // Debug.e(e);
        // }

        this.mBackgroundTextureRegion = loadTexture("gfx/background.png");
        this.mShipTextureRegion = loadTexture("gfx/ship.png");
    }

    private ITextureRegion loadTexture(final String filename) {
        try {
            ITexture backgroundTexture = new BitmapTexture(
                    this.getTextureManager(), new IInputStreamOpener() {
                        @Override
                        public InputStream open() throws IOException {
                            return getAssets().open(filename);
                        }
                    });
            backgroundTexture.load();
            return TextureRegionFactory.extractFromTexture(backgroundTexture);
        } catch (IOException e) {
            Debug.e(e);
        }
        return null;
    }

    @Override
    protected Scene onCreateScene() {
        // TODO Auto-generated method stub
        mEngine.registerUpdateHandler(new FPSLogger());
        mCurrentScene = new Scene();
        Sprite backgroundSprite = new Sprite(0, 0,
                this.mBackgroundTextureRegion, getVertexBufferObjectManager());
        mCurrentScene.attachChild(backgroundSprite);

        Sprite shipSprite = new Sprite(0, 0, this.mShipTextureRegion,
                getVertexBufferObjectManager());
        mCurrentScene.attachChild(shipSprite);

        return mCurrentScene;
    }

    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions) {
        return new FixedStepEngine(pEngineOptions, 60);
    }

    public void setCurrentScene(Scene scene) {
        mCurrentScene = scene;
        getEngine().setScene(mCurrentScene);
    }

}
