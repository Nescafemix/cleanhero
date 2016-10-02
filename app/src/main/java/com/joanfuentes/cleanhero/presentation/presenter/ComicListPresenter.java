package com.joanfuentes.cleanhero.presentation.presenter;

import com.joanfuentes.cleanhero.presentation.view.ComicListActivity;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.domain.usecase.GetComicsUseCase;

import java.util.List;

import javax.inject.Inject;

public class ComicListPresenter extends BasePresenter {
    private final ComicListActivity activity;
    private final GetComicsUseCase usecase;

    @Inject
    public ComicListPresenter(ComicListActivity activity, GetComicsUseCase usecase) {
        this.activity = activity;
        this.usecase = usecase;
    }

    @Override
    public void onStart() {
        executeUseCase();
    }

    private void executeUseCase() {
        usecase.execute(new GetComicsUseCase.Callback() {
            @Override
            public void onComicsReady(List<Comic> comics) {
                activity.renderComics(comics);
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
