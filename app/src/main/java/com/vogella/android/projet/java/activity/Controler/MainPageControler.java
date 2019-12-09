package com.vogella.android.projet.java.activity.Controler;

import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.Model.Anime_info;
import com.vogella.android.projet.java.activity.Model.FavorisList;
import com.vogella.android.projet.java.activity.Model.JikkanAPI;
import com.vogella.android.projet.java.activity.Model.ReponseAPI;
import com.vogella.android.projet.java.activity.Model.SingleInfo;

import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPageControler {
    private View v;
    private String userName;
    private FavorisList favModel;
    private List<Anime_info> dataFromApi;
    private HashSet<Integer> waitingToAdd;
    public MainPageControler(View v,String name){
        this.userName = name;
        this.v = v;
        this.favModel = new FavorisList();
        this.waitingToAdd = new HashSet<>();

    }

    public void clickAnime(Object id, View v) {
        Integer animeId = Integer.parseInt(id.toString());
        System.out.println(animeId);
    }
    public void clickFavoris(Object id, View view) {
        Integer var = Integer.parseInt(id.toString());
        ImageButton button = view.findViewById(R.id.imageButton);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        JikkanAPI animeApi = retrofit.create(JikkanAPI.class);
        for(Anime_info i : dataFromApi) {
            if( i.getMal_id() == var && !waitingToAdd.contains(var)){
                if (favModel.isFav(i.getMal_id())){
                    favModel.delFav(i.getMal_id());
                    button.setImageResource(R.drawable.star_off);
                } else {
                    waitingToAdd.add(var);
                    Call<SingleInfo> call = animeApi.getAnime(var.toString());
                    call.enqueue(new Callback<SingleInfo>() {
                        @Override
                        public void onResponse(Call<SingleInfo> call, Response<SingleInfo> response) {
                            SingleInfo info = response.body();
                            favModel.addFav(info);
                            waitingToAdd.remove(var);
                            button.setImageResource(R.drawable.star_on);
                        }
                        @Override
                        public void onFailure(Call<SingleInfo> call, Throwable t) {
                        }
                    });
                }
            }
        }
    }
    public FavorisList getFavorisList() {
        return favModel;
    }
    public List<Anime_info> getDataFromApi() {
        return dataFromApi;
    }
    public void setDataFromApi(List<Anime_info> data) {
        dataFromApi = data;
    }

    public String getName() {
        return userName;
    }
}
