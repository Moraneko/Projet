package com.vogella.android.projet.java.activity.View;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.Controler.FragListControler;
import com.vogella.android.projet.java.activity.Controler.MainPageControler;

/**
 * Provides UI for the view with List.
 */
public class AllAnimeFragment extends Fragment {
    private Boolean apiEndCall = false;
    private MainPageControler mainControler;
    private FragListControler fragListContrler;

    public AllAnimeFragment(MainPageControler mainControler){
        this.mainControler = mainControler;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragListContrler = new FragListControler(mainControler, inflater, container, savedInstanceState, getActivity());
        return fragListContrler.initView();
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
}
