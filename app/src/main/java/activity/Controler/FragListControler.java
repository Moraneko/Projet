package activity.Controler;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.projet.R;
import activity.Model.Anime_info;
import activity.Model.JikkanAPI;
import activity.Model.ReponseAPI;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragListControler  {
    private JikkanAPI api;
    private MainPageControler mainContoler;
    private LayoutInflater inflater;
    private ViewGroup container;
    private  Bundle savedInstanceState;
    private RecyclerView recyclerView;
    private Integer page = 1;
    private FragmentActivity activity;

    public FragListControler(MainPageControler mainControler, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, FragmentActivity activity) {
        this.mainContoler = mainControler;
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        this.activity = activity;
    }
    public View initView() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        JikkanAPI animeApi = retrofit.create(JikkanAPI.class);
        this.api = animeApi;
        View retView = inflater.inflate(R.layout.fragment_list_anime, container, false);
        recyclerView = retView.findViewById(R.id.my_recycler_view);
        TextView headerText = retView.findViewById(R.id.headerText);
        headerText.setText("Bienvenue " + mainContoler.getName());
        Button next = retView.findViewById(R.id.suivant);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                appelApi();
            }
        });
        Button prec = retView.findViewById(R.id.precedent);
        prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page>1){
                    page--;
                    appelApi();
                }
            }
        });
        appelApi();
        return retView;
    }
    private View appelApi() {
        Call<ReponseAPI> call = this.api.getListAnime(this.page.toString());
        call.enqueue(new Callback<ReponseAPI>() {
            @Override
            public void onResponse(Call<ReponseAPI> call, Response<ReponseAPI> response) {
                if( response.body() != null) {
                    ReponseAPI restAnime = response.body();
                    List<Anime_info> listAnime = restAnime.getResult();
                    mainContoler.setDataFromApi(listAnime);
                    RecyclerAdapter adapter = new RecyclerAdapter(recyclerView.getContext(), mainContoler.getDataFromApi(), mainContoler.getFavorisList());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                }
            }
            @Override
            public void onFailure(Call<ReponseAPI> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
        return recyclerView;
    }
}
