package com.example.mobilegameprogramming2021;

import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;

public class AudioManager {
    public final static AudioManager Instance = new AudioManager();

    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    private AudioManager()
    {

    }

    public void Init(SurfaceView _view)
    {
        view = _view;
        Release(); // clear the audiomap
    }

    public void PlayAudio(int _id, float _volume)
    {
        // if Audio is loaded
        if (audioMap.containsKey(_id))
        {
            // Have the clip
            MediaPlayer curr = audioMap.get(_id);
            curr.seekTo(0);
            curr.setVolume(_volume, _volume);
            curr.start();
        }
        else
        {
            MediaPlayer curr = MediaPlayer.create(view.getContext(), _id);
            audioMap.put(_id, curr);
            curr.start();
        }
    }

    public void StopAudio(int _id)
    {
        MediaPlayer curr =audioMap.get(_id);
        curr.stop();
    }


    // Release the resource
    public void Release()
    {
        for (HashMap.Entry<Integer, MediaPlayer> entry : audioMap.entrySet())
        {
            entry.getValue().stop();
            entry.getValue().reset();
            entry.getValue().release();
        }

        //Empty it
        audioMap.clear();
    }

    private MediaPlayer GetAudio(int _id)
    {
        // Check if audio is loaded or not
        if (audioMap.containsKey(_id))
        {
            // has the clip then return it
            return audioMap.get(_id);
        }

        MediaPlayer result = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id, result);
        return result;

    }

}
