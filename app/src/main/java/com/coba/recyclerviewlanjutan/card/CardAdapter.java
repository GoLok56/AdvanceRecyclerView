package com.coba.recyclerviewlanjutan.card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coba.recyclerviewlanjutan.R;
import com.coba.recyclerviewlanjutan.carddetail.CardDetailActivity;
import com.coba.recyclerviewlanjutan.model.CardModel;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<CardModel> cards;

    public CardAdapter(Context context, List<CardModel> cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, null);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardModel card = cards.get(position);

        Glide.with(context).load(card.getImageUrl()).into(((CardViewHolder) holder).imageViewPoster);
        ((CardViewHolder) holder).textViewTitle.setText(card.getName());
        ((CardViewHolder) holder).textViewRarity.setText(card.getRarity());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewRarity;

        CardViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.card_imageview_poster);
            textViewTitle = itemView.findViewById(R.id.card_textview_title);
            textViewRarity = itemView.findViewById(R.id.card_textview_rarity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, CardDetailActivity.class);
            intent.putExtra("name", cards.get(getAdapterPosition()).getName());
            intent.putExtra("series", cards.get(getAdapterPosition()).getSeries());
            intent.putExtra("rarity", cards.get(getAdapterPosition()).getRarity());
            intent.putExtra("poster", cards.get(getAdapterPosition()).getImageUrl());
            context.startActivity(intent);
        }
    }
}

