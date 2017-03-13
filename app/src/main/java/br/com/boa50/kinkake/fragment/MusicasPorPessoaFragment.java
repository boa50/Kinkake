package br.com.boa50.kinkake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasAdicionarActivity;
import br.com.boa50.kinkake.adapter.MusicaReservadaAdapter;
import br.com.boa50.kinkake.model.Musica;

public class MusicasPorPessoaFragment extends Fragment{

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Musica> musicas;
    private FloatingActionButton fabAddMusica;

    public MusicasPorPessoaFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musicas_reservadas, container, false);

        listView = (ListView) view.findViewById(R.id.lv_fragmento);
        fabAddMusica = (FloatingActionButton) view.findViewById(R.id.fab_add);
        musicas = new ArrayList<>();
        adapter = new MusicaReservadaAdapter(getActivity(), musicas);
        listView.setAdapter(adapter);

        fabAddMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MusicasAdicionarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
