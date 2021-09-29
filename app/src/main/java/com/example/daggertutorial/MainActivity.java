package com.example.daggertutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.daggertutorial.model.Car;
import com.example.daggertutorial.model.DaggerInjector.CarComponent;
import com.example.daggertutorial.model.DaggerInjector.DaggerCarComponent;

public class MainActivity extends AppCompatActivity {
    private Car car;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        *   With Dagger we don't need to initialize our Car instant object with it attributes, because it Dagger creates all the attributes needed for us.
        *   To allow Dagger to do so we have to create the Injector interface that is going to initialize our Car instant here.
        *   And we also have to add the annotation required to Car constructor and it's attributes' constructor.
        *   Because we added the annotation required and compile the project successfully, Dagger created DaggerCarComponent knowing that CarComponent is the Injector interface.
        *   Then we can use that injector to get our car created by Dagger.
        * */

        CarComponent carComponent = DaggerCarComponent.create();

        car = carComponent.getCar();
        car.drive();

    }
}