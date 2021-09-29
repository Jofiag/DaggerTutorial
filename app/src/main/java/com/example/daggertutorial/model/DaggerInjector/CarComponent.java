package com.example.daggertutorial.model.DaggerInjector;

import com.example.daggertutorial.model.Car;

import dagger.Component;

/*
*   Here is the Injector. This class will initialize the instant of our object Car.
*
* */

@Component
public interface CarComponent {
    Car getCar();
}
