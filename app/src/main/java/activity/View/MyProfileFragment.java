package activity.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.Controler.MainPageControler;
import activity.Controler.ProfileControler;

import androidx.fragment.app.Fragment;

public class MyProfileFragment extends Fragment {
    private MainPageControler mainControler;
    private ProfileControler profileControler;

    public MyProfileFragment(MainPageControler mainControler){
        this.mainControler = mainControler;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileControler = new ProfileControler(mainControler, inflater, container, savedInstanceState, getActivity());
        return profileControler.initView();
    }
}
