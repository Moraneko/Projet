package com.vogella.android.projet.java.activity.View;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.Model.Anime_info;
import com.vogella.android.projet.java.activity.Model.JikkanAPI;
import com.vogella.android.projet.java.activity.Model.ReponseAPI;

import java.util.List;

/**
 * Provides UI for the view with List.
 */
public class ListContentFragment extends Fragment {
    private JikkanAPI api;
    List<Anime_info> dataFromApi;
    private Integer page =1;
    private Boolean apiEndCall = false;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.jikan.moe/v3/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        JikkanAPI animeApi = retrofit.create(JikkanAPI.class);
        this.api = animeApi;

        //Recuperer les arguments passer lors de la création du fragment
        // Type collectionType = new TypeToken<List<Anime_info>>(){}.getType();
        //
        //  Bundle dataBundle = getArguments();
        //dataFromApi = new Gson().fromJson(dataBundle.getString("Key1"), collectionType);

        View retView = inflater.inflate(R.layout.fragment_list_anime, container, false);
        recyclerView = retView.findViewById(R.id.my_recycler_view);
        appelApi();
        return retView;
    }

    private View appelApi() {
        Call<ReponseAPI> call = this.api.getListAnime(this.page.toString());
        call.enqueue(new Callback<ReponseAPI>() {
            @Override
            public void onResponse(Call<ReponseAPI> call, Response<ReponseAPI> response) {
                ReponseAPI restAnime = response.body();
                List<Anime_info> listAnime = restAnime.getResult();
                dataFromApi = listAnime;
                ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), dataFromApi);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
            @Override
            public void onFailure(Call<ReponseAPI> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView description;
        public CardView layoutContainer;
        public ImageButton addFav;
        public  Context context;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            layoutContainer= itemView.findViewById(R.id.card);
            addFav = itemView.findViewById(R.id.imageButton);
            context = itemView.getContext();
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        Context context;
        List<Anime_info> dataFromApi;
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        public ContentAdapter(Context context, List<Anime_info> input) {
            this.dataFromApi = input;
            Resources resources = context.getResources();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Anime_info current_Anime = dataFromApi.get(position);
            Picasso.with(holder.context).load(current_Anime.getImage_url()).into(holder.avator);
            holder.name.setText(current_Anime.getTitle());
            holder.description.setText("Rang: "+current_Anime.getRank());
            holder.addFav.setTag(current_Anime.getMal_id());
            holder.layoutContainer.setTag(current_Anime.getMal_id());

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
