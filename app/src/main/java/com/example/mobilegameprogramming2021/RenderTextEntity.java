package com.example.mobilegameprogramming2021;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

import java.sql.Time;

public class RenderTextEntity extends Context implements EntityBase{
    // Paint
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    private  int red = 0, green = 0, blue = 0;
    Typeface myfont;  // USe for loading font

    // Use for loading FPS so need da more parameters!
    // We want to load FPS on my screen
    int frameCount;
    long lastTime = 0;
    long lastFPSTime = 0;
    float fps;
    float Timer = 60.0f;
    boolean isDone;
    //Intent intent = new Intent();



    @Override
    public boolean IsDone() {
        return isDone;
    }

    public void SetIsDone(boolean _isDone){
        isDone = _isDone;
    }

    @Override
    // For us to intialize or load resource eg: images
    public void Init(SurfaceView _view){
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(),"fonts/Gemcut.otf");
    }
    @Override
    public void Update(float _dt) {
        // get actual fps
        frameCount++;
        // Update Time
        Timer -= 1 * _dt;

        long currentTime = System.currentTimeMillis();

        lastTime = currentTime;

/*
         if (Timer <= 0.0f)
        {
             StateManager.Instance.ChangeState("GameOver")
       }
 */

        if (currentTime - lastFPSTime > 1000)
        {
            fps = (frameCount * 1000.f) / (currentTime - lastFPSTime);
            lastFPSTime = currentTime;
            frameCount = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        paint.setARGB(255, 0, 0, 0);
        paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(50);

        paint2.setARGB(255, 1, 1, 1);
        paint2.setStrokeWidth(200);
        paint2.setTypeface(myfont);
        paint2.setTextSize(50);

        _canvas.drawText("FPS: " + Math.round(fps), 30, 80, paint); // For now, default number but u can use _view.getWidth/ ?
        _canvas.drawText("Time: " + Math.round(Timer), 450, 80, paint);

    }
    @Override
    public boolean IsInit(){
        return true;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.RENDERTEXT_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer){
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_TEXT;
    }

    public static RenderTextEntity Create(){
        RenderTextEntity result = new RenderTextEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_TEXT);
        return result;
    }

    public float gettimer()
    {
        return Timer;
    }


}
