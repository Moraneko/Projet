package com.vogella.android.projet.java.activity.Controler;

import android.view.View;

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

    public void clickAnime(Object id) {
        Integer animeId = Integer.parseInt(id.toString());
        System.out.println(animeId);
    }
    public void clickFavoris(Object id) {
        Integer var = Integer.parseInt(id.toString());
        for(Anime_info i : dataFromApi) {
            if( i.getMal_id() == var){
                if (favModel.isFav(i.getMal_id())){
                    favModel.delFav(i.getMal_id());
                } else {
                    favModel.addFav(i);
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
}
