package com.joanfuentes.cleanhero.presentation.view.internal.di;

import com.joanfuentes.cleanhero.internal.di.ApplicationComponent;
import com.joanfuentes.cleanhero.internal.di.scopes.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = RuntimeActivityModule.class)
public interface RuntimeActivityComponent extends ActivityComponent { }