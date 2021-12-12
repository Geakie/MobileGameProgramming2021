package com.example.mobilegameprogramming2021;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private Trashcan trashcan;
    private TouchManager touchManager;
    private float xPos, yPos;

    private float timer = 0.0f;


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
        PauseButtonEntity.Create(); //wk8 <-add pause button

        // Example to include another Renderview for Pause Button
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

    }

    @Override
    public void Update(float _dt) {
        EntityManager.Instance.Update(_dt);

        if (GameSystem.Instance.GetIsPaused()) return;

        if (TouchManager.Instance.IsDown())
        {

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
    }

}



