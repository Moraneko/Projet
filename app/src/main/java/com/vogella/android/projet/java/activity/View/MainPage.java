package com.vogella.android.projet.java.activity.View;

        import android.content.Intent;

        import com.google.android.material.navigation.NavigationView;

        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentTransaction;
        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Toast;

        import com.vogella.android.projet.R;
        import com.vogella.android.projet.java.activity.Controler.MainPageControler;
        import com.vogella.android.projet.java.activity.Model.Anime_info;

        import java.util.ArrayList;
        import java.util.List;

public class MainPage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MainPageControler mainControler;
    ListContentFragment fragList;
    MyProfileFragment fragProfile;
    QuitFragment fragQuit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        String userName = getIntent().getStringExtra("userName");
        mainControler = new MainPageControler(findViewById(R.id.main_content),userName);
        // Adding Drawer to Main Screen
        initNavDrawer();
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        Fragment frag = fragList;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit();
    }
    private void initNavDrawer() {
        fragList = new ListContentFragment(mainControler);
        fragProfile = new MyProfileFragment();
        fragQuit = new QuitFragment();
        drawerLayout = findViewById(R.id.drawer_layout_id);
        NavigationView navView = findViewById(R.id.navigation_id);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment frag = null;
                int id = menuItem.getItemId();
                if (id == R.id.anime) {
                    frag = fragList;
                } else if (id == R.id.perso) {
                    frag = fragProfile;
                } else if (id == R.id.quit) {
                    frag = fragQuit;
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
        mainControler.clickAnime(v.getTag(), v);
    }
    public void favorisClick(View v) {
        mainControler.clickFavoris(v.getTag(), v);
    }
}
