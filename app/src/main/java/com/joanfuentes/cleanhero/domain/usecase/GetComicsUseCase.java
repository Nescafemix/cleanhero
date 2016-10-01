package com.joanfuentes.cleanhero.domain.usecase;

import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.data.ComicRepository;
import com.joanfuentes.cleanhero.threading.PostExecutionThread;
import com.joanfuentes.cleanhero.threading.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

public class GetComicsUseCase implements Runnable {
    private final ThreadExecutor executor;
    private final PostExecutionThread postExecution;
    private final ComicRepository repository;
    private Callback callback;

    @Inject
    public GetComicsUseCase(ThreadExecutor executor,
                            PostExecutionThread postExecution,
                            ComicRepository repository) {
        this.executor = executor;
        this.postExecution = postExecution;
        this.repository = repository;
    }

    @Override
    public void run() {
        //TODO: getChatacterId from SharedPreferences (we should let the user select the character)
        //meanwhile hardcoded
        long characterID = 1009220;
        repository.getComics(characterID, new ComicRepository.Callback() {
            @Override
            public void onSuccess(final List<Comic> comics) {
                notifyOnSuccess(comics);
            }

            @Override
            public void onError() {
                notifyOnError();
            }
        });
    }

    public void execute(final Callback callback) {
        this.callback = callback;
        this.executor.execute(this);
    }

    public interface Callback {
        void onComicsReady(List<Comic> comics);
        void onError();
    }

    private void notifyOnSuccess(final List<Comic> comics) {
        postExecution.post(new Runnable() {
            @Override
            public void run() {
                callback.onComicsReady(comics);
            }
        });
    }

    protected void notifyOnError() {
        postExecution.post(new Runnable() {
            @Override
            public void run() {
                callback.onError();
            }
        });
    }
}
