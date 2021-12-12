package com.example.mobilegameprogramming2021;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private Trashcan trashcan;
    private TouchManager touchManager;
    private PaperEntity[] paperEntities;
    public RenderTextEntity textEntity;
    private float xPos, yPos;
    private float timer = 0.0f;



    public MainGameSceneState()
    {
    }

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

        for (int i = 0; i < 50; i++)
        {
            PaperEntity.Create();
        }

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



