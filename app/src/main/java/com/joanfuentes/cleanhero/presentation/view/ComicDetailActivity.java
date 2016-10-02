package com.joanfuentes.cleanhero.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.joanfuentes.cleanhero.R;

public class ComicDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            addFragmentToTheActivity();
        }
    }

    @Override
    int onRequestLayout() {
        return R.layout.activity_comic_detail;
    }

    @Override
    void onInitializeInjection() {}

    @Override
    void onViewReady() {}

    private void addFragmentToTheActivity() {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ComicDetailFragment.ARG_COMIC,
                getIntent().getSerializableExtra(ComicDetailFragment.ARG_COMIC));
        ComicDetailFragment fragment = new ComicDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ComicListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
