package br.com.boa50.kinkake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasAdicionarActivity;
import br.com.boa50.kinkake.adapter.MusicaReservadaAdapter;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;
import br.com.boa50.kinkake.util.PessoaUtil;

public class MusicasPorPessoaFragment extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView textViewVazio;
    private FloatingActionButton fabAddMusica;
    private static ArrayList<Musica> musicas;
    private ArrayList<Musica> musicasParaExcluir;
    private ArrayList<Integer> posicoesViewsSelecionadas;
    private ActionBar toolbar;
    private MenuItem itemExcluir;

    public MusicasPorPessoaFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_fab, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_fab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        fabAddMusica = (FloatingActionButton) view.findViewById(R.id.fab_fragment_recycle);
        textViewVazio = (TextView) view.findViewById(R.id.tv_fragment_recycle_fab);
        if(musicas == null)
            musicas = new ArrayList<>();
        adapter = new MusicaReservadaAdapter(musicas);
        recyclerView.setAdapter(adapter);
        musicasParaExcluir = new ArrayList<>();
        posicoesViewsSelecionadas = new ArrayList<>();
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        setHasOptionsMenu(true);
        PessoaUtil.setAdapterMusicasPessoa(adapter);
        mostrarFabDelay();

        fabAddMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MusicasAdicionarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_reservadas, menu);
        super.onCreateOptionsMenu(menu, inflater);

        itemExcluir = menu.findItem(R.id.item_excluir);
        itemExcluir.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_excluir:
                excluirMusicasSelecionadas();
                return true;
            case android.R.id.home:
                gerenciarVoltar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void itemLongClickListener(View view, int position){
        adicionarMusicaExcluir(view, position);
        itemExcluir.setVisible(true);
        toolbar.setTitle(R.string.tituloMusicasPessoaExcluir);
    }

    public void itemClickListener(View view, int position){
        if(!musicasParaExcluir.isEmpty()){
            if(posicoesViewsSelecionadas.contains(position)){
                removerPessoasExcluir(view, position);
                if(musicasParaExcluir.isEmpty())
                    voltarEstadoTela();
            }else{
                adicionarMusicaExcluir(view, position);
            }
        }
    }

    private void excluirMusicasSelecionadas(){
        musicas.removeAll(musicasParaExcluir);
        PessoaUtil.removerMusicasPessoaAtiva(musicasParaExcluir);
        verificarListaMusicasVazia();
        adapter.notifyDataSetChanged();
        PessoaUtil.getAdapterPessoa().notifyDataSetChanged();
        voltarEstadoTela();
    }

    private void gerenciarVoltar(){
        if(musicasParaExcluir.isEmpty()){
            getActivity().onBackPressed();
            MusicasReservadasFragment.mostrarFabDelay();
        }else{
            voltarEstadoTela();
        }
    }

    private void voltarEstadoTela(){
        for(Integer posicao : posicoesViewsSelecionadas){
            recyclerView.getChildAt(posicao).setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background));
        }
        musicasParaExcluir.clear();
        posicoesViewsSelecionadas.clear();
        itemExcluir.setVisible(false);
        toolbar.setTitle("Músicas de " + PessoaUtil.getPessoaAtiva().getNome());
    }

    private void adicionarMusicaExcluir(View view, int position){
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.highlightList));
        posicoesViewsSelecionadas.add(position);
        musicasParaExcluir.add(musicas.get(position));
    }

    private void removerPessoasExcluir(View view, int position){
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background));
        posicoesViewsSelecionadas.remove(Integer.valueOf(position));
        musicasParaExcluir.remove(musicas.get(position));
    }

    public static void setMusicas(ArrayList<Integer> codigosMusicas){
        if(musicas == null)
            musicas = new ArrayList<>();
        else
            musicas.clear();

        musicas.addAll(MusicaUtil.getMusicasPorCodigos(codigosMusicas));
        if(PessoaUtil.getAdapterMusicasPessoa() != null)
            PessoaUtil.getAdapterMusicasPessoa().notifyDataSetChanged();
        if(PessoaUtil.getAdapterPessoa() != null)
            PessoaUtil.getAdapterPessoa().notifyDataSetChanged();
    }

    private void verificarListaMusicasVazia(){
        if(musicas.isEmpty())
            textViewVazio.setText(R.string.reservadasMusicasVazio);
        else
            textViewVazio.setText("");
    }

    private void mostrarFabDelay(){
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fabAddMusica.show();
            }
        }, 500);
    }

    @Override
    public void onPause() {
        super.onPause();
        fabAddMusica.hide();
    }

    @Override
    public void onResume() {
        super.onResume();
        verificarListaMusicasVazia();
        mostrarFabDelay();
    }
}
