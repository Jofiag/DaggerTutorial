package com.example.daggertutorial.dagger.interfaceProvider;

import android.util.Log;

import com.example.daggertutorial.model.CarBody;

import javax.inject.Inject;

public class NormalBody implements CarBody {
    private static final String TAG = "CAR";

    @Inject
    public NormalBody() {
    }

    @Override
    public void protectDriver() {
        Log.d(TAG, "Normal Protection Activated");
    }
}
