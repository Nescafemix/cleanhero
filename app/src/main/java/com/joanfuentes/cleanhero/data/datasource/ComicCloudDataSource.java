package com.joanfuentes.cleanhero.data.datasource;

import com.joanfuentes.cleanhero.data.api.ComicApi;
import com.joanfuentes.cleanhero.domain.model.Comic;

import java.util.List;

import javax.inject.Inject;

public class ComicCloudDataSource {
    private final ComicApi api;

    @Inject
    public ComicCloudDataSource(ComicApi api) {
        this.api = api;
    }

    public List<Comic> getComics(long characterId) {
        return api.getComics(characterId);
    }
}
