package com.example.daggertutorial.model.daggerModule;

import com.example.daggertutorial.model.CarSeat;
import com.example.daggertutorial.model.SewingLine;
import com.example.daggertutorial.model.Tissue;

import dagger.Module;
import dagger.Provides;

@Module
public class CarSeatModule {

    @Provides
    public Tissue provideTissue(){
        return new Tissue();
    }

    @Provides
    public SewingLine provideSewingLine(){
        SewingLine sewingLine = new SewingLine();
        sewingLine.inflate();

        return sewingLine;
    }

    @Provides
    public CarSeat provideCarSeat(Tissue tissue, SewingLine sewingLine){
        return new CarSeat(tissue, sewingLine);
    }
}
