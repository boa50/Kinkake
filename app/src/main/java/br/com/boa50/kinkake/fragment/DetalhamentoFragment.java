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
public class DetalhamentoFragment extends Fragment {


    public DetalhamentoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalhamento, container, false);
    }

}
