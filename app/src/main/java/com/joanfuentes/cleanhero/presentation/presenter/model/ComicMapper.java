package com.joanfuentes.cleanhero.presentation.presenter.model;

import com.joanfuentes.cleanhero.domain.model.Comic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ComicMapper {
    @Inject
    public ComicMapper(){}

    public List<ComicMVO> map(List<Comic> comics) {
        List<ComicMVO> comicMVOs = new ArrayList<>();
        for (Comic comic: comics) {
            ComicMVO comicMVO = new ComicMVO.Builder()
                    .setTitle(comic.getTitle())
                    .setDescription(comic.getDescription())
                    .setThumbnail(comic.getThumbnail())
                    .setImages(comic.getImages())
                    .build();
            comicMVOs.add(comicMVO);
        }
        return comicMVOs;
    }
}
