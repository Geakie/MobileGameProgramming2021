package com.example.mobilegameprogramming2021;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Display;
import android.view.SurfaceView;
import java.util.Random;

public class Trashcan implements EntityBase{
    private boolean isDone = false;
    private int screenX, screenY;
    private float xPos, yPos,width,height, offset;
    private Sprite spriteplayer = null;   // New on Week 8
    private float screenRatioX,screenRatioY;

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


        xPos = 1 * _view.getWidth()/2;
        yPos = 1 * _view.getHeight() -160;

        //week 8=>randomise position
        //xPos = ranGen.nextFloat() * _view.getWidth();
        //yPos = ranGen.nextFloat() * _view.getHeight();
    }

    @Override
    public void Update(float _dt) {

        if (GameSystem.Instance.GetIsPaused()) return;

        // wk8=> update sprite animation frame based on timing
        spriteplayer.Update(_dt);


        if (TouchManager.Instance.HasTouch())  // Touch and drag
        {
            // Check collision with the smurf sprite
            float imgRadius1 = spriteplayer.GetWidth() * 0.5f;
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

    public float GetPosY()
    {
        return yPos;
    }


    public void onHit(Collidable _other)
    {
        if (_other.GetType() == "PaperEntity")
        {
            // SetisDone
        }
    }


}
