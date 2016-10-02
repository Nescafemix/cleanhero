package com.joanfuentes.cleanhero.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanfuentes.cleanhero.R;
import com.joanfuentes.cleanhero.domain.model.Comic;
import com.joanfuentes.cleanhero.presentation.view.instrumentation.ImageLoader;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class ComicListAdapter extends RecyclerView.Adapter<ComicListAdapter.ViewHolder> {

    private List<Comic> comics;
    private Callback onItemClickListener;
    private ImageLoader imageLoader;

    @Inject
    public ComicListAdapter(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comic_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.comic = comics.get(position);
        holder.title.setText(holder.comic.getTitle());
        imageLoader.load(holder.comic.getThumbnail(), holder.itemView.getContext(), holder.thumbnail);
    }

    void setOnItemClickListener(Callback callback) {
        this.onItemClickListener = callback;
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public void setData(List<Comic> comics) {
        this.comics = comics;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title) TextView title;
        @BindView(R.id.thumbnail) ImageView thumbnail;
        private Comic comic;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }

        @OnClick()
        public void clickedFabButton(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(comic, thumbnail);
            }
        }
    }

    interface Callback {
        void onClick(Comic comic, ImageView imageView);
    }
}