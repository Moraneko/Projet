package com.vogella.android.projet.java.activity.View;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.Model.JikkanAPI;
import com.vogella.android.projet.java.activity.Controler.MyAdapter;
import com.vogella.android.projet.java.activity.Model.Anime_info;
import com.vogella.android.projet.java.activity.Model.ReponseAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Integer page =1;
    private JikkanAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JikkanAPI animeApi = retrofit.create(JikkanAPI.class);
        this.api = animeApi;
        appelApi();

        NestedScrollView scrollView = findViewById(R.id.nestedScroll);
        Button next = findViewById(R.id.PreviousButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(page>1){
                    page--;
                    appelApi();
                    scrollView.smoothScrollTo(0,0);
                }
            }
        });
        Button previous = findViewById(R.id.NextButton);
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                page++;
                appelApi();
                scrollView.smoothScrollTo(0,0);
            }
        });
    }

    private void appelApi() {
        Call<ReponseAPI> call = this.api.getListAnime(this.page.toString());
        call.enqueue(new Callback<ReponseAPI>() {
            @Override
            public void onResponse(Call<ReponseAPI> call, Response<ReponseAPI> response) {
                ReponseAPI restAnime = response.body();
                List<Anime_info> listAnime = restAnime.getResult();
                showList(listAnime);
            }

            @Override
            public void onFailure(Call<ReponseAPI> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }

    private MyAdapter.OnItemClickListener getListener () {
        return new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Anime_info item) {
                Intent i = new Intent(getBaseContext(), InfoActivity.class);
                i.putExtra("key", item.getMal_id());
                startActivity(i);
            }
        };
    }

    public void showList(List<Anime_info> input){
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(input , getListener());
        recyclerView.setAdapter(mAdapter);
    }
}
