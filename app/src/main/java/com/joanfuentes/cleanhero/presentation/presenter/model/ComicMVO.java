package com.joanfuentes.cleanhero.presentation.presenter.model;

import java.io.Serializable;
import java.util.List;

public class ComicMVO implements Serializable {
    private String title;
    private String description;
    private String thumbnail;
    private List<String> images;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<String> getImages() {
        return images;
    }

    public static class Builder {
        private String title;
        private String description;
        private String thumbnail;
        private List<String> images;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder setImages(List<String> images) {
            this.images = images;
            return this;
        }

        public ComicMVO build() {
            ComicMVO comic = new ComicMVO();
            comic.title = title;
            comic.description = description;
            comic.thumbnail = thumbnail;
            comic.images = images;
            return comic;
        }
    }
}
