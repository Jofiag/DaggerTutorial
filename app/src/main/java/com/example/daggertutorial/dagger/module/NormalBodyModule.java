package com.example.daggertutorial.dagger.module;

import com.example.daggertutorial.model.CarBody;
import com.example.daggertutorial.dagger.interfaceProvider.NormalBody;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NormalBodyModule {

    @Binds
    abstract CarBody provideNormalCarBody(NormalBody normalBody);

}
