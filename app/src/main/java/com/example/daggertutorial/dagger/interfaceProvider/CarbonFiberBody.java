package com.example.daggertutorial.dagger.interfaceProvider;

import android.util.Log;

import com.example.daggertutorial.model.CarBody;

import javax.inject.Inject;

public class CarbonFiberBody implements CarBody {

    private static final String TAG = "CAR";

    private int protectionPower;

    @Inject
    public CarbonFiberBody(int protectionPower) {
        this.protectionPower = protectionPower;
    }

    @Override
    public void protectDriver()  {
        Log.d(TAG, "Carbon Fiber Protection Activated. Protection Power : " + protectionPower);
    }
}
