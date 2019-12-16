package activity.Controler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vogella.android.projet.R;

import androidx.fragment.app.FragmentActivity;

public class QuitControler {
    private MainPageControler mainControler;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;
    private  FragmentActivity activity;
    public QuitControler(MainPageControler mainControler, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, FragmentActivity activity) {
        this.mainControler = mainControler;
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        this.activity = activity;
    }

    public View initView() {
        View quitView = inflater.inflate(R.layout.fragment_quit, container, false);
        return quitView;
    }
}
