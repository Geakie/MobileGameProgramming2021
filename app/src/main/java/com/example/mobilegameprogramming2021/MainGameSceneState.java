package com.example.mobilegameprogramming2021;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Created by TanSiewLan2021

public class MainGameSceneState extends Activity implements StateBase {
    private Trashcan trashcan;
    private TouchManager touchManager;
    private PaperEntity paperEntities;
    public RenderTextEntity textEntity;
    private float xPos, yPos;
    public float timer = 10.0f;

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {


        RenderBackground.Create(); // Background is an entity
        RenderTextEntity.Create(); // Text Entity
        Trashcan.Create(); // Player Entity
        PaperEntity.Create(); // Gameobject Entity 1
        //Paperball.Create();
        PauseButtonEntity.Create(); //wk8 <-add pause button

        AudioManager.Instance.PlayAudio(R.raw.bgm, 1.0f);


        for (int i = 0; i < 10; i++)
        {
            PaperEntity.Create();
        }


        GameSystem.Instance.GetScore();
        GameSystem.Instance.ResetScore();
        GameSystem.Instance.SaveEditBegin();
        GameSystem.Instance.SetIntInSave("Score", GameSystem.Instance.GetScore());
        GameSystem.Instance.SaveEditEnd();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

        String scoreText = String.format("SCORE : %d", GameSystem.Instance.GetIntFromSave("Score"));

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(64);

        _canvas.drawText(scoreText, 10, 220, paint);

    }

    @Override
    public void Update(float _dt) {
        EntityManager.Instance.Update(_dt);

        if (GameSystem.Instance.GetIsPaused()) return;

        if (timer <= 0)
        {
            StateManager.Instance.ChangeState("Mainmenu");
        }
        else
        {
            timer -= 1 * _dt;
        }


        //if (TouchManager.Instance.IsDown()) {

            //Example of touch on screen in the main game to trigger back to Main menu
            //StateManager.Instance.ChangeState("Mainmenu");
        //}
    }





    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
        AudioManager.Instance.StopAudio(R.raw.bgm);

    }

}



