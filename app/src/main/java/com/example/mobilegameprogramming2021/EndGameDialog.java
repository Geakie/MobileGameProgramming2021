package com.example.mobilegameprogramming2021;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class EndGameDialog extends DialogFragment {
    public static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        IsShown = true;
        // Use the builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Save Score?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User triggered phone
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),GameOver.class);
                        StateManager.Instance.ChangeState("GameOver");
                        startActivity(intent);
                        GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                        IsShown = false;

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the pause
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),GameOver.class);
                        StateManager.Instance.ChangeState("GameOver");
                        startActivity(intent);

                        IsShown = false;

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

