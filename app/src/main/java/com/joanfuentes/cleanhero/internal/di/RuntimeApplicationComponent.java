package com.joanfuentes.cleanhero.internal.di;

import com.joanfuentes.cleanhero.Application;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RuntimeApplicationModule.class)
public interface RuntimeApplicationComponent extends ApplicationComponent {
    void inject(Application application);
}
