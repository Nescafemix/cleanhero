package com.joanfuentes.cleanhero.presentation.presenter;

import com.joanfuentes.cleanhero.presentation.presenter.model.ComicMapper;
import com.joanfuentes.cleanhero.presentation.view.ItemListActivity;
import com.joanfuentes.cleanhero.domain.model.ComicDTO;
import com.joanfuentes.cleanhero.domain.usecase.GetComicsUseCase;

import java.util.List;

import javax.inject.Inject;

public class DashboardPresenter extends BasePresenter {
    private final ItemListActivity activity;
    private final GetComicsUseCase usecase;
    private final ComicMapper mapper;

    @Inject
    public DashboardPresenter(ItemListActivity activity, GetComicsUseCase usecase, ComicMapper mapper) {
        this.activity = activity;
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @Override
    public void onStart() {
        usecase.execute(new GetComicsUseCase.Callback() {
            @Override
            public void onComicsReady(List<ComicDTO> comics) {
                activity.renderComics(mapper.map(comics));
            }

            @Override
            public void onError() {
                activity.renderError();
            }
        });
    }

    @Override
    public void onStop() { }
}
