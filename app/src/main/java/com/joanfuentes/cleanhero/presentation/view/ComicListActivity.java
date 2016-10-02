package com.joanfuentes.cleanhero.presentation.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;


import com.joanfuentes.cleanhero.Application;
import com.joanfuentes.cleanhero.R;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.presentation.presenter.ComicListPresenter;
import com.joanfuentes.cleanhero.presentation.view.instrumentation.ImageLoader;
import com.joanfuentes.cleanhero.presentation.view.internal.di.DaggerRuntimeActivityComponent;
import com.joanfuentes.cleanhero.presentation.view.internal.di.RuntimeActivityModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ComicListActivity extends BaseActivity {

    @Inject
    ComicListPresenter presenter;
    @Inject ImageLoader imageLoader;

    private boolean twoPaneMode;
    private ComicListAdapter recyclerViewAdapter;
    private List<Comic> comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        DaggerRuntimeActivityComponent
                .builder()
                .applicationComponent(((Application)getApplication()).getApplicationComponent())
                .runtimeActivityModule(new RuntimeActivityModule(this))
                .build()
                .inject(this);
        setToolbar();
        setFloatingActionButton();
        if (findViewById(R.id.item_detail_container) != null) {
            twoPaneMode = true;
        }
        presenter.onStart();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    private void setFloatingActionButton() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.you_just_gave_donation, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    private void setupFirstTimeRecyclerView(List<Comic> data) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_list);
        int numberOfColumns = getResources().getInteger(R.integer.gridview_columns_number);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new ComicListAdapter(data, imageLoader);
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
        if (recyclerViewAdapter == null) {
            this.comics = new ArrayList<>(comics);
            setupFirstTimeRecyclerView(this.comics);
        } else {
            this.comics.clear();
            this.comics.addAll(comics);
            updateDataOnRecyclerView();
        }
    }

    private void updateDataOnRecyclerView() {
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void renderError() {
        Snackbar.make(this.getWindow().getDecorView(), R.string.error_check_connectivity,
                Snackbar.LENGTH_LONG)
                .show();
    }
}
