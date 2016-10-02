package com.joanfuentes.cleanhero.integration.internal.di;

import com.joanfuentes.cleanhero.integration.api.ComicListApiIntegrationTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = IntegrationTestModule.class )
public interface IntegrationTestComponent {
    void inject(ComicListApiIntegrationTest test);
}