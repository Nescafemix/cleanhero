package com.joanfuentes.cleanhero.data.api.transformer;

import javax.inject.Inject;

public class ImageUriTransformer {
    public static final String IMAGE_URI_PATTERN = "%s.%s";

    @Inject
    public ImageUriTransformer() { }

    public String transform(String urlWithoutExtension, String extension) {
        return String.format(IMAGE_URI_PATTERN, urlWithoutExtension, extension);
    }
}
