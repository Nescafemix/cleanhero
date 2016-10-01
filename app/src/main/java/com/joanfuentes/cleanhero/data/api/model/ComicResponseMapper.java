package com.joanfuentes.cleanhero.data.api.model;

import com.joanfuentes.cleanhero.data.api.endpoint.model.ComicDataResultEndpointResponse;
import com.joanfuentes.cleanhero.data.api.endpoint.model.ComicDataResultImageEndpointResponse;
import com.joanfuentes.cleanhero.data.api.transformer.ImageUriTransformer;
import com.joanfuentes.cleanhero.domain.model.ComicDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ComicResponseMapper {

    private ImageUriTransformer imageUriTransformer;

    @Inject
    public ComicResponseMapper(ImageUriTransformer imageUriTransformer){
        this.imageUriTransformer = imageUriTransformer;
    }

    public List<ComicDTO> map(List<ComicDataResultEndpointResponse> response) {
        List<ComicDTO> comics = new ArrayList<>();
        for (ComicDataResultEndpointResponse result: response) {
            ComicDTO comic = new ComicDTO.Builder()
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
