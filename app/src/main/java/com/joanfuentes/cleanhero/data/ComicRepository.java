package com.joanfuentes.cleanhero.data;

import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.data.datasource.ComicCloudDataSource;

import java.util.List;

import javax.inject.Inject;

public class ComicRepository {
    private final ComicCloudDataSource cloudDataSource;

    @Inject
    public ComicRepository(ComicCloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    public void getComics(long characterId, final Callback callback) {
        try {
            callback.onSuccess(cloudDataSource.getComics(characterId));
        } catch (Exception error) {
            callback.onError();
        }

    }

    public interface Callback {
        void onSuccess(final List<Comic> comics);
        void onError();
    }
}
