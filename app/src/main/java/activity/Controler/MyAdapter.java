package activity.Controler;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vogella.android.projet.R;
import activity.Model.Anime_info;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Anime_info> values;
    private OnItemClickListener listener ;
    public interface OnItemClickListener {
        void onItemClick(Anime_info item);
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView imgInfo;
        public Context context;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            imgInfo = v.findViewById(R.id.Image_affiche);
            context = v.getContext();
        }
    }
    public void add(int position, Anime_info item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Anime_info> myDataset , OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent , int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Anime_info current_Anime = values.get(position);
        final String description = "Rank : "+current_Anime.getRank();
        final String name = current_Anime.getTitle();
        final String urlStr = current_Anime.getImage_url();
        holder.txtHeader.setText(name);
        holder.txtFooter.setText(description);
        Picasso.with(holder.context).load(urlStr).into(holder.imgInfo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(current_Anime);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}