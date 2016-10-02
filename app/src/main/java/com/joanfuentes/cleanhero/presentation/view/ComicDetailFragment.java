package com.joanfuentes.cleanhero.presentation.view;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanfuentes.cleanhero.Application;
import com.joanfuentes.cleanhero.R;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.presentation.view.instrumentation.ImageLoader;
import com.joanfuentes.cleanhero.presentation.view.internal.di.DaggerRuntimeActivityComponent;
import com.joanfuentes.cleanhero.presentation.view.internal.di.RuntimeActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ComicDetailFragment extends BaseFragment {
    public static final String ARG_COMIC = "comic";
    private Comic comic;
    @BindView(R.id.detail_toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.image_toolbar) ImageView imageViewInToolbar;
    @BindView(R.id.image_thumbnail) ImageView thumbnailImageView;
    @BindView(R.id.description) TextView descriptionTextView;
    @Inject ImageLoader imageLoader;

    public ComicDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_COMIC)) {
            comic = (Comic) getArguments().getSerializable(ARG_COMIC);
        }
    }

    @Override
    int onRequestLayout() {
        return R.layout.fragment_comic_detail;
    }

    @Override
    void onInitializeInjection() {
        DaggerRuntimeActivityComponent
                .builder()
                .applicationComponent(((Application)getActivity().getApplication()).getApplicationComponent())
                .runtimeActivityModule(new RuntimeActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    void onViewReady() {
        setToolbar();
        if (comic != null) {
            setDescription();
            setThumbnail();
        }
    }

    private void setToolbar() {
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        showUpButtonInActionBar();
    }

    private void showUpButtonInActionBar() {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setDescription() {
        descriptionTextView.setText(comic.getDescription());
    }

    private void setThumbnail() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            thumbnailImageView.setTransitionName(comic.getTitle());
        }
        imageLoader.load(comic.getThumbnail(), this.getContext(), thumbnailImageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureAppBarLayout();
    }

    private void configureAppBarLayout() {
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(comic.getTitle());
            if (comic.containImages()) {
                imageLoader.load(comic.getRandomImage(), this.getContext(), imageViewInToolbar);
            } else {
                imageLoader.load(comic.getThumbnail(), this.getContext(), imageViewInToolbar);
            }
        }
    }

    @OnClick(R.id.fab)
    public void clickedFabButton(View view) {
        Snackbar.make(view, R.string.captain_america_was_called, Snackbar.LENGTH_LONG)
                .show();
    }
}
