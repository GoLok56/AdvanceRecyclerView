package com.coba.recyclerviewlanjutan.card;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coba.recyclerviewlanjutan.R;
import com.coba.recyclerviewlanjutan.model.CardModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client;
    SwipeRefreshLayout swipeRefreshCards;

    RecyclerView recyclerViewCards;
    LinearLayoutManager linearLayoutManager;
    CardAdapter cardAdapter;
    ArrayList<CardModel> cards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();

        setupSwipeRefresh();
        setupRecyclerView();
        populateCardData();
    }

    private void populateCardData() {
        swipeRefreshCards.setRefreshing(true);
        cards.clear();
        Request request = new Request.Builder()
                .url("https://api.pokemontcg.io/v1/cards")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshCards.setRefreshing(false);
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    final CardModel.CardResultModel result = gson.fromJson(response.body().string(), CardModel.CardResultModel.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshCards.setRefreshing(false);
                            cards.addAll(result.getCards());
                            cardAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshCards.setRefreshing(false);
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void setupSwipeRefresh() {
        swipeRefreshCards = findViewById(R.id.main_swiperefresh_cards);
        swipeRefreshCards.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateCardData();
            }
        });
    }

    private void setupRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        recyclerViewCards = findViewById(R.id.main_recyclerview_cards);
        cardAdapter = new CardAdapter(this, cards);
        recyclerViewCards.setAdapter(cardAdapter);
        recyclerViewCards.setLayoutManager(linearLayoutManager);
        recyclerViewCards.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewCards.setItemAnimator(new DefaultItemAnimator());
    }
}
