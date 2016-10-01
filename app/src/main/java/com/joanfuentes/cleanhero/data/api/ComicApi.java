package com.joanfuentes.cleanhero.data.api;

import android.util.Log;

import com.joanfuentes.cleanhero.data.api.endpoint.ComicEndpoint;
import com.joanfuentes.cleanhero.data.api.endpoint.model.ComicEndpointResponse;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.data.api.model.ComicResponseMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class ComicApi extends AbsRetrofitApi{
    private final ComicEndpoint endpoint;
    private final ComicResponseMapper mapper;

    @Inject
    public ComicApi(ComicEndpoint endpoint, ComicResponseMapper mapper) {
        this.endpoint = endpoint;
        this.mapper = mapper;
    }

    public List<Comic> getComics(long characterId) {
        List<Comic> result = Collections.emptyList();
        try {
            final Call<ComicEndpointResponse> apiCaller = endpoint
                    .getComicList(characterId, API_KEY, TS, HASH);
            final Response<ComicEndpointResponse> response = apiCaller.execute();
            final ComicEndpointResponse comics = response.body();
            result = mapper.map(comics.data.results);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            Log.d("ERROR","ERROR: " + e.getMessage());
        }
        return result;
    }
}
