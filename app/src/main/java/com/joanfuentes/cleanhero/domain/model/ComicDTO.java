package com.joanfuentes.cleanhero.domain.model;

import java.util.List;

public class ComicDTO {
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
        private ComicDTO comicList;

        public Builder() {
            this.comicList = new ComicDTO();
        }

        public Builder setTitle(String title) {
            comicList.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            comicList.description = description;
            return this;
        }

        public Builder setThumbnail(String thumbnail) {
            comicList.thumbnail = thumbnail;
            return this;
        }

        public Builder setImages(List<String> images) {
            comicList.images = images;
            return this;
        }

        public ComicDTO build() {
            return comicList;
        }
    }
}
