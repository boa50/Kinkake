package br.com.boa50.kinkake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.adapter.AdicionarMusicaAdapter;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.model.MusicaSelecao;
import br.com.boa50.kinkake.util.MusicaUtil;

public class AdicionaMusicaPessoaFragment extends Fragment{

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Musica> musicas;
    private ArrayList<MusicaSelecao> musicasSelecao;
    private FloatingActionButton fabConfirma;

    public AdicionaMusicaPessoaFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adiciona_musica_pessoa, container, false);

        listView = (ListView) view.findViewById(R.id.lv_fragmento);
        fabConfirma = (FloatingActionButton) view.findViewById(R.id.fab_confirma);
        musicas = new ArrayList<>();
        musicas.addAll(MusicaUtil.getTodasMusicas());
        musicasSelecao = new ArrayList<>();

        preencherMusicasSelecao();

        adapter = new AdicionarMusicaAdapter(getActivity(), musicasSelecao);
        listView.setAdapter(adapter);

        fabConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                musicasSelecao.get(i).setSelecao(!musicasSelecao.get(i).isSelecao());
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void preencherMusicasSelecao(){
        for(Musica musica : musicas){
            musicasSelecao.add(new MusicaSelecao(musica));
        }
    }
}
