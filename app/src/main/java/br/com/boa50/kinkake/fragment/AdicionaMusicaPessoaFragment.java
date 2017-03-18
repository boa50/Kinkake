package br.com.boa50.kinkake.fragment;

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
    private ArrayList<Musica> musicasFiltro;
    private ArrayList<MusicaSelecao> musicasSelecao;
    private ArrayList<Integer> codigosMusicasAdicionar;
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
        musicasFiltro = new ArrayList<>();
        codigosMusicasAdicionar = new ArrayList<>();
        pessoaAtiva = PessoaUtil.getPessoaAtiva();

        musicas = MusicaUtil.getMusicasDiferentesPorCodigos(pessoaAtiva.getCodigosMusicas());
        musicasFiltro.addAll(musicas);
        atualizarMusicasSelecao();

        adapter = new AdicionarMusicaAdapter(getActivity(), musicasSelecao);
        listView.setAdapter(adapter);

        fabConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoaAtiva.getCodigosMusicas().addAll(codigosMusicasAdicionar);
                PessoaUtil.atulizarListaMusicasPessoaAtiva();
                MusicasPorPessoaFragment.setMusicas(pessoaAtiva.getCodigosMusicas());
                getActivity().onBackPressed();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                musicasSelecao.get(position).setSelecao(!musicasSelecao.get(position).isSelecao());
                atualizarMusicasAdicionar(position);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void atualizarMusicasSelecao(){
        musicasSelecao.clear();

        for(Musica musica : musicas){
            if(codigosMusicasAdicionar.contains(musica.getCodigo()))
                musicasSelecao.add(new MusicaSelecao(musica, true));
            else
                musicasSelecao.add(new MusicaSelecao(musica));
        }
    }

    private void atualizarMusicasAdicionar(int position){
        MusicaSelecao musicaSelecao = musicasSelecao.get(position);

        if(musicaSelecao.isSelecao()){
            codigosMusicasAdicionar.add(musicaSelecao.getMusica().getCodigo());
        }else{
            for(Integer codigo : codigosMusicasAdicionar){
                if(codigo.equals(musicaSelecao.getMusica().getCodigo())){
                    codigosMusicasAdicionar.remove(codigo);
                    break;
                }
            }
        }
    }

    public void filtrar(String texto, boolean apenasFavoritas){
        if(musicas == null)
            musicas = new ArrayList<>();

        musicas.clear();
        musicas.addAll(MusicaUtil.filtrar(musicasFiltro, texto, apenasFavoritas));
        atualizarMusicasSelecao();
        adapter.notifyDataSetChanged();
    }
}
