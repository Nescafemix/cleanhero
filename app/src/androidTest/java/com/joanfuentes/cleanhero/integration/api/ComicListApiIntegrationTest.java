package com.joanfuentes.cleanhero.integration.api;

import com.joanfuentes.cleanhero.data.api.ComicApi;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.integration.IntegrationTest;
import com.joanfuentes.cleanhero.integration.internal.di.DaggerIntegrationTestComponent;

import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

public class ComicListApiIntegrationTest extends IntegrationTest {
    @Inject ComicApi api;
    private static long CAPTAIN_AMERICA_CHARACTER_ID = 1009220;

    @Override
    protected void onInitializeInjection() {
        DaggerIntegrationTestComponent.builder()
                .build()
                .inject(this);
    }

    @Test
    public void shouldGetComicList() {
        final List<Comic> comics = api.getComics(CAPTAIN_AMERICA_CHARACTER_ID);

        assertNotNull(comics);
        assertFalse(comics.isEmpty());
    }
}
