package activity.Controler;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.vogella.android.projet.R;
import activity.Model.Anime_info;
import activity.Model.FavorisList;
import activity.View.AllAnimeFragment;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter to display recycler view.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<AllAnimeFragment.ViewHolder> {
    Context context;
    List<Anime_info> dataFromApi;
    private FavorisList favList;
    // Set numbers of List in RecyclerView.
    private static final int LENGTH = 50;
    public RecyclerAdapter(Context context, List<Anime_info> input, FavorisList favList) {
        this.dataFromApi = input;
        Resources resources = context.getResources();
        this.favList = favList;
    }

    @Override
    public AllAnimeFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllAnimeFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(AllAnimeFragment.ViewHolder holder, int position) {
        Anime_info current_Anime = dataFromApi.get(position);
        Picasso.with(holder.context).load(current_Anime.getImage_url()).into(holder.avator);
        holder.name.setText(current_Anime.getTitle());
        holder.description.setText("Rang: "+current_Anime.getRank());
        holder.addFav.setTag(current_Anime.getMal_id());
        holder.layoutContainer.setTag(current_Anime.getMal_id());
        holder.addFav.setImageResource(R.drawable.star_off);
        if(favList.isFav(current_Anime.getMal_id())){
            holder.addFav.setImageResource(R.drawable.star_on);
        }

    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }

}