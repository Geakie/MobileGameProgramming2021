package com.example.mobilegameprogramming2021;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class GameSystem {

    public final static GameSystem Instance = new GameSystem();
    public static final String SHARED_PREF_ID = "GameSaveFile";

    // Game stuff
    private boolean isEnded = false;
    private boolean isPaused = false;

    // Scoring
    private int currScore = 0;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    public Trashcan trashcaninstance = null;


    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {

    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {
        // Get our save file
        sharedPref = GamePage.Instance.getSharedPreferences(SHARED_PREF_ID, 0);


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


    public void SaveEditBegin()
    {
        // Safety Check
        if (editor != null)
        {
            return;
        }

        // Start editing
        editor = sharedPref.edit();
    }

    public void SaveEditEnd() {
        // check if has editor
        if (editor == null)
        {
            return;
        }

        editor.commit();
        editor = null; // safety to ensure other functions will fail once commit done

    }

    public void SetIntInSave(String _key, int _value)
    {
        if (editor == null)
        {
            return;
        }
        editor.putInt(_key, _value);
    }

    public int GetIntFromSave(String _key)
    {
        return sharedPref.getInt(_key, 10);
    }


    public int GetScore()
    {
        return currScore;
    }


    public void AddScore()
    {
        AddScore(1);
    }


    public void AddScore(int _amt)
    {
        currScore += _amt;
    }

    public void MinusScore()
    {
        MinusScore(1);
    }

    public void MinusScore(int _amt)
    {
        currScore -= _amt;
    }


    public void ResetScore()
    {
        currScore = 0;
    }

}
