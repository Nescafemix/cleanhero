package com.joanfuentes.cleanhero.presentation.view.internal.di;

import android.app.Activity;

import com.joanfuentes.cleanhero.presentation.view.ItemListActivity;
import com.joanfuentes.cleanhero.data.api.endpoint.internal.di.EndpointsModule;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                PresentationModule.class,
                EndpointsModule.class
        }
)
public class RuntimeActivityModule implements ActivityModule {
    private final Activity activity;

    public RuntimeActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Override @Provides
    public ItemListActivity provideItemListActivity() {
        return (ItemListActivity) activity;
    }
}