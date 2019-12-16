package activity.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.Controler.MainPageControler;
import activity.Controler.QuitControler;
import androidx.fragment.app.Fragment;

public class QuitFragment extends Fragment {
    private MainPageControler mainControler;
    private QuitControler quitControler;

    public QuitFragment(MainPageControler mainControler){
        this.mainControler = mainControler;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        quitControler = new QuitControler(mainControler, inflater, container, savedInstanceState, getActivity());
        return quitControler.initView();
    }
}
