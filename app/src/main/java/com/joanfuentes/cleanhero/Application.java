package com.joanfuentes.cleanhero;

import com.joanfuentes.cleanhero.internal.di.ApplicationComponent;
import com.joanfuentes.cleanhero.internal.di.DaggerRuntimeApplicationComponent;
import com.joanfuentes.cleanhero.internal.di.RuntimeApplicationComponent;
import com.joanfuentes.cleanhero.internal.di.RuntimeApplicationModule;

public class Application extends android.app.Application {
    private RuntimeApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjection();
    }

    private void initializeInjection() {
        this.applicationComponent = DaggerRuntimeApplicationComponent
                .builder()
                .runtimeApplicationModule(new RuntimeApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}