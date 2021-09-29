package com.example.daggertutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.daggertutorial.model.Car;

public class MainActivity extends AppCompatActivity {
    private Car car;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        *   With Dagger we don't need to initialize our Car instant object, because it does it for us.
        *   Since we are using Dagger we should create the Injector interface that is going to initialize our Car instant here.
        * */
        car.drive();

    }
}