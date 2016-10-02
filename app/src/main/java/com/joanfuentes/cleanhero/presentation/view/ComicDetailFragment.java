package com.joanfuentes.cleanhero.presentation.view;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanfuentes.cleanhero.Application;
import com.joanfuentes.cleanhero.R;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.presentation.view.instrumentation.ImageLoader;
import com.joanfuentes.cleanhero.presentation.view.internal.di.DaggerRuntimeActivityComponent;
import com.joanfuentes.cleanhero.presentation.view.internal.di.RuntimeActivityModule;

import javax.inject.Inject;

public class ComicDetailFragment extends BaseFragment {
    public static final String ARG_COMIC = "comic";
    private Comic comic;

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
    void onInitializeInjection() {
        DaggerRuntimeActivityComponent
                .builder()
                .applicationComponent(Application.getInstance().getApplicationComponent())
                .runtimeActivityModule(new RuntimeActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureAppBarLayout();
    }

    private void configureAppBarLayout() {
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) this.getActivity().findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(comic.getTitle());
            ImageView imageToolbar = (ImageView)this.getActivity().findViewById(R.id.image_toolbar);
            if (comic.containImages()) {
                imageLoader.load(comic.getRandomImage(), this.getContext(), imageToolbar);
            } else {
                imageLoader.load(comic.getThumbnail(), this.getContext(), imageToolbar);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        if (comic != null) {
            setViews(rootView);
        }
        return rootView;
    }

    private void setViews(View rootView) {
        ((TextView) rootView.findViewById(R.id.description)).setText(comic.getDescription());
        ImageView thumbnail = (ImageView) rootView.findViewById(R.id.image_thumbnail);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            thumbnail.setTransitionName(comic.getTitle());
        }
        imageLoader.load(comic.getThumbnail(), this.getContext(), thumbnail);
    }
}