package com.example.daggertutorial.dagger.interfaceProvider;

import android.util.Log;

import com.example.daggertutorial.model.CarBody;

import javax.inject.Inject;
import javax.inject.Named;

public class NormalBody implements CarBody {
    private static final String TAG = "CAR";

    private int protectionPower;
    private int groundProximity;

    @Inject
    public NormalBody(@Named("protection power") int protectionPower,
                      @Named("ground proximity") int groundProximity) {

        this.protectionPower = protectionPower;
        this.groundProximity = groundProximity;
    }

    @Override
    public void protectDriver() {
        Log.d(TAG, "Normal Protection Activated. \nProtection Power : " + protectionPower
                                                    + "\nGround Proximity : " + groundProximity);
    }
}
