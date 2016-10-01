package com.joanfuentes.cleanhero.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.joanfuentes.cleanhero.Application;
import com.joanfuentes.cleanhero.R;
import com.joanfuentes.cleanhero.presentation.presenter.model.ComicMVO;
import com.joanfuentes.cleanhero.presentation.presenter.DashboardPresenter;
import com.joanfuentes.cleanhero.presentation.view.instrumentation.ImageLoader;
import com.joanfuentes.cleanhero.presentation.view.internal.di.DaggerRuntimeActivityComponent;
import com.joanfuentes.cleanhero.presentation.view.internal.di.RuntimeActivityModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ItemListActivity extends BaseActivity {

    private boolean twoPaneMode;
    private SimpleItemRecyclerViewAdapter recyclerViewAdapter;
    private List<ComicMVO> comics;

    @Inject DashboardPresenter presenter;
    @Inject ImageLoader imageLoader;

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
        setTolbar();
        setFloatingActionButton();
        if (findViewById(R.id.item_detail_container) != null) {
            twoPaneMode = true;
        }
        presenter.onStart();
    }

    private void setTolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    private void setFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    private void setupFirstTimeRecyclerView(List<ComicMVO> data) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_list);
        int numberOfColumns = getResources().getInteger(R.integer.gridview_columns_number);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new SimpleItemRecyclerViewAdapter(data);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void renderComics(List<ComicMVO> comics) {
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
        Snackbar.make(this.getWindow().getDecorView(),
                "There was an error. Check your connectivity", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public Activity getActivity() {
        return this;
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<ComicMVO> mValues;

        SimpleItemRecyclerViewAdapter(List<ComicMVO> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.title.setText(holder.mItem.getTitle());
            imageLoader.load(holder.mItem.getThumbnail(), holder.mView.getContext(), holder.thumbnail);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (twoPaneMode) {
                        loadDetailFragment(holder);
                    } else {
                        navigateToDetail(view.getContext(), holder);
                    }
                }
            });
        }

        private void navigateToDetail(Context context, ViewHolder holder) {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra(ItemDetailFragment.ARG_COMIC, holder.mItem);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.thumbnail.setTransitionName(holder.title.getText().toString());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), holder.thumbnail, holder.title.getText().toString());
                context.startActivity(intent,options.toBundle());
            } else {
                context.startActivity(intent);
            }
        }

        private void loadDetailFragment(ViewHolder holder) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(ItemDetailFragment.ARG_COMIC, holder.mItem);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView title;
            final ImageView thumbnail;
            ComicMVO mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                title = (TextView) view.findViewById(R.id.title);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + title.getText() + "'";
            }
        }
    }
}
