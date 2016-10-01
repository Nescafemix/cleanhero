package com.joanfuentes.cleanhero.presentation.view.internal.di;

import android.app.Activity;

import com.joanfuentes.cleanhero.presentation.view.ItemDetailFragment;
import com.joanfuentes.cleanhero.presentation.view.ItemListActivity;
import com.joanfuentes.cleanhero.data.api.endpoint.internal.di.EndpointsModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {EndpointsModule.class})
public class RuntimeActivityModule implements ActivityModule {
    private Activity activity;
    private ItemDetailFragment itemDetailFragment;

    public RuntimeActivityModule(Activity activity) {
        this.activity = activity;
    }

    public RuntimeActivityModule(ItemDetailFragment itemDetailFragment) {
        this.itemDetailFragment = itemDetailFragment;
    }

    @Override @Provides
    public ItemListActivity provideItemListActivity() {
        return (ItemListActivity) activity;
    }
}