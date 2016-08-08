package com.example.therdsak.beatbox;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Therdsak on 8/8/2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    private static final String TAG = "SingleFragmentActivity";

    @LayoutRes
    public int getLayoutResId()
    {
        return R.layout.activity_single_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());


        Log.d(TAG, "On create activity");

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);


        if (f == null)//เช็คว่ามีหรือยัง บอกว่าเจอไหม จะทำงานรอบเดียว
        {
            f = onCreateFragment();

            fm.beginTransaction().add(R.id.fragment_container, f)
                    .commit();

            Log.d(TAG, "Fragment is creatd");

        } else {

            Log.d(TAG, "Fagment have already beencreated");

        }
    }

    protected abstract Fragment onCreateFragment();
}



