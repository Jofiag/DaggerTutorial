package com.example.daggertutorial.model.DaggerInjector;

import com.example.daggertutorial.MainActivity;
import com.example.daggertutorial.model.Car;

import dagger.Component;

/*
*   Here is the Injector. This class will initialize the instant of our object Car.
*   In order for dagger to know and create at runtime the Car component in our case,
*   we have to add @Inject annotation to the Car constructor and to the constructor of all Car's attributes.
* */

@Component
public interface CarComponent {
    //For the first method : without using the @Inject annotation on the declaration of Car attribute in the MainActivity
    Car getCar();

    //For the second method : using the @Inject annotation on the declaration of Car attribute in the MainActivity.
    //Notice that here the parameter has to be MainActivity, if we put Activity or other context type it won't work.
    //So if we want to create a Car attribute in other activity, we are have to create another method with the specific activity in parameter (one method for each activity).
    void injectInMainActivity(MainActivity mainActivity);
}
