package com.vogella.android.projet.java.activity.Controler;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieDataSet;
import com.vogella.android.projet.R;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileControler implements OnChartValueSelectedListener  {
    private MainPageControler mainContoler;
    private LayoutInflater inflater;
    private ViewGroup container;
    private  Bundle savedInstanceState;
    private RecyclerView recyclerView;
    private FragmentActivity activity;

    public ProfileControler(MainPageControler mainControler, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, FragmentActivity activity) {
        this.mainContoler = mainControler;
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        this.activity = activity;
    }

    public View initView() {
        View profileView = inflater.inflate(R.layout.fragment_profile, container, false);
        PieChart pieChart = profileView.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        ArrayList<Entry> yvalues = new ArrayList<>();
         yvalues.add(new Entry(8f, 0));
        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

        ArrayList<String> xvalues = new ArrayList<String>();
        xvalues.add("January");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(xvalues, dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.animateXY(1400, 1400);
        return profileView;
    }
    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null) {
            return;
        }
        System.out.println("VAL SELECTED + Value: " + e.getVal() + ", xIndex: " + e.getXIndex() + ", DataSet index: " + dataSetIndex);
    }@Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}
