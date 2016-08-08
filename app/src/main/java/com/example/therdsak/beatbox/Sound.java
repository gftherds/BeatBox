package com.example.therdsak.beatbox;

import android.content.Intent;
import android.util.Log;

/**
 * Created by Therdsak on 8/8/2016.
 */
public class Sound {
    private static final String TAG = "Sound";

    private String assetPath;
    private String name;

    public Integer getSoundId() {
        return soundId;
    }



    private Integer soundId;


    public Sound(String assetPath){
        this.assetPath = assetPath;
        Log.d(TAG, "Asset Path: " + assetPath);
        String[] components = assetPath.split("/");
        String filename = components[components.length-1];
        name = filename.replace(".wav","");
        Log.d(TAG, "name=" + name + ", assetPath="+assetPath);



    }
    public void setSoundId(Integer soundId) {
        this.soundId = soundId;
    }

    public String getAssetPAth(){
        return assetPath;
    }

    public String getName(){
        return name;
    }
}
