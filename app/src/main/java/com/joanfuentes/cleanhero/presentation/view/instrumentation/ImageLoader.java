package com.joanfuentes.cleanhero.presentation.view.instrumentation;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.transcode.BitmapToGlideDrawableTranscoder;

import javax.inject.Inject;

public class ImageLoader {
    private static final int NO_PLACE_HOLDER = -1;

    @Inject
    public ImageLoader() {}

    public void load(String url, Context context, ImageView imageView) {
        load(url, context, imageView, NO_PLACE_HOLDER);
    }

    public void load(String url, Context context, ImageView imageView, int placeHolderResource) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(getBitmapRequestForThumbnail(url, context, placeHolderResource))
                .into(imageView);
    }

    private BitmapRequestBuilder<String, GlideDrawable> getBitmapRequestForThumbnail(String url,
                                                                                     Context context,
                                                                                     int placeHolderResource) {
        BitmapRequestBuilder<String, GlideDrawable> bitmapRequestBuilder =
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .transcode(new BitmapToGlideDrawableTranscoder(context), GlideDrawable.class)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
        if (placeHolderResource != NO_PLACE_HOLDER) {
            bitmapRequestBuilder.placeholder(placeHolderResource);
        }
        return bitmapRequestBuilder;
    }
}
