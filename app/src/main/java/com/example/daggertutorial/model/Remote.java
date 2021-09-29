package com.example.daggertutorial.model;

import android.util.Log;

import javax.inject.Inject;

public class Remote {
    private static final String TAG = "Car";

    @Inject
    public Remote() {
    }

    public void setListener(){
        Log.d(TAG, "Remote connected");
    }
}
