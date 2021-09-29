package com.example.daggertutorial.model;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";

    private Engine engine;
    private Wheels wheels;
    private CarSeat carSeat;

    @Inject
    public Car(Engine engine, Wheels wheels, CarSeat carSeat) {
        this.engine = engine;
        this.wheels = wheels;
        this.carSeat = carSeat;
    }


    ///////////////////// METHOD INJECTION /////////////////////
    /*
     *   To use Dagger method injection we have to add @Inject annotation to the method and it's parameters constructor so that it create it.
     *   Here the method we use is in the model Car, that means whenever an instance (of a Car created by Dagger) is called, the method used is executed by Dagger.
     *   IMPORTANT: methods injected with Dagger are only executed if we used constructor injection of the method class. Also we don't do method injection in activities.
     * */

    /////   Notice that the order of Dagger execution in a class is constructor, field(attribute), method.

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
