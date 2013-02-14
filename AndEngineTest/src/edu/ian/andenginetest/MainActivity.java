package edu.ian.andenginetest;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.BaseActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;

public class MainActivity extends SimpleBaseGameActivity {

    static final int CAMERA_WIDTH = 800;
    static final int CAMERA_HEIGHT = 480;

    public Font mFont;
    public Camera mCamera;

    public Scene mCurrentScene;
    public static BaseActivity instance;

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
    }

    @Override
    protected Scene onCreateScene() {
        // TODO Auto-generated method stub
        mEngine.registerUpdateHandler(new FPSLogger());
        mCurrentScene = new Scene();
        mCurrentScene.setBackground(new Background(0.09804f, 0.0274f, 0.8f));
        return mCurrentScene;
    }

    public void setCurrentScene(Scene scene) {
        mCurrentScene = scene;
        getEngine().setScene(mCurrentScene);
    }

}
