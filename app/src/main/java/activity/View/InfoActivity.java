package activity.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.projet.R;
import activity.Controler.AdapterInfo;
import activity.Model.JikkanAPI;
import activity.Model.SingleInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        View view = findViewById(R.id.base);
        Integer info = getIntent().getIntExtra("key",0);
        Gson gson2 = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create(gson2))
                .build();

        JikkanAPI animeApi = retrofit2.create(JikkanAPI.class);

        Call<SingleInfo> call = animeApi.getAnime(info.toString());
        call.enqueue(new Callback<SingleInfo>() {
            @Override
            public void onResponse(Call<SingleInfo> call, Response<SingleInfo> response) {
                SingleInfo reponseAnime = response.body();
                AdapterInfo affichage = new AdapterInfo(view,reponseAnime);
                affichage.setLayout();
            }
            @Override
            public void onFailure(Call<SingleInfo> call, Throwable t) {
            }
        });

    }
}
