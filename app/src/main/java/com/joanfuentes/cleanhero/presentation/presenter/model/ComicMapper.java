package com.joanfuentes.cleanhero.presentation.presenter.model;

import com.joanfuentes.cleanhero.domain.model.ComicDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ComicMapper {
    @Inject
    public ComicMapper(){}

    public List<ComicMVO> map(List<ComicDTO> comicDTOs) {
        List<ComicMVO> comics = new ArrayList<>();
        for (ComicDTO comicDTO: comicDTOs) {
            ComicMVO comic = new ComicMVO.Builder()
                    .setTitle(comicDTO.getTitle())
                    .setDescription(comicDTO.getDescription())
                    .setThumbnail(comicDTO.getThumbnail())
                    .setImages(comicDTO.getImages())
                    .build();
            comics.add(comic);
        }
        return comics;
    }
}
