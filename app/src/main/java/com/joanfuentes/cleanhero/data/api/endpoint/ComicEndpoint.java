package com.joanfuentes.cleanhero.data.api.endpoint;

import com.joanfuentes.cleanhero.data.api.endpoint.model.ComicEndpointResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ComicEndpoint {
    String ENDPOINT = "/v1/public/characters/{id}/comics";

    @GET(ENDPOINT)
    Call<ComicEndpointResponse> getComicList(@Path("id") long characterId,
                                             @Query("apikey") String apiKey,
                                             @Query("ts") String ts,
                                             @Query("hash") String hash,
                                             @Query("orderBy") String orderBy);
}
