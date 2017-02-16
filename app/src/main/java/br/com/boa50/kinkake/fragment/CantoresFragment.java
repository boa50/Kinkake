package br.com.boa50.kinkake.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.boa50.kinkake.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CantoresFragment extends Fragment {


    public CantoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cantores, container, false);
    }

}
