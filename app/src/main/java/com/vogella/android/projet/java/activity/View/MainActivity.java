package com.vogella.android.projet.java.activity.View;

import android.content.Intent;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.Model.Anime_info;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Integer page =1;
    List<Anime_info> dataFromApi;
    private List<Anime_info> myFavorite;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adding Drawer to Main Screen
        initNavDrawer();
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        myFavorite = new ArrayList<>();

    }

    private void initNavDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout_id);
        NavigationView navView = findViewById(R.id.navigation_id);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment frag = null;
                int id = menuItem.getItemId();
                if (id == R.id.anime) {
                    frag = new ListContentFragment();
                } else if (id == R.id.perso) {
                    frag = new MyProfileFragment();
                } else if (id == R.id.quit) {
                    frag = new QuitFragment();
                }
                if (frag != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
                    transaction.commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                return false;
            }
        });
    }
    public void clickList(View v) {
        System.out.println(v.getTag());
    }
    public void favorisClick(View v) {
        System.out.println(v.getTag());
        Integer var = (Integer) v.getTag();
        Anime_info myFav;
        for(Anime_info i : dataFromApi) {
            if( i.getMal_id() == var){
                myFav = i;
                if(this.myFavorite.contains(myFav)){
                    this.myFavorite.remove(myFav); // Mettre l'effet bouton favoris
                }else{
                    this.myFavorite.add(myFav); // Mettre l'effet bouton favoris
                }
            }
        }
    }
    /*private void setupViewPager(ViewPager viewPager, List<Anime_info> input) {
        Bundle data = new Bundle();
        data.putString("Key1", new Gson().toJson(input));
        ListContentFragment fragList = new ListContentFragment();
        fragList.setArguments(data);
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(fragList, "List");
        viewPager.setAdapter(adapter);
    }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*/
}
