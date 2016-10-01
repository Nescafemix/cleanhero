package com.joanfuentes.cleanhero.data.api.model;

import com.joanfuentes.cleanhero.data.api.endpoint.model.ComicDataResultEndpointResponse;
import com.joanfuentes.cleanhero.data.api.endpoint.model.ComicDataResultImageEndpointResponse;
import com.joanfuentes.cleanhero.data.api.transformer.ImageUriTransformer;
import com.joanfuentes.cleanhero.domain.model.Comic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ComicResponseMapper {

    private ImageUriTransformer imageUriTransformer;

    @Inject
    public ComicResponseMapper(ImageUriTransformer imageUriTransformer){
        this.imageUriTransformer = imageUriTransformer;
    }

    public List<Comic> map(List<ComicDataResultEndpointResponse> response) {
        List<Comic> comics = new ArrayList<>();
        for (ComicDataResultEndpointResponse result: response) {
            Comic comic = new Comic.Builder()
                    .setTitle(result.title)
                    .setDescription(result.description)
                    .setThumbnail(mapImage(result.thumbnail))
                    .setImages(mapImages(result.images))
                    .build();
            comics.add(comic);
        }
        return comics;
    }

    private List<String> mapImages(List<ComicDataResultImageEndpointResponse> imageEndpointResponses) {
        List<String> images = new ArrayList<>();
        for (ComicDataResultImageEndpointResponse imageEndpointResponse: imageEndpointResponses) {
            images.add(mapImage(imageEndpointResponse));
        }
        return images;
    }

    private String mapImage(ComicDataResultImageEndpointResponse imageEndpointResponse) {
        return imageUriTransformer.transform(imageEndpointResponse.path,
                imageEndpointResponse.extension);

    }
}
