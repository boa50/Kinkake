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
import br.com.boa50.kinkake.adapter.MusicaAdapter;
import br.com.boa50.kinkake.model.Musica;

public class MusicasFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Musica> musicas;

    public MusicasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musicas, container, false);

        musicas = Musica.getListaMusicasTeste();

        listView = (ListView) view.findViewById(R.id.lv_musicas);
        adapter = new MusicaAdapter(getActivity(), musicas);
        listView.setAdapter(adapter);

        return view;
    }

}