package com.joanfuentes.cleanhero.data.api.endpoint.internal.di;

import com.joanfuentes.cleanhero.data.api.endpoint.ComicEndpoint;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class EndpointsModule {
    private static final String BASE_URL = "http://gateway.marvel.com/";

    @Provides
    ComicEndpoint provideComicEndpoint() {
        return getRetrofitBuilder().create(ComicEndpoint.class);
    }

    private Retrofit getRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
