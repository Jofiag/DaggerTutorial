package com.example.daggertutorial.dagger.interfaceProvider;

import android.util.Log;

import com.example.daggertutorial.model.CarBody;

import javax.inject.Inject;

public class CarbonFiberBody implements CarBody {

    private static final String TAG = "CAR";

    @Inject
    public CarbonFiberBody() {
    }

    @Override
    public void protectDriver() {
        Log.d(TAG, "Carbon Fiber Protection Activated");
    }
}
