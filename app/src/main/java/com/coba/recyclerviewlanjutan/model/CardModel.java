package com.coba.recyclerviewlanjutan.model;

import java.util.List;

public class CardModel {
    private String id;
    private String name;
    private String imageUrl;
    private String rarity;
    private String series;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public class CardResultModel {
        private List<CardModel> cards;

        public List<CardModel> getCards() {
            return cards;
        }

        public void setCards(List<CardModel> cards) {
            this.cards = cards;
        }
    }
}
