package com.example.mobilegameprogramming2021;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GameOver extends Activity implements OnClickListener, StateBase{
    // Define the button. We have 1 Buttons

    // Back
    private Button btn_back;


    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.gameover);

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this); // Set Listener to this button --> Back Button

        StateManager.Instance.AddState(new GameOver());

    }

    @Override
    // Invoke a callback event in the view
    public void onClick(View v)
    {
        // Intent = action to be performed
        // Intent is an object provides runtime binding
        // You have to new an instance of this object to use it

        // Check if Start Button is Clicked/Touched, then after what we want to do.
        // If Start is Clicked/Touched, we go to Splashscreen.
        Intent intent = new Intent();
        if (v == btn_back)
        {
            // Intent -> to set to another class which is another page or screen to be launched
            // Equal to change screen
            intent.setClass(this,Mainmenu.class);
            StateManager.Instance.ChangeState("Mainmenu"); // Default is like a loading Page
        }
        startActivity(intent);
        // If Exit is Clicked/Touched, we exit the app.
    }

    @Override
    public void Render(Canvas _canvas) {}

    @Override
    public void OnEnter(SurfaceView _view) {}

    @Override
    public void OnExit() {}

    @Override
    public void Update(float _dt){}

    @Override
    public String GetName() {return "GameOver";}

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

}
