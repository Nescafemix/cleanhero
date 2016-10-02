package com.joanfuentes.cleanhero.data;

import com.joanfuentes.cleanhero.UnitTest;
import com.joanfuentes.cleanhero.data.datasource.ComicCloudDataSource;
import com.joanfuentes.cleanhero.domain.model.Comic;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class ComicRepositoryUnitTest extends UnitTest {
    private final static long CHARACTER_ID = 1;
    private ComicRepository comicRepository;

    @Mock ComicRepository.Callback comicListCallbackMock;
    @Mock ComicCloudDataSource comicCloudDataSource;
    @Mock List<Comic> comicsMock;

    @Override
    protected void onSetup() {
        comicRepository = new ComicRepository(comicCloudDataSource);
    }

    @Test
    public void shouldGetComics() {
        doReturn(comicsMock).when(comicCloudDataSource).getComics(eq(CHARACTER_ID));

        comicRepository.getComics(CHARACTER_ID, comicListCallbackMock);

        verify(comicCloudDataSource).getComics(eq(CHARACTER_ID));
        verify(comicListCallbackMock).onSuccess(eq(comicsMock));
    }
}
