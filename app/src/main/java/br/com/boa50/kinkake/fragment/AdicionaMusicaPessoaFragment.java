package br.com.boa50.kinkake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.MusicaUtil;
import br.com.boa50.kinkake.util.PessoaUtil;

public class AdicionaMusicaPessoaFragment extends Fragment{

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Musica> musicas;
    private ArrayList<MusicaSelecao> musicasSelecao;
    private FloatingActionButton fabConfirma;
    private Pessoa pessoaAtiva;

    public AdicionaMusicaPessoaFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adiciona_musica_pessoa, container, false);

        listView = (ListView) view.findViewById(R.id.lv_fragmento);
        fabConfirma = (FloatingActionButton) view.findViewById(R.id.fab_confirma);
        musicasSelecao = new ArrayList<>();
        pessoaAtiva = PessoaUtil.getPessoaAtiva();

        musicas = MusicaUtil.getMusicasDiferentesPorCodigos(pessoaAtiva.getCodigosMusicas());

        preencherMusicasSelecao();

        Log.i("qwerty", String.valueOf(musicas.size()));

        adapter = new AdicionarMusicaAdapter(getActivity(), musicasSelecao);
        listView.setAdapter(adapter);

        fabConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(MusicaSelecao selecao : musicasSelecao){
                    if(selecao.isSelecao())
                        pessoaAtiva.getCodigosMusicas().add(selecao.getMusica().getCodigo());
                }

                MusicasPorPessoaFragment.setMusicas(pessoaAtiva.getCodigosMusicas());
                getActivity().onBackPressed();
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
