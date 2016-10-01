package com.joanfuentes.cleanhero.presentation.view.internal.di;

import com.joanfuentes.cleanhero.presentation.view.ItemDetailFragment;
import com.joanfuentes.cleanhero.presentation.view.ItemListActivity;

public interface ActivityComponent {
        void inject(ItemListActivity activity);
        void inject(ItemDetailFragment itemDetailFragment);
}
