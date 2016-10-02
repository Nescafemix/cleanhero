package com.joanfuentes.cleanhero.presentation.view.internal.di;

import android.app.Activity;

import com.joanfuentes.cleanhero.presentation.view.ComicDetailFragment;
import com.joanfuentes.cleanhero.presentation.view.ComicListActivity;
import com.joanfuentes.cleanhero.data.api.endpoint.internal.di.EndpointsModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {EndpointsModule.class})
public class RuntimeActivityModule implements ActivityModule {
    private Activity activity;
    private ComicDetailFragment comicDetailFragment;

    public RuntimeActivityModule(Activity activity) {
        this.activity = activity;
    }

    public RuntimeActivityModule(ComicDetailFragment comicDetailFragment) {
        this.comicDetailFragment = comicDetailFragment;
    }

    @Override @Provides
    public ComicListActivity provideItemListActivity() {
        return (ComicListActivity) activity;
    }
}