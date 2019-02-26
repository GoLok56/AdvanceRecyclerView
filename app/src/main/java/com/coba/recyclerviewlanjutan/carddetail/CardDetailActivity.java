package com.coba.recyclerviewlanjutan.carddetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coba.recyclerviewlanjutan.R;

public class CardDetailActivity extends AppCompatActivity {
    ImageView imageViewPoster;
    TextView textViewName;
    TextView textViewSeries;
    TextView textViewRarity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        imageViewPoster = findViewById(R.id.carddetail_imageview_poster);
        textViewName = findViewById(R.id.carddetail_textview_title);
        textViewSeries = findViewById(R.id.carddetail_textview_series);
        textViewRarity = findViewById(R.id.carddetail_textview_rarity);

        try {
            Intent intent = getIntent();
            Glide.with(this).load(intent.getStringExtra("poster")).into(imageViewPoster);
            textViewName.setText(intent.getStringExtra("name"));
            textViewSeries.setText(intent.getStringExtra("series"));
            textViewRarity.setText(intent.getStringExtra("rarity"));
        } catch (Exception e) {
            Log.d("Error", "Error Message : " + e.getMessage());
        }
    }
}