package com.example.daggertutorial;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.daggertutorial.dagger.injector.CarComponent;
import com.example.daggertutorial.dagger.injector.DaggerCarComponent;
import com.example.daggertutorial.model.Car;

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

        //Here is how we provide interface using Dagger
        providingInterface();

        //Here is how we allow injecting value to Dagger at runtime when using Dagger interface provider
        injectValuesAtRuntimeForInterfaceProvider();
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
//        CarComponent carComponent = DaggerCarComponent.create();
//        car = carComponent.getCar();
//        car.drive();

        /// METHOD 2
        //  A second method to create a car using Dagger is to add the @Inject annotation when declaring the attribute,
        //  but you should not use private while doing so because Dagger need to access the variable.
        //  And you have de declare a void method in the Injector interface,
        //  that methode must have the activity where you're going to create the Car as parameter(MainActivity here).
        //  Then you can call that method and pass the activity, so that Dagger do the job.
//        CarComponent carComponent = DaggerCarComponent.create();
//        carComponent.injectInMainActivity(this);
//        car2.drive();

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

    private void providingInterface(){
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

    private void injectValuesAtRuntimeForInterfaceProvider(){
        /*
        * Some times we need to inject values (any variable or object created) to an object when instantiating that object. This is in the case of interface providing.
        * When using Dagger this is how to do:
        * (In our case here, we are going to use one of our interface provider CarbonFiberBody, so we are going to use there module as well)
        * (We are going to use only one value here that is an integer : protectionPower. Notice that you can use as many values as you need)
        *
        *   1) Add the protectionPower as an attribute to the interface provider (CarbonFiberBody).
        *
        *   2) Remove the @Inject annotation on the constructor of CarbonFiberBody, because we want to pass a value when instantiating the object,
        *      so we don't need Dagger to provide us the constructor anymore.
        *
        *   3) Add protectionPower as a parameter of the constructor and set the attribute with the parameter:
        *      public CarbonFiberBody(int protectionPower) {
        *           this.protectionPower = protectionPower;
        *       }
        *       Here after that we append the protectionPower inside the interface override method to check if it will work after the instantiation.
        *
        *   4) Go to the module of the class : CarbonFiberBodyModule. Replace @Binds by @Provide on the method witch return CarBody and remove the abstract from the class.
        *      Of course if you didn't use @Binds just keep the @Provide, or if you don't have that method create it with the @Provide annotation.
        *      In our case it is the provideCarbonFiberBody method.
        *
        *   5) Add protectionPower as an attribute to the module CarbonFiberModule and generate the constructor with that attribute.
        *
        *   6) Remove the CarbonFiberBody parameter of provideCarbonFiberBody. Add "return new CarbonFiberBody(this.protectionPower)" to the method body.
        *      If you want to use it in more than one activity, you should:
        *           a) add Context attribute to CarbonFiberBody,
        *           b) update the constructor with the context attribute,
        *           c) create a provideContext method that return the context attribute created above (don't forget that method must have @Provide annotation),
        *           d) now you can instantiate it in whatever activity you want.
        *
        *   7) Add a getter for protectionPower, the getter is a @Provide method. These way we can use protectionPower whenever we need it.
        *      If we add the @Provides getter method we can keep the @Provides getter method parameter witch is here CarbonFiberBody; and just return that parameter.
        *      (Remember the step 6, we remove the parameter)
        *      But make sure you didn't forget to add @Inject annotation to the constructor of the interface provider concerned (CarbonFiberBody here).
        *
        *   8) Make sure the module used (here it's CarbonFiberBodyModule is declared in the annotation of the injector interface) like this:
        *      @Component(modules = {..., CarbonFiberBodyModule.class})
        *      Build the object in the activity :
        *           CarComponent carComponent = DaggerCarComponent.builder()
        *               .carbonFiberBodyModule(new CarbonFiberBodyModule(100))
        *               .build();
        *
        *   9) Another method to build the object and inject the value at runtime (For this method we're going to use our second interface provider NormalBody.class):
        *       a) add your value as an attribute of the interface provider NormalBody, the value here is protectionPower
        *       b) add that attribute to the constructor
        *       c) define manually the builder of the injector interface :
        *           @Component.Builder
        *           interface Builder{
        *               @BuildsInstance
         *              Builder protectionPower(int protectionPower);
         *
        *               CarComponent build();
        *           }
        *       d) when doing so, we have to implement the prototype of all the build method : CarComponent build();
        *       e) also in Builder interface, we implement the prototype of the method that will inject the value at runtime:
        *           @BuildsInstance
        *           Builder protectionPower(int protectionPower);
        *       f) now make sure that the right module is declare in the annotation of the injector interface : @Component(modules = {..., NormalBodyModule.class}),
        *          remember there shouldn't be more than one module of the same interface inside that annotation, otherwise it won't work.
        *       g) finally in the activity you can build :
        *          CarComponent carComponent = DaggerCarComponent.builder()
        *               .protectionPower(50)
        *               .build();
        *       By using this method the module doesn't need to have the values as attribute, neither it's @Provides getter method and nor the module constructor.
        *
        *    10) Using second method, but this time with multiple values to inject at runtime:
        *       a) add the second value as an attribute to NormalBody and add it to the constructor
        *       b) in the Build interface add another method prototype that will inject the value,
        *          but this time with @Named("name of the value") annotation just before the declaration of the value in the parentheses
        *          and for the method of other values as well.
        *          Add the same annotation in the constructor of interface provide NormalBody:
        *          @Component.Builder
        *          interface Builder{
        *
        *               @BuildsInstance
        *               Builder protectionPower(@Named("protection power")int protectionPower);
        *
        *               @BuildsInstance
        *               Builder protectionPower(@Named("ground proximity")int groundProximity);
        *
        *               CarComponent build();
        *          }
        *
        *          @Inject
        *          public NormalBody(@Named("protection power") int protectionPower,
        *                            @Named("ground proximity") int groundProximity) {
        *
        *            this.protectionPower = protectionPower;
        *            this.groundProximity = groundProximity;
        *           }
        *
        *           Notice : If you don't add the add annotation, dagger won't know when to use values with the same type and send you an error.
        *
        *
        *
        * */

//        CarComponent carComponent = DaggerCarComponent.builder()
//                .carbonFiberBodyModule(new CarbonFiberBodyModule(100))
//                .build();

        CarComponent carComponent = DaggerCarComponent.builder()
                .protectionPower(50)
                .groundProximity(2)
                .build();

        car = carComponent.getCar();
        car.drive();

        carComponent.injectInMainActivity(MainActivity.this);
        car2.drive();
    }
}