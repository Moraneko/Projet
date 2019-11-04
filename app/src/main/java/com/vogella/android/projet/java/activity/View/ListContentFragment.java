package com.vogella.android.projet.java.activity.View;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.Model.Anime_info;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Provides UI for the view with List.
 */
public class ListContentFragment extends Fragment {

    List<Anime_info> dataFromApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Recuperer les arguments passer lors de la création du fragment
        Type collectionType = new TypeToken<List<Anime_info>>(){}.getType();
  //
        Bundle dataBundle = getArguments();
        dataFromApi = new Gson().fromJson(dataBundle.getString("Key1"), collectionType);

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), dataFromApi);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView description;
        public  Context context;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            context = itemView.getContext();
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        Context context;
        List<Anime_info> dataFromApi;
        /*private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final Drawable[] mPlaceAvators;*/
        public ContentAdapter(Context context, List<Anime_info> input) {
            this.dataFromApi = input;
            Resources resources = context.getResources();
            /*mPlaces = resources.getStringArray(R.;
            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvators = new Drawable[a.length()];
            for (int i = 0; i < mPlaceAvators.length; i++) {
                mPlaceAvators[i] = a.getDrawable(i);
            }
            a.recycle();*/
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Anime_info current_Anime =dataFromApi.get(position);
            Picasso.with(holder.context).load(current_Anime.getImage_url()).into(holder.avator);
            holder.name.setText(current_Anime.getTitle());
            holder.description.setText("Rang: "+current_Anime.getRank());
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
