package com.example.daggertutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.daggertutorial.model.Car;
import com.example.daggertutorial.model.daggerInjector.CarComponent;
import com.example.daggertutorial.model.daggerInjector.DaggerCarComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private Car car;
    @Inject Car car2;

    /*
    *  The purpose of Dagger is to create object and provide them at the right time.
    *  That means we have to inform Dagger witch object to create and when it should do it.
    *  One way to do so is by using injection annotation in the object concerned,
    *  but when we don't own the object it's not possible because we cannot add code in the object we don not own.
    *  Another way is to use module and provider methods, that is useful when we don not own the object we want Dagger to provide.
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Here is the case where we own the objects we want Dagger to provide, so we can write code in them
        daggerInjection();
    }

    private void daggerInjection(){
        ///////////////////// FIELD INJECTION /////////////////////

        /// METHOD 1
        //   With Dagger we don't need to initialize our Car instant object with it attributes, because it Dagger creates all the attributes needed for us.
        //   To allow Dagger to do so we have to create the Injector interface that is going to initialize our Car instant here.
        //   And we also have to add the annotation required to Car constructor and it's attributes' constructor.
        //   Because we added the annotation required and compile the project successfully,
        //   Dagger created DaggerCarComponent knowing that CarComponent is the Injector interface.
        //   Then we can use that injector to get our car created by Dagger.
        CarComponent carComponent = DaggerCarComponent.create();
        car = carComponent.getCar();
        car.drive();

        /// METHOD 2
        //  A second method to create a car using Dagger is to add the @Inject annotation when declaring the attribute,
        //  but you should not use private while doing so because Dagger need to access the variable.
        //  And you have de declare a void method in the Injector interface,
        //  that methode must have the activity where you're going to create the Car as parameter(MainActivity here).
        //  Then you can call that method and pass the activity, so that Dagger do the job.
        carComponent.injectInMainActivity(this);
        car2.drive();

        ///////////////////// METHOD INJECTION /////////////////////
        /*
         *   To use Dagger method injection we have to add @Inject annotation to the method and it's parameters constructor so that it create it.
         *   Here the method we use is in the model Car, that means whenever an instance (of a Car created by Dagger) is called, the method used is executed by Dagger.
         *   IMPORTANT: methods injected with Dagger are only executed if we used constructor injection of the method class. Also we don't do method injection in activities.
         * */

        /////   Notice that the order of Dagger execution in a class is constructor, field(attribute), method.
    }
}