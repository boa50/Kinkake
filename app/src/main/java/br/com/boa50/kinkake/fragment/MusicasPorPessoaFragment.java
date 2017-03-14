package br.com.boa50.kinkake.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasAdicionarActivity;
import br.com.boa50.kinkake.adapter.MusicaReservadaAdapter;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.MusicaUtil;
import br.com.boa50.kinkake.util.PessoaUtil;

public class MusicasPorPessoaFragment extends Fragment{

    private ListView listView;
    private ArrayAdapter adapter;
    private static ArrayList<Musica> musicas;
    private FloatingActionButton fabAddMusica;
    private ArrayList<Musica> musicasParaExclusao;
    private MenuItem itemExcluir;

    public MusicasPorPessoaFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musicas_reservadas, container, false);

        listView = (ListView) view.findViewById(R.id.lv_fragmento);
        fabAddMusica = (FloatingActionButton) view.findViewById(R.id.fab_add);
        if(musicas == null)
            musicas = new ArrayList<>();
        adapter = new MusicaReservadaAdapter(getActivity(), musicas);
        listView.setAdapter(adapter);
        musicasParaExclusao = new ArrayList<>();

        setHasOptionsMenu(true);

        PessoaUtil.setAdapterMusicasPessoa(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green800));
                musicasParaExclusao.add(musicas.get(i));
                itemExcluir.setVisible(true);
                return false;
            }
        });

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
}
