package com.example.mobilegameprogramming2021;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class GameSystem {

    public final static GameSystem Instance = new GameSystem();

    // Game stuff
    private boolean isEnded = false;
    private boolean isPaused = false;

    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {

    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {

        // We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());
        StateManager.Instance.AddState(new MainGameSceneState());
        StateManager.Instance.AddState(new Instructionmenu());
        StateManager.Instance.AddState(new Optionmenu());
        StateManager.Instance.AddState(new GameOver());
        StateManager.Instance.AddState(new PauseScreen());
    }

    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public void SetIsEnded(boolean _newIsEnded)
    {
        isEnded = _newIsEnded;
    }


    public boolean GetIsPaused()
    {
        return isPaused;
    }

    public boolean GetIsEnded()
    {
        return isEnded;
    }

}
