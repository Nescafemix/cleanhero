package com.joanfuentes.cleanhero.presentation.view;

import android.app.Activity;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanfuentes.cleanhero.Application;
import com.joanfuentes.cleanhero.R;
import com.joanfuentes.cleanhero.presentation.presenter.model.ComicMVO;
import com.joanfuentes.cleanhero.presentation.view.instrumentation.ImageLoader;
import com.joanfuentes.cleanhero.presentation.view.internal.di.DaggerRuntimeActivityComponent;
import com.joanfuentes.cleanhero.presentation.view.internal.di.RuntimeActivityModule;

import java.util.Random;

import javax.inject.Inject;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    @Inject ImageLoader imageLoader;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_COMIC = "comic";

    /**
     * The dummy content this fragment is presenting.
     */
    private ComicMVO comic;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
        DaggerRuntimeActivityComponent
                .builder()
                .applicationComponent(Application.getInstance().getApplicationComponent())
                .runtimeActivityModule(new RuntimeActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_COMIC)) {
            comic = (ComicMVO) getArguments().getSerializable(ARG_COMIC);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(comic.getTitle());
                ImageView imageToolbar = (ImageView)getActivity().findViewById(R.id.image_toolbar);
                imageLoader.load(comic.getImages().get(new Random().nextInt((comic.getImages().size()))), this.getContext(), imageToolbar);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (comic != null) {
            ((TextView) rootView.findViewById(R.id.description)).setText(comic.getDescription());
            ImageView thumbnail = (ImageView) rootView.findViewById(R.id.image_thumbnail);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                thumbnail.setTransitionName(comic.getTitle());
            }
            imageLoader.load(comic.getThumbnail(), this.getContext(), thumbnail);

        }

        return rootView;
    }
}
