package com.joanfuentes.cleanhero.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.joanfuentes.cleanhero.R;

public class ItemDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            super.onCreate(savedInstanceState);
        }
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        setListeners();
        showUpButtonInActionBar();
        if (savedInstanceState == null) {
            addFragmentToTheActivity();
        } else {
            super.onCreate(savedInstanceState);
        }
    }

    private void setListeners() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Captain America was called... or was Ultron?...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void addFragmentToTheActivity() {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ItemDetailFragment.ARG_COMIC,
                getIntent().getSerializableExtra(ItemDetailFragment.ARG_COMIC));
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();
    }

    private void showUpButtonInActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
