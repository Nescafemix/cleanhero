package com.joanfuentes.cleanhero.integration.internal.di;

import com.joanfuentes.cleanhero.data.api.endpoint.internal.di.EndpointsModule;

import dagger.Module;

@Module(includes = {
        EndpointsModule.class})
public class IntegrationTestModule { }