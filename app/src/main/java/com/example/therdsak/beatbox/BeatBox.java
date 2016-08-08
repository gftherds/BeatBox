package com.example.therdsak.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Therdsak on 8/8/2016.
 */
public class BeatBox {

    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";

    private static final int MAX_SOUNDS = 5;


    private AssetManager assets;
    private List<Sound> sounds = new ArrayList<>();
    private SoundPool soundpool;


    public BeatBox(Context context) {
        assets = context.getAssets();
        soundpool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);

        loadSounds();
    }
    public void loadSounds() {
        String[] soundNames;


        try{
        soundNames = assets.list(SOUNDS_FOLDER);
            Log.i(TAG, "loadSounds: found all " + soundNames.length+ "sounds");

    } catch (IOException ioe) {
            Log.e(TAG, "loadSounds: could not list asset", ioe);
        return ;


    }
        for (String filename :soundNames ) {


            try {

                String assertPath = SOUNDS_FOLDER+ File.separator+ filename;

                Sound sound = new Sound(assertPath);

                load(sound);

                //add sound in list
                sounds.add(sound);
            } catch (IOException e) {
                e.printStackTrace();
            }



    }


    }

    private void load(Sound sound) throws IOException {
       AssetFileDescriptor afd =  assets.openFd(sound.getAssetPAth());
        int soundId = soundpool.load(afd , 1);
        sound.setSoundId(soundId);

    }


    public List<Sound> getSounds(){
        return sounds;
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();

        if(soundId == null){
            return;
        }
        soundpool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release(){
        soundpool.release();
    }
}
