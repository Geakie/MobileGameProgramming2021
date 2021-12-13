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

public class Mainmenu extends Activity implements OnClickListener, StateBase{
    // Define the button. We have 4 Buttons

    // Start
    private Button btn_start;
    // Instruction
    private Button btn_instruction;
    // Option
    private Button btn_option;
    // Exit
    private Button btn_exit;


    @Override
    protected void onCreate (Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.mainmenu);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this); // Set Listener to this button --> Start Button

        btn_instruction = (Button)findViewById(R.id.btn_instruction);
        btn_instruction.setOnClickListener(this); // Set Listener to this button --> Instruction Button

        btn_option = (Button)findViewById(R.id.btn_option);
        btn_option.setOnClickListener(this); // Set Listener to this button --> Option Button

        btn_exit = (Button)findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this); // Set Listener to this button --> Exit Button
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
        if (v == btn_start)
        {
            // Intent -> to set to another class which is another page or screen to be launched
            // Equal to change screen
            intent.setClass(this,GamePage.class);
            StateManager.Instance.ChangeState("MainGame"); // Default is like a loading Page
        }
        else if (v == btn_instruction)
        {
            intent.setClass(this,Instructionmenu.class);
            StateManager.Instance.ChangeState("Instructionmenu");
        }
        else if (v == btn_option)
        {
            intent.setClass(this,Optionmenu.class);
            StateManager.Instance.ChangeState("Optionmenu");
        }
        else if (v == btn_exit)
        {
            intent.setClass(this,Mainmenu.class);
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
    public void Update(float _dt){


    }

    @Override
    public String GetName() {return "Mainmenu";}

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
