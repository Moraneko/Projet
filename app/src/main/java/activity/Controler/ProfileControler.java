package activity.Controler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieDataSet;
import com.vogella.android.projet.R;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

import activity.Model.SingleInfo;
import activity.Model.SingleInfo_Genre;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileControler{
    private MainPageControler mainContoler;
    private LayoutInflater inflater;
    private ViewGroup container;
    private  Bundle savedInstanceState;
    private RecyclerView recyclerView;
    private FragmentActivity activity;
    private int max = -1;
    private String maxName = "";
    private TextView genrePref;

    public ProfileControler(MainPageControler mainControler, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, FragmentActivity activity) {
        this.mainContoler = mainControler;
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        this.activity = activity;
    }

    public View initView() {
        HashMap<Integer, SingleInfo> favList = this.mainContoler.getFavorisList().getCurrentFavList();
        HashMap<String, Integer> dataList = new HashMap<>();
        for( int i : favList.keySet()){
            for (SingleInfo_Genre j : favList.get(i).getGenre()){
                if(dataList.keySet().contains(j.getName())){
                    dataList.put(j.getName(),dataList.get(j.getName())+1);
                } else {
                    dataList.put(j.getName(),1);
                }
            }
        }
        View profileView = inflater.inflate(R.layout.fragment_profile, container, false);
        PieChart pieChart = profileView.findViewById(R.id.piechart);
        genrePref = profileView.findViewById(R.id.genrePref);
        pieChart.setUsePercentValues(true);
        ArrayList<Entry> yvalues = new ArrayList<>();
        int xindex = 0;
        for(String i : dataList.keySet()){
            yvalues.add(new Entry(dataList.get(i), xindex));
            if(dataList.get(i)>this.max){
                this.max = dataList.get(i);
                this.maxName = i;
            }
            xindex++;
        }
        PieDataSet dataSet = new PieDataSet(yvalues, "Genres");
        ArrayList<String> xvalues = new ArrayList<String>();
        for(String i : dataList.keySet()){
            xvalues.add(i);
        }
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(xvalues, dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.animateXY(1400, 1400);
        ProfileControler self = this;
        String text = "Genre préféré :\n" + self.maxName + "("+ self.max +")";
        genrePref.setText(text);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if(e!=null){
                    genrePref.setText(xvalues.get(e.getXIndex())+"\n" + Math.round(e.getVal()));
                } else {
                    String text = "Genre préféré :\n" + self.maxName + "("+ self.max +")";
                    genrePref.setText(text);
                }
            }

            @Override
            public void onNothingSelected() {
                String text = "Genre préféré :\n" + self.maxName + "("+ self.max +")";
                genrePref.setText(text);
            }
        });
        TextView titre = profileView.findViewById(R.id.titreOfPage);
        titre.setText("Page de " + mainContoler.getName());
        TextView nbEpisode = profileView.findViewById(R.id.NbTotal);
        Integer total = 0;
        for (SingleInfo i : favList.values()) {
            total += i.getEpisodes();
        }
        nbEpisode.setText(total.toString());
        return profileView;
    }
}
