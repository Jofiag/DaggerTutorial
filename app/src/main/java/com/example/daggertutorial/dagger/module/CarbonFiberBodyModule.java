package com.example.daggertutorial.dagger.module;

import com.example.daggertutorial.model.CarBody;
import com.example.daggertutorial.dagger.interfaceProvider.CarbonFiberBody;

import dagger.Module;
import dagger.Provides;

@Module
public class CarbonFiberBodyModule {

    private int protectionPower;

    public CarbonFiberBodyModule(int protectionPower) {
        this.protectionPower = protectionPower;
    }

    @Provides
    CarBody provideCarbonFiberBody(){
        return new CarbonFiberBody(this.protectionPower);
    }
}

//    we can use @Binds instead of @Provide annotation for the method created above, but when doing so,
//    the just declare the prototype of method instead of implementing it's body, also we make the method abstract and it's module class abstract too.
//    Using @Binds is the better way only when we want Dagger to user CarbonFiberBody when we want to return CarBody, because it make us avoid redundant code.
//    If we want to do other thing in the method, then we use @Provide annotation.

//  @Module
//  public abstract class CarbonFiberBodyModule {
//
//      @Binds
//      abstract CarBody provideCarBody(CarbonFiberBody carbonFiberBody);
//  }
