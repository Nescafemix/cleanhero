package com.joanfuentes.cleanhero.data.api.generator;

import com.joanfuentes.cleanhero.UnitTest;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class HashGeneratorUnitTest extends UnitTest {
    private final static String TIMESTAMP = "1";
    private final static String PRIVATE_KEY = "12345";
    private final static String PUBLIC_KEY = "678910";
    private final static String HASH = "2b7c0e435a43e9c7453c8063b1ac2358";
    private HashGenerator hashGenerator;

    @Override
    protected void onSetup() {
        hashGenerator = new HashGenerator();
    }

    @Test
    public void shouldTransformImageUri() {
        final String expectedValue = HASH;

        final String generationResult = hashGenerator.getHash(TIMESTAMP, PRIVATE_KEY, PUBLIC_KEY);

        assertNotNull(generationResult);
        assertEquals(expectedValue, generationResult);
    }
}
