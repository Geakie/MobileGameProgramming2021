package com.example.mobilegameprogramming2021;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceView;
import java.util.Random;

public class PaperEntity implements EntityBase{
    private boolean isDone = false;
    int ScreenWidth, ScreenHeight;
    private float yStart = 0,xPos = 0,yPos = 0, offset;
    private float speed;
    Random ranGen;
    private SurfaceView view = null;
    DisplayMetrics metrics;
    private Sprite spritepaper = null;   // New on Week 8
    private float screenRatioX,screenRatioY;



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
        spritepaper = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.paper),1,3, 1 );

        // Randomize a location to spawn, Y is fixed, x is random
        Random ranGen = new Random();
        yStart = yPos = 500;
        ScreenWidth = _view.getWidth();
        //xPos = 540;
        xPos = ranGen.nextInt() % ScreenWidth;

      // Set a speed to cross the screen
        speed = _view.getWidth() * 0.2f;

        //week 8=>randomise position
        //xPos = ranGen.nextFloat() * _view.getWidth();
        //yPos = ranGen.nextFloat() * _view.getHeight();
    }

    @Override
    public void Update(float _dt) {

        if (GameSystem.Instance.GetIsPaused()) return;

        // wk8=> update sprite animation frame based on timing
        spritepaper.Update(_dt);

        yPos += speed * _dt;
        // Check if out of the screen
        // if (yPos >= 1920)
       //{
            // Move it to another Location
        //    yPos = yStart;

        //}

        // Check Collision with Player
        //if (Collision.SphereToSphere(xPos,yPos, spritepaper.GetWidth() * 0.5f,GameSystem.Instance.))

    }


    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        spritepaper.Render(_canvas,(int)xPos,  (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return spritepaper != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.GAMEOBJECTS_LAYER;
    } //wk 8=>update player layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PAPER;
    } //Week 8=>Update ent type

    public static PaperEntity Create() {
        PaperEntity result = new PaperEntity(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAPER); //wk8=>update ent tyep
        return result;
    }

}
