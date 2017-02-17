package br.com.boa50.kinkake.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.adapter.CantorAdapter;
import br.com.boa50.kinkake.model.Cantor;

public class CantoresFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Cantor> cantores;

    public CantoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cantores, container, false);

        cantores = Cantor.getCantoresListaTeste();

        listView = (ListView) view.findViewById(R.id.lv_cantores);
        adapter = new CantorAdapter(getActivity(), cantores);
        listView.setAdapter(adapter);

        return view;
    }

}
