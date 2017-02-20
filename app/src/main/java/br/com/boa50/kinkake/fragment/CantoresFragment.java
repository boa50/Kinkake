package br.com.boa50.kinkake.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MainActivity;
import br.com.boa50.kinkake.activity.MusicasCantorActivity;
import br.com.boa50.kinkake.adapter.CantorAdapter;
import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.util.ExtrasNomes;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cantor cantor = cantores.get(i);

                Intent intent = new Intent(getActivity(), MusicasCantorActivity.class);
                intent.putExtra(ExtrasNomes.NOME_CANTOR.getValor(), cantor.getNome());
                intent.putExtra(ExtrasNomes.LISTA_MUSICAS_CANTOR.getValor(), cantor.getCodigosMusicas());
                startActivity(intent);
            }
        });

        return view;
    }

}
