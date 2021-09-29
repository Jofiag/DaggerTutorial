package com.example.daggertutorial.model;

public class CarSeat {
    //We supposing that we don't own this class, in order to use Dagger module and provider methods
    private Tissue tissue;
    private SewingLine sewingLine;

    public CarSeat(Tissue tissue, SewingLine sewingLine) {
        this.tissue = tissue;
        this.sewingLine = sewingLine;
    }
}
