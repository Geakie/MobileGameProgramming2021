package com.example.mobilegameprogramming2021;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Display;
import android.view.SurfaceView;
import java.util.Random;

public class PaperEntity implements EntityBase{
    public int speed = 20;
    private boolean isDone = false;
    private int screenX, screenY;
    private float width,height;
    int x,y;
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

        width = spritepaper.GetWidth();
        height = spritepaper.GetHeight();



        //week 8=>randomise position
        //xPos = ranGen.nextFloat() * _view.getWidth();
        //yPos = ranGen.nextFloat() * _view.getHeight();
    }

    @Override
    public void Update(float _dt) {

        if (GameSystem.Instance.GetIsPaused()) return;

        // wk8=> update sprite animation frame based on timing
        spritepaper.Update(_dt);

    }


    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        spritepaper.Render(_canvas, (int)140, (int)300);
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
