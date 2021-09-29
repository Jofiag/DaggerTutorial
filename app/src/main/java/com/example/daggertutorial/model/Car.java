package com.example.daggertutorial.model;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";

    private Engine engine;
    private Wheels wheels;

    @Inject
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }


    /*
    *   When using Dagger method injection, here we cannot call the method concerned in the constructor because the parameter here is not created yet by Dagger.
    *   That means we don't have to call the method, Dagger does it for us.
    * */
    @Inject
    public void enableRemote(Remote remote){
        remote.setListener(this);
    }

    public void drive(){
        Log.d(TAG, "driving: ");
    }
}
