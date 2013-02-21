package edu.ian.andenginetest;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.ui.activity.BaseGameActivity;

public class MainActivity extends BaseGameActivity {

    static final int CAMERA_WIDTH = 800;
    static final int CAMERA_HEIGHT = 480;

    public Font mFont;
    public Camera mCamera;

    public static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        instance = this;
        mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions eo = new EngineOptions(true,
                ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(
                        CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
        // eo.setEngineLock(EngineLock.this)
        return eo;
    }

    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions) {

        return new LimitedFPSEngine(pEngineOptions, 60);
    }

    @Override
    public void onCreateResources(
            OnCreateResourcesCallback pOnCreateResourcesCallback)
            throws Exception {

        pOnCreateResourcesCallback.onCreateResourcesFinished();

    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
            throws Exception {
        mEngine.registerUpdateHandler(new FPSLogger());
        SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);

        pOnCreateSceneCallback.onCreateSceneFinished(SceneManager.getInstance()
                .getCurrentScene());

    }

    @Override
    public void onPopulateScene(Scene pScene,
            OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
        mEngine.registerUpdateHandler(new TimerHandler(3f,
                new ITimerCallback() {
                    @Override
                    public void onTimePassed(final TimerHandler pTimerHandler) {

                        SceneManager.getInstance().createMenuScene();
                        // Log.e("test", "test9");
                        mEngine.unregisterUpdateHandler(pTimerHandler);
                        // pTimerHandler.reset();
                        // Log.e("test", "test");
                    }
                }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

}
