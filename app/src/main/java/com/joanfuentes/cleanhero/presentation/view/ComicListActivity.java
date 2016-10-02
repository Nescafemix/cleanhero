package com.joanfuentes.cleanhero.presentation.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.joanfuentes.cleanhero.Application;
import com.joanfuentes.cleanhero.R;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.presentation.presenter.ComicListPresenter;
import com.joanfuentes.cleanhero.presentation.view.internal.di.DaggerRuntimeActivityComponent;
import com.joanfuentes.cleanhero.presentation.view.internal.di.RuntimeActivityModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ComicListActivity extends BaseActivity {
    @Nullable @BindView(R.id.item_detail_container) View containerView;
    @BindView(R.id.swipe_container) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.initial_progressbar) ContentLoadingProgressBar contentLoadingProgressBar;
    @BindView(R.id.item_list) RecyclerView recyclerView;
    @BindView(R.id.error) RelativeLayout errorRelativeLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private boolean twoPaneMode;
    private List<Comic> comics;

    @Inject ComicListPresenter presenter;
    @Inject ComicListAdapter recyclerViewAdapter;

    @Override
    int onRequestLayout() {
        return R.layout.activity_comic_list;
    }

    @Override
    void onInitializeInjection() {
        DaggerRuntimeActivityComponent
                .builder()
                .applicationComponent(((Application)getApplication()).getApplicationComponent())
                .runtimeActivityModule(new RuntimeActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    void onViewReady() {
        setToolbar();
        setSwipe2Refresh();
        if (containerView != null) {
            twoPaneMode = true;
        }
        presenter.onStart();
    }

    private void setSwipe2Refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onStart();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @OnClick(R.id.fab)
    public void clickedFabButton(View view) {
        Snackbar.make(view, R.string.you_just_gave_donation, Snackbar.LENGTH_LONG).show();
    }

    private void setupFirstTimeRecyclerView(List<Comic> data) {
        int numberOfColumns = getResources().getInteger(R.integer.gridview_columns_number);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter.setData(data);
        recyclerViewAdapter.setOnItemClickListener(new ComicListAdapter.Callback() {
            @Override
            public void onClick(Comic comic, ImageView imageView) {
                if (twoPaneMode) {
                    loadDetailFragment(comic);
                } else {
                    navigateToDetail(comic, imageView);
                }
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void navigateToDetail(Comic comic, ImageView imageView) {
        Intent intent = new Intent(this, ComicDetailActivity.class);
        intent.putExtra(ComicDetailFragment.ARG_COMIC, comic);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(comic.getTitle());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, imageView, comic.getTitle());
            this.startActivity(intent,options.toBundle());
        } else {
            this.startActivity(intent);
        }
    }

    private void loadDetailFragment(Comic comic) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ComicDetailFragment.ARG_COMIC, comic);
        ComicDetailFragment fragment = new ComicDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit();
    }

    public void renderComics(List<Comic> comics) {
        showList();
        if (recyclerView.getAdapter() == null) {
            this.comics = new ArrayList<>(comics);
            setupFirstTimeRecyclerView(this.comics);
        } else {
            this.comics.clear();
            this.comics.addAll(comics);
            updateDataOnRecyclerView();
        }
    }

    private void showList() {
        contentLoadingProgressBar.setVisibility(View.GONE);
        errorRelativeLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void showError() {
        contentLoadingProgressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorRelativeLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void updateDataOnRecyclerView() {
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void renderError() {
        showError();
    }
}
