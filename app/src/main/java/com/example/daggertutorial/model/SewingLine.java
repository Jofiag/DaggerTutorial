package com.example.daggertutorial.model;

import android.util.Log;

public class SewingLine {
    //We supposing that we don't own this class, in order to use Dagger module and provider methods

    private static final String TAG = "CAR";

    public void inflate(){
        Log.d(TAG, "Sewing line inflated ");
    }

}
