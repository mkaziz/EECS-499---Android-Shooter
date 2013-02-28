package edu.ian.andenginetest;

import org.andengine.entity.sprite.Sprite;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import edu.ian.andenginetest.SceneManager.SceneType;

public class GameScene extends BaseScene implements SensorEventListener {

    private Sprite backgroundSprite;
    private Sprite shipSprite;
    SensorManager sensorManager;
    Sensor accelerometer;

    public GameScene() {
        this.createScene();
        sensorManager = (SensorManager) mainActivity
                .getSystemService(mainActivity.SENSOR_SERVICE);
        accelerometer = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }

    private void createBackground() {
        AssetManager am = AssetManager.getInstance();

        backgroundSprite = new Sprite(0, 0,
                am.getTextureRegion("gfx/background.png"),
                mainActivity.getVertexBufferObjectManager());
        this.attachChild(backgroundSprite);
    }

    private void createShip() {
        AssetManager am = AssetManager.getInstance();

        shipSprite = new Sprite(0, 0, am.getTextureRegion("gfx/ship.png"),
                mainActivity.getVertexBufferObjectManager());
        this.attachChild(shipSprite);
    }

    @Override
    public void createScene() {

        createBackground();
        createShip();

    }

    @Override
    public void onBackKeyPressed() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disposeScene() {
        // TODO Auto-generated method stub

    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            shipSprite.setX(shipSprite.getX() + (int) event.values[1]);
            shipSprite.setY(shipSprite.getY() + (int) event.values[0]);

        }
    }

}
