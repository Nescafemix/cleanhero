package com.joanfuentes.cleanhero.data.datasource;

import com.joanfuentes.cleanhero.UnitTest;
import com.joanfuentes.cleanhero.data.api.ComicApi;
import com.joanfuentes.cleanhero.data.api.endpoint.model.ComicEndpointResponse;
import com.joanfuentes.cleanhero.data.api.model.ComicResponseMapper;
import com.joanfuentes.cleanhero.domain.model.Comic;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class ComicCloudDataSourceUnitTest extends UnitTest {
    private final static long CHARACTER_ID = 1;
    private ComicCloudDataSource datasource;
    @Mock ComicApi comicApiMock;
    @Mock ComicEndpointResponse comicEndpointResponse;
    @Mock List<Comic> comicsMock;

    @Override
    protected void onSetup() {
        datasource = new ComicCloudDataSource(comicApiMock);
    }

    @Test
    public void shouldGetComics() {
        doReturn(comicsMock).when(comicApiMock).getComics(eq(CHARACTER_ID));

        datasource.getComics(CHARACTER_ID);

        verify(comicApiMock).getComics(eq(CHARACTER_ID));
    }
}
