package com.vogella.android.projet.java.activity.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JikkanAPI {
    @GET("top/anime/{page}")
    Call<ReponseAPI> getListAnime(@Path("page") String page);
    @GET("anime/{id}")
    Call<SingleInfo> getAnime(@Path("id") String id);
}