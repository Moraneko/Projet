package activity.View;

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
import activity.Controler.FragListControler;
import activity.Controler.MainPageControler;


public class AllAnimeFragment extends Fragment {
    private MainPageControler mainControler;
    private FragListControler fragListContrler;
    private MainPage mainPage;

    public AllAnimeFragment(MainPageControler mainControler, MainPage mainPage){
        this.mainControler = mainControler;
        this.mainPage = mainPage;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragListContrler = new FragListControler(mainControler, inflater, container, savedInstanceState, getActivity(), mainPage);
        View listView = fragListContrler.initView();

        return listView;
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
