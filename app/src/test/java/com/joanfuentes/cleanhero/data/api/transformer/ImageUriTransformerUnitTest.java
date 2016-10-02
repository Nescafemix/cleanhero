package com.joanfuentes.cleanhero.data.api.transformer;

import com.joanfuentes.cleanhero.UnitTest;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class ImageUriTransformerUnitTest extends UnitTest {
    private final static String IMAGE_URI_WITHOUT_EXTENSION = "https://pbs.twimg.com/profile_images/2196820151/CE_cap1_TW";
    private final static String IMAGE_EXTENSION = "https://pbs.twimg.com/profile_images/2196820151/CE_cap1_TW";
    private final static String IMAGE_URI = IMAGE_URI_WITHOUT_EXTENSION + "." + IMAGE_EXTENSION;
    private ImageUriTransformer transformer;

    @Override
    protected void onSetup() {
        transformer = new ImageUriTransformer();
    }

    @Test
    public void shouldTransformImageUri() {
        final String expectedValue = IMAGE_URI;

        final String transformationResult = transformer
                .transform(IMAGE_URI_WITHOUT_EXTENSION, IMAGE_EXTENSION);

        assertNotNull(transformationResult);
        assertEquals(expectedValue, transformationResult);
    }
}
