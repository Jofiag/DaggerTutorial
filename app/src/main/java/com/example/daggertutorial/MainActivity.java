package com.example.daggertutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.daggertutorial.model.Car;
import com.example.daggertutorial.dagger.injector.CarComponent;
import com.example.daggertutorial.dagger.daggerInjector.DaggerCarComponent;

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

        //Here is the case where we do not own the object we want Dagger to provide.
        modulesAndProviderMethods();
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

    private void modulesAndProviderMethods(){
        /*
        *   When we do not have access to the object we want Dagger to provide we use modules and provider methods.
        *   For the module part, we create a CarSeatModule class that will correspond to the CarSeat object we don't own.
        *   After that we add @Module annotation to that class.
        *   Then we create one public method for each attribute of CarSeatModule, those methods must return it's attribute and have the @Provide annotation.
        *   Now we create a public method for CarSeat, that method must return CarSeat and have for parameters the CarSeat attributes (for witch we created provide method earlier)
        *   And in the Injector interface we have to add next to @Component annotation this : (modules = CarSeatModule.class),
        *   so whenever a method of that interface is called dagger knows that he have to use CarSeatModule class to provide CarSeat object.
        *
        * */
    }

    private void providingInterfaceWithBind(){
        ///////////////////// INTERFACE PROVIDING /////////////////////
        /*  To allow Dagger to provide interface we have to :
        *
        *   1) create as many class as we need that implement the interface we want Dagger to provide (in this case that interface is CarBody.
        *       Classes created here : CarbonFiberBody and NormalBody)
        *
        *   2) inject those classes constructor (with @Inject annotation because since we created those classes we have access to them) so that Dagger knows how to provide them
        *
        *   3) create a module for each class created (CarbonFiberBody and NormalBody) so that Dagger knows witch of them he has to provide.
        *
        *   4) of course we have to add @Module annotation to each module created (CarbonFiberBodyModule and NormalBodyModule)
        *
        *   5) create a method in each module class witch return the interface we want Dagger to provide and take for parameter it's parent class, add @Provide annotation each method
        *      (the provide method of CarbonFiberBodyModule take CarbonFiberBody as parameter and return it;
        *       the return type declaration is the interface CarBody not the class CarbonFiberBody)
        *
        *   6) we can use @Binds instead of @Provide annotation for the method created above, but when doing so,
        *      the just declare the prototype of method instead of implementing it's body, also we make the method abstract and it's module class abstract too.
        *      Using @Binds is the better way only when we want Dagger to user CarbonFiberBody when we want to return CarBody, because it make us avoid redundant code.
        *      If we want to do other thing in the method, then we use @Provide annotation.
        *
        *   7) add @Component(modules = {CarSeatModule.class, CarbonFiberBodyModule.class}). Here we cannot choose more than one module witch provide the same interface in the same injector interface.
        *      That's means we cannot put both CarbonFiberBodyModule.class and NormalBodyModule.class in the CarComponent interface.
        *      (we cannot do this in CarComponent : @Component(modules = {CarSeatModule.class, CarbonFiberBodyModule.class, NormalBodyModule.class})).
        *      If we want to test other module we created, we just need to replace the actual one with the one we want to test.
        *
        *
        *   8) now we can call the interface concerned method we want to use (here is protectDriver method from CarBody interface).
        *      Here we call it inside a method of the object we want Dagger to provide Car.
        *
        * */

    }
}