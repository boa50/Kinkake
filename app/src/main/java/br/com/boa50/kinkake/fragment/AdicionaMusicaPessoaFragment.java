package br.com.boa50.kinkake.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.adapter.AdicionarMusicaAdapter;
import br.com.boa50.kinkake.application.PessoaFirebase;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.model.MusicaSelecao;
import br.com.boa50.kinkake.model.Pessoa;
import br.com.boa50.kinkake.util.MusicaUtil;
import br.com.boa50.kinkake.util.VariaveisEstaticas;

public class AdicionaMusicaPessoaFragment extends Fragment{

    private RecyclerView.Adapter adapter;
    private ArrayList<Musica> musicas;
    private ArrayList<Musica> musicasFiltro;
    private ArrayList<MusicaSelecao> musicasSelecao;
    private ArrayList<Integer> codigosMusicasAdicionar;
    private Pessoa pessoaAtiva;
    private FloatingActionButton fabConfirma;

    public AdicionaMusicaPessoaFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_fab, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_fab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        fabConfirma = (FloatingActionButton) view.findViewById(R.id.fab_fragment_recycle);
        fabConfirma.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.green800)));
        fabConfirma.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_check));

        musicasSelecao = new ArrayList<>();
        musicasFiltro = new ArrayList<>();
        codigosMusicasAdicionar = new ArrayList<>();
        pessoaAtiva = VariaveisEstaticas.getPessoaAtiva();

        musicas = MusicaUtil.getMusicasDiferentesPorCodigos(pessoaAtiva.getCodigosMusicas());
        musicasFiltro.addAll(musicas);
        atualizarMusicasSelecao();

        adapter = new AdicionarMusicaAdapter(musicasSelecao);
        recyclerView.setAdapter(adapter);

        fabConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PessoaFirebase.adicionarMusicasPessoaAtiva(codigosMusicasAdicionar);
                getActivity().onBackPressed();
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

    public void atualizarMusicasAdicionar(int position){
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

        if(codigosMusicasAdicionar.isEmpty())
            fabConfirma.hide();
        else
            fabConfirma.show();
        adapter.notifyDataSetChanged();
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
