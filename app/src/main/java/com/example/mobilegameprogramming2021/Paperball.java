package com.example.mobilegameprogramming2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import java.util.Random;

public class Paperball implements EntityBase, Collidable{
    private boolean isDone = false;
    private Bitmap bmp = null, scaledbmp = null;
    int ScreenWidth, ScreenHeight, ScreenHeight2;
    public float yStart = 0,xPos = 0, yPos = 0, offset;
    private SurfaceView view = null;
    DisplayMetrics metrics;
    Random ranGen;
    float imgradiusball;
    public float speed;






    //check if anything to do with entity (use for pause)
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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.paperobj); //<==use Res Manager
        //Find the surfaceview size or screensize


        imgradiusball = bmp.getWidth() * 0.5f;

        // Randomize a location to spawn, Y is fixed, x is random
        Random ranGen = new Random();
        yStart = yPos = 0;
        //ScreenWidth = _view.getWidth();
        //ScreenHeight = _view.getHeight();

        metrics = _view.getResources().getDisplayMetrics();
        ScreenHeight = metrics.heightPixels / 5;
        ScreenWidth = metrics.widthPixels / 5;
        ScreenHeight2 = _view.getHeight();

        xPos = ranGen.nextInt(ScreenWidth) % ScreenWidth;
        // Set a speed to cross the screen
        speed = _view.getWidth() * 0.2f;
        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight, true);





    }

    @Override
    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused()) return;


        // Check if out of the screen
        if (yPos > ScreenHeight2)
        {
            yPos = 0;
        }
        else
        {
            // Update the paper position using speed
            yPos += speed * _dt;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(scaledbmp, xPos, yPos, null); //1st image
    }

    @Override
    public boolean IsInit() {
        return bmp != null;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PAPERBALL;
    }

    public static Paperball Create() {
        Paperball result = new Paperball();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAPERBALL);
        return result;
    }


    @Override
    public String GetType() {
        return "Paperball";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return imgradiusball;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "Trashcan")
        {
            yPos = 0;
        }
    }
}


