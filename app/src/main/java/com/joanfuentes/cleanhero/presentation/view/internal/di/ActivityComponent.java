package com.joanfuentes.cleanhero.presentation.view.internal.di;

import com.joanfuentes.cleanhero.presentation.view.ComicDetailFragment;
import com.joanfuentes.cleanhero.presentation.view.ComicListActivity;

public interface ActivityComponent {
        void inject(ComicListActivity activity);
        void inject(ComicDetailFragment comicDetailFragment);
}
