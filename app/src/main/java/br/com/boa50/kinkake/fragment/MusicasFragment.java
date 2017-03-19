package br.com.boa50.kinkake.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.DetalhamentoActivity;
import br.com.boa50.kinkake.adapter.MusicaAdapter;
import br.com.boa50.kinkake.application.ConfiguracaoFirebase;
import br.com.boa50.kinkake.application.MusicaListeners;
import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.model.Musica;
import br.com.boa50.kinkake.util.ExtrasNomes;
import br.com.boa50.kinkake.util.MusicaUtil;

public class MusicasFragment extends Fragment {

    private static ArrayAdapter adapter;
    private static ArrayList<Musica> musicas;
    private Cantor cantor;

    private static DatabaseReference databaseReference;

    public MusicasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musicas, container, false);
        ListView listView;

        if(musicas == null){
            musicas = new ArrayList<>();
        }

        if(cantor != null) {
            musicas.clear();
            musicas.addAll(MusicaUtil.listaMusicasPorCantor(cantor));
        }else{
            musicas.clear();
            musicas.addAll(MusicaUtil.getTodasMusicas());
        }

        listView = (ListView) view.findViewById(R.id.lv_musicas);
        if(adapter == null)
            adapter = new MusicaAdapter(getActivity(), musicas);
        listView.setAdapter(adapter);

        if(databaseReference == null){
            databaseReference = ConfiguracaoFirebase.getReferenciaMusica();
            databaseReference.addChildEventListener(MusicaListeners.getListenerMusicas(adapter));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Musica musica = musicas.get(i);

                Intent intent = new Intent(getActivity(), DetalhamentoActivity.class);
                intent.putExtra(ExtrasNomes.MUSICA.getValor(), musica);
                startActivity(intent);
            }
        });

        return view;
    }

    public void filtrar(String texto, boolean apenasFavoritas){
        if(musicas == null)
            musicas = new ArrayList<>();

        musicas.clear();
        musicas.addAll(MusicaUtil.filtrar(texto, apenasFavoritas));
        adapter.notifyDataSetChanged();
    }

    public void filtrar(Integer idCantor, boolean apenasFavoritas){
        if(musicas == null)
            musicas = new ArrayList<>();

        musicas.clear();
        musicas.addAll(MusicaUtil.filtrar(idCantor, apenasFavoritas));
        adapter.notifyDataSetChanged();
    }

    public void setCantor(Cantor cantor) {
        this.cantor = cantor;
    }
}
