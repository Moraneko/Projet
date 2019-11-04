package com.vogella.android.projet.java.activity.View;

import android.content.Intent;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.Model.JikkanAPI;
import com.vogella.android.projet.java.activity.Controler.MyAdapter;
import com.vogella.android.projet.java.activity.Model.Anime_info;
import com.vogella.android.projet.java.activity.Model.ReponseAPI;

import java.util.ArrayList;
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
    List<Anime_info> dataFromApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        JikkanAPI animeApi = retrofit.create(JikkanAPI.class);
        this.api = animeApi;
        appelApi();

        /*//Gestion des boutons
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
        });*/
    }

    private void affichageTabs() {
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, dataFromApi);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager, List<Anime_info> input) {
        Bundle data = new Bundle();
        data.putString("Key1", new Gson().toJson(input));
        ListContentFragment fragList = new ListContentFragment();
        fragList.setArguments(data);
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(fragList, "List");
        adapter.addFragment(new TileContentFragment(), "Tile");
        adapter.addFragment(new CardContentFragment(), "Card");
        viewPager.setAdapter(adapter);
    }
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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
        dataFromApi = input;
        affichageTabs();
        /*recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(input , getListener());
        recyclerView.setAdapter(mAdapter);*/
    }
}
