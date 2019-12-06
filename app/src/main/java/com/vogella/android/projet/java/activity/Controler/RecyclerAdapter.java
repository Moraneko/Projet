package com.vogella.android.projet.java.activity.Controler;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.vogella.android.projet.java.activity.Model.Anime_info;
import com.vogella.android.projet.java.activity.View.ListContentFragment;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter to display recycler view.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<ListContentFragment.ViewHolder> {
    Context context;
    List<Anime_info> dataFromApi;
    // Set numbers of List in RecyclerView.
    private static final int LENGTH = 18;
    public RecyclerAdapter(Context context, List<Anime_info> input) {
        this.dataFromApi = input;
        Resources resources = context.getResources();
    }

    @Override
    public ListContentFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListContentFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ListContentFragment.ViewHolder holder, int position) {
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