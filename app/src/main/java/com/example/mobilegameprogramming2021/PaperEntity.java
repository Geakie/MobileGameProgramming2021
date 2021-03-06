package com.example.mobilegameprogramming2021;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import java.util.Random;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class PaperEntity implements EntityBase, Collidable{
    private boolean isDone = false;
    public int ScreenWidth, ScreenHeight;
    public float yStart = 1,xPos = 0,yPos = 0, offset;
    public float speed;
    Random ranGen;
    private SurfaceView view = null;
    DisplayMetrics metrics;
    private Sprite spritepaper = null;   // New on Week 8
    float imgradiussprite;
    private Vibrator _vibrator;
    private float SpawnTimer;
    private boolean SetActive;


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
        ScreenWidth = _view.getWidth();
        ScreenHeight = _view.getHeight();
        yPos = -1500;
        xPos = ranGen.nextInt(ScreenWidth);

        imgradiussprite = (float) (spritepaper.GetWidth() * 0.5);

        // Set a speed to cross the screen
        speed = (ranGen.nextInt(20) + 1) * _view.getWidth() * 0.05f;
        //week 8=>randomise position
        //xPos = ranGen.nextFloat() * _view.getWidth();
        //yPos = ranGen.nextFloat() * _view.getHeight();

        // Setup Hardware vibrate
        _vibrator = (Vibrator) _view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);

        GameSystem.Instance.GetScore();
        GameSystem.Instance.SaveEditBegin();
        GameSystem.Instance.SetIntInSave("Score", GameSystem.Instance.GetScore());
        GameSystem.Instance.SaveEditEnd();

        SpawnTimer = 5;
    }

    @Override
    public void Update(float _dt) {

        if (GameSystem.Instance.GetIsPaused()) return;

        // wk8=> update sprite animation frame based on timing
        spritepaper.Update(_dt);


        // Check if out of the screen
        if (yPos > ScreenHeight)
        {
            SetIsDone(true);
            AudioManager.Instance.PlayAudio(R.raw.pointlost, 1.0f);
            //int currScore  = GameSystem.Instance.GetIntFromSave("Score");
            //currScore--;
            //GameSystem.Instance.SaveEditBegin();
            //GameSystem.Instance.SetIntInSave("Score", currScore);
            //GameSystem.Instance.SaveEditEnd();


            GameSystem.Instance.GetScore();
            GameSystem.Instance.MinusScore();
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("Score", GameSystem.Instance.GetScore());
            GameSystem.Instance.SaveEditEnd();

        }
        else
        {
            // Update the paper position using speed
            yPos += speed * _dt;
        }
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
        return LayerConstants.BACKGROUND_LAYER;
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



    @Override
    public String GetType() {
        return "PaperEntity";
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
        return imgradiussprite;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "Trashcan")
        {
            AudioManager.Instance.PlayAudio(R.raw.pointgain, 1.0f);
            //startVibrate();

            //int currScore  = GameSystem.Instance.GetIntFromSave("Score");
            //currScore++;
            //GameSystem.Instance.SaveEditBegin();
            //GameSystem.Instance.SetIntInSave("Score", currScore);
            //GameSystem.Instance.SaveEditEnd();

            GameSystem.Instance.GetScore();
            GameSystem.Instance.AddScore();
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("Score", GameSystem.Instance.GetScore());
            GameSystem.Instance.SaveEditEnd();




            SetIsDone(true);
        }
    }

    // Vibrate
    public void startVibrate()
    {
        if (Build.VERSION.SDK_INT >= 26)
        {
            _vibrator.vibrate(VibrationEffect.createOneShot(150,10));
        }
        else
        {
            long pattern[] = {0, 50, 0};
            _vibrator.vibrate(pattern, -1);
        }
    }

    public void stopVibrate()
    {
        _vibrator.cancel();
    }


    public void Spawn(){
        SetActive = true;

    }

}
