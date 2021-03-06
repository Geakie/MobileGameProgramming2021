package com.example.mobilegameprogramming2021;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PauseButtonEntity implements EntityBase{

    private Bitmap bmpP,bmpUP,ScaledbmpP,ScaledbmpUP;
    private float xPos = 0, yPos = 0;

    private boolean isDone = false;
    private boolean isInit = false;
    private boolean Paused = false;

    int ScreenWidth, ScreenHeight;

    private float buttonDelay = 0;

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

        bmpP = ResourceManager.Instance.GetBitmap(R.drawable.pause);
        bmpUP = ResourceManager.Instance.GetBitmap(R.drawable.pause1);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        ScaledbmpP = Bitmap.createScaledBitmap(bmpP, (int) (ScreenWidth)/12, (int)(ScreenWidth)/7, true);
        ScaledbmpUP = Bitmap.createScaledBitmap(bmpUP, (int) (ScreenWidth)/12, (int)(ScreenWidth)/7, true);

        xPos = ScreenWidth - 150;
        yPos = 150;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        buttonDelay += _dt;

        if (TouchManager.Instance.HasTouch() && !Paused)
        {
            // Check Collision of button here!!
            float imgRadius = ScaledbmpP.getHeight() * 0.5f;
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) && buttonDelay >= 0.25) {
                Paused = true;


                // Button got clicked show the popup dialog
                if (PauseConfirmDialogFragment.IsShown)
                {
                    return;
                }

                PauseConfirmDialogFragment newPauseConfirm = new PauseConfirmDialogFragment();
                newPauseConfirm.show(GamePage.Instance.getSupportFragmentManager(), "PauseConfirm");


                //buttonDelay = 0;
                //GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());


            //if (!Paused) {
                // Check Collision of button here!!
            //    float imgRadius = ScaledbmpP.getHeight() * 0.5f;
                //buttonDelay = 0;
                //GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
            }

            //buttonDelay = 0;
            //GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());

        }
        else
        {
            Paused = false;
            //AudioManager.Instance.PlayAudio(R.raw.bgm, 1.0f);
        }


    }

    @Override
    public void Render(Canvas _canvas) {

        if (Paused == false)
            _canvas.drawBitmap(ScaledbmpP,xPos - ScaledbmpP.getWidth() * 0.5f, yPos - ScaledbmpP.getHeight() * 0.5f, null);
        else
            _canvas.drawBitmap(ScaledbmpUP,xPos - ScaledbmpUP.getWidth() * 0.5f, yPos - ScaledbmpUP.getHeight() * 0.5f, null);


    }

    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.PAUSEBUTTON_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_PAUSE;}

    public static PauseButtonEntity Create()
    {
        PauseButtonEntity result = new PauseButtonEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }
}
