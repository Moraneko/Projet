package activity.Controler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vogella.android.projet.R;
import activity.Model.SingleInfo;

public class AdapterInfo{
    View v;
    SingleInfo info;

    public AdapterInfo(View v,SingleInfo info){
        this.v=v;
        this.info=info;
    }
    public void setLayout(){
        TextView titre = v.findViewById(R.id.titreJap);
        TextView titreEng = v.findViewById(R.id.titreEnglish);
        TextView rang = v.findViewById(R.id.rang);
        TextView score = v.findViewById(R.id.score);
        TextView genre = v.findViewById(R.id.genre);
        TextView studio = v.findViewById(R.id.studio);
        TextView diver = v.findViewById(R.id.diver);
        TextView synopsis = v.findViewById(R.id.synopsis);
        ImageView img = v.findViewById(R.id.imageAnime);

        Picasso.with(v.getContext()).load(info.getImage_url()).into(img);
        titre.setText("Titre: "+info.getTitle());
        if(info.getTitle_english() != null){
            titreEng.setText("Titre anglais: "+info.getTitle_english());
        }else {titreEng.setText("Titre anglais: "+info.getTitle());}
        rang.setText("Rang: "+info.getRank());
        score.setText("Score: "+info.getScore());
        genre.setText("Genre:\n"+info.getGenreStr());
        studio.setText("Studio:\n"+info.getStudiosStr());
        diver.setText("Informations supplémentaires:\n      Source: "+info.getSource()+"\n      Type: "+info.getType()+
                "\n      Nb Episode: "+info.getEpisodes()+"\n      Durée par ep:"+info.getDuration()+
                "\n      Age conseillé: "+info.getRating()+"\n      Popularité: "+info.getPopularity());
        synopsis.setText("Synopsis: "+info.getSynopsis());
    }

}
