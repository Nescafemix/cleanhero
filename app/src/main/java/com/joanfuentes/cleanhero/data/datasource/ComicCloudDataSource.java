package com.joanfuentes.cleanhero.data.datasource;

import com.joanfuentes.cleanhero.data.api.ComicApi;
import com.joanfuentes.cleanhero.domain.model.ComicDTO;

import java.util.List;

import javax.inject.Inject;

public class ComicCloudDataSource {
    private final ComicApi api;

    @Inject
    public ComicCloudDataSource(ComicApi api) {
        this.api = api;
    }

    public List<ComicDTO> getComics(long characterId) {
        return api.getComics(characterId);
    }
}
