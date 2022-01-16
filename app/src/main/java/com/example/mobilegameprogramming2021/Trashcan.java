package com.example.mobilegameprogramming2021;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Display;
import android.view.SurfaceView;
import java.util.Random;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class Trashcan implements EntityBase, Collidable,SensorEventListener{
    private boolean isDone = false;
    private int screenX, screenY;
    private float xPos, yPos,width,height, offset;
    private Sprite spriteplayer = null;   // New on Week 8
    private float screenRatioX,screenRatioY;
    float imgRadius1;
    private float[ ] values = {0,0,0};
    private long lastTime = System.currentTimeMillis();
    private SensorManager sensorManager;
    private Sensor sensor;



    //Random ranGen = new Random(); //wk 8=>Random Generator

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        //week 8 => create new sprite instance
        spriteplayer = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.player),1,3, 1 );

        imgRadius1 = spriteplayer.GetWidth() * 0.5f;
        xPos = _view.getWidth()/2;
        yPos = _view.getHeight() -160;

        //week 8=>randomise position
        //xPos = ranGen.nextFloat() * _view.getWidth();
        //yPos = ranGen.nextFloat() * _view.getHeight();

        sensorManager = (SensorManager) _view.getContext().getSystemService(Context.SENSOR_SERVICE);
        //sensor.registerListener(this, sensor.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void Update(float _dt) {

        if (GameSystem.Instance.GetIsPaused()) return;

        // wk8=> update sprite animation frame based on timing
        spriteplayer.Update(_dt);


        if (TouchManager.Instance.HasTouch())  // Touch and drag
        {
            // Check collision with the smurf sprite
            //Log.v("imgrad","s"+imgRadius1);
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius1)) {
                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();
            }
        }
    }


    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        spriteplayer.Render(_canvas, (int)xPos, (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return spriteplayer != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.PLAYER_LAYER;
    } //wk 8=>update player layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PLAYER;
    } //Week 8=>Update ent type

    public static Trashcan Create() {
        Trashcan result = new Trashcan(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PLAYER); //wk8=>update ent type
        return result;
    }

    public float getPosX()
    {
        return xPos;
    }

    @Override
    public String GetType() {
        return "Trashcan";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    public float GetPosY()
    {
        return yPos;
    }

    public float GetRadius()
    {
        return imgRadius1;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "Paperball")
        {

        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //values = SensorEvent.
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void SensorMove(){
        float tempx, tempy;

        //values[1] for x
        //values[0] for y



    }


}
