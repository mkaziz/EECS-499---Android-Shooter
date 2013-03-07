package edu.ian.andenginetest;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import edu.ian.andenginetest.SceneManager.SceneType;

public class GameScene extends BaseScene implements SensorEventListener {

    private Sprite backgroundSprite;
    private Sprite shipSprite;
    private Body ship;

    private HUD gameHud;
    private PhysicsWorld physicsWorld;

    SensorManager sensorManager;
    Sensor accelerometer;

    Vector2 currentAcceleration = new Vector2();

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
        ITextureRegion shipTextureRegion = am.getTextureRegion("gfx/ship.png");
        shipSprite = new Sprite(0, MainActivity.CAMERA_HEIGHT
                - shipTextureRegion.getHeight(), shipTextureRegion,
                mainActivity.getVertexBufferObjectManager());

        ship = PhysicsFactory.createBoxBody(physicsWorld, shipSprite,
                BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 1));
        ship.setUserData(ship);
        ship.setFixedRotation(true);

        Line line_bottom = new Line(0, MainActivity.CAMERA_HEIGHT,
                MainActivity.CAMERA_WIDTH, MainActivity.CAMERA_HEIGHT,
                mainActivity.getVertexBufferObjectManager());
        Line line_left = new Line(0, 0, 0, MainActivity.CAMERA_HEIGHT,
                mainActivity.getVertexBufferObjectManager());
        Line line_right = new Line(MainActivity.CAMERA_WIDTH, 0,
                MainActivity.CAMERA_WIDTH, MainActivity.CAMERA_HEIGHT,
                mainActivity.getVertexBufferObjectManager());
        PhysicsFactory.createLineBody(physicsWorld, line_bottom,
                PhysicsFactory.createFixtureDef(0, 0, 0));
        PhysicsFactory.createLineBody(physicsWorld, line_left,
                PhysicsFactory.createFixtureDef(0, 0, 0));
        PhysicsFactory.createLineBody(physicsWorld, line_right,
                PhysicsFactory.createFixtureDef(0, 0, 0));

        // this.physicsWorld.registerPhysicsConnector(new
        // PhysicsConnector(line_bottom, wall_bottom, true, true));
        this.attachChild(line_bottom);

        physicsWorld.registerPhysicsConnector(new PhysicsConnector(shipSprite,
                ship, true, false) {

            @Override
            public void onUpdate(float pSecondsElapsed) {
                super.onUpdate(pSecondsElapsed);
                mainActivity.mCamera.onUpdate(0.1f);

                ship.setLinearDamping(10.0f);
                // ship.applyForce(currentAcceleration, new Vector2(0, 0));
                ship.setLinearVelocity(currentAcceleration);

            }
        });

        this.attachChild(shipSprite);
    }

    private void createPhysics() {
        physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, 0), false);
        registerUpdateHandler(physicsWorld);
    }

    @Override
    public void createScene() {

        createBackground();
        createPhysics();
        createShip();

    }

    @Override
    public void onBackKeyPressed() {
        this.disposeScene();
        SceneManager.getInstance().createMenuScene();
    }

    @Override
    public void disposeScene() {
        backgroundSprite.detachSelf();
        backgroundSprite.dispose();
        shipSprite.detachSelf();
        shipSprite.dispose();

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

            // Vector2 newAcceleration = currentAcceleration;
            //
            // if ((Math.abs((int) event.values[1] - (int)
            // currentAcceleration.x)) > 1)
            // newAcceleration.set((int) event.values[1],
            // (int) currentAcceleration.y);
            //
            // if ((Math.abs((int) event.values[0] - (int)
            // currentAcceleration.y)) > 1) {
            // newAcceleration.set(newAcceleration.x, (int) event.values[0]);
            // }
            currentAcceleration.set(event.values[1], event.values[0])
                    .mul(1.75f);
            //
            // if (newX > shipSprite.getWidth()
            // || newX < mainActivity.CAMERA_WIDTH - shipSprite.getWidth())
            // shipSprite.setX(newX);
            // if (newY > mainActivity.CAMERA_HEIGHT / 2
            // || newY < mainActivity.CAMERA_HEIGHT
            // - shipSprite.getHeight())
            // shipSprite.setY(newY);

        }

    }
}
