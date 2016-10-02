package com.joanfuentes.cleanhero.data.api;

import com.joanfuentes.cleanhero.data.api.endpoint.ComicEndpoint;
import com.joanfuentes.cleanhero.data.api.endpoint.model.ComicEndpointResponse;
import com.joanfuentes.cleanhero.data.api.generator.HashGenerator;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.data.api.model.ComicResponseMapper;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class ComicApi extends AbsRetrofitApi{
    private static final String ORDER_BY_LAST_ON_SALE = "-onsaleDate";
    private final ComicEndpoint endpoint;
    private final ComicResponseMapper mapper;
    private final HashGenerator hashGenerator;

    @Inject
    public ComicApi(ComicEndpoint endpoint, ComicResponseMapper mapper,
                    HashGenerator hashGenerator) {
        this.endpoint = endpoint;
        this.mapper = mapper;
        this.hashGenerator = hashGenerator;
    }

    public List<Comic> getComics(long characterId) {
        List<Comic> result;
        try {
            String timestamp = getCurrentTimestamp();
            String hash = hashGenerator.getHash(timestamp, PRIVATE_KEY, PUBLIC_KEY);
            final Call<ComicEndpointResponse> apiCaller = endpoint
                    .getComicList(characterId, PUBLIC_KEY, timestamp, hash, ORDER_BY_LAST_ON_SALE);
            final Response<ComicEndpointResponse> response = apiCaller.execute();
            final ComicEndpointResponse comics = response.body();
            result = mapper.map(comics.data.results);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
