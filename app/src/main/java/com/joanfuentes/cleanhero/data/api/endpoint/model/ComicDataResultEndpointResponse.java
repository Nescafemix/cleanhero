package com.joanfuentes.cleanhero.data.api.endpoint.model;

import java.util.List;

public class ComicDataResultEndpointResponse {
    public String title;
    public String description;
    public ComicDataResultImageEndpointResponse thumbnail;
    public List<ComicDataResultImageEndpointResponse> images;
}
