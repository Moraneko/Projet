package com.vogella.android.projet.java.activity;

import com.vogella.android.projet.java.activity.model.ReponseAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JikkanAPI {
    @GET("top/anime")
   // @GET("top/anime/1/airing")
    //@GET("/top/anime/1/upcoming")
    Call<ReponseAPI> getListAnime();
}