package com.vogella.android.projet.java.activity.Controler;

import android.view.View;
import android.widget.ImageButton;

import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.Model.Anime_info;
import com.vogella.android.projet.java.activity.Model.FavorisList;

import java.util.List;

public class MainPageControler {
    private View v;
    private String userName;
    private FavorisList favModel;
    private List<Anime_info> dataFromApi;
    public MainPageControler(View v,String name){
        this.userName = name;
        this.v = v;
        this.favModel = new FavorisList();
    }

    public void clickAnime(Object id, View v) {
        Integer animeId = Integer.parseInt(id.toString());
        System.out.println(animeId);
    }
    public void clickFavoris(Object id, View view) {
        Integer var = Integer.parseInt(id.toString());
        ImageButton button = view.findViewById(R.id.imageButton);
        for(Anime_info i : dataFromApi) {
            if( i.getMal_id() == var){
                if (favModel.isFav(i.getMal_id())){
                    favModel.delFav(i.getMal_id());
                    button.setImageResource(R.drawable.star_off);
                } else {
                    favModel.addFav(i);
                    button.setImageResource(R.drawable.star_on);
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
