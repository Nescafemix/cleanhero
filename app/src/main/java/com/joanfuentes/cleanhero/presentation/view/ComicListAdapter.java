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

class ComicListAdapter extends RecyclerView.Adapter<ComicListAdapter.ViewHolder> {

    private final List<Comic> comics;
    private Callback onItemClickListener;
    private ImageLoader imageLoader;

    ComicListAdapter(List<Comic> comics, ImageLoader imageLoader) {
        this.comics = comics;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.comic = comics.get(position);
        holder.title.setText(holder.comic.getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(holder.comic, holder.thumbnail);
                }
            }
        });
        imageLoader.load(holder.comic.getThumbnail(), holder.view.getContext(), holder.thumbnail);
    }

    void setOnItemClickListener(Callback callback) {
        this.onItemClickListener = callback;
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView title;
        final ImageView thumbnail;
        Comic comic;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }

    interface Callback {
        void onClick(Comic comic, ImageView imageView);
    }
}