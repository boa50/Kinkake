package br.com.boa50.kinkake.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.boa50.kinkake.R;
import br.com.boa50.kinkake.activity.MusicasCantorActivity;
import br.com.boa50.kinkake.adapter.CantorAdapter;
import br.com.boa50.kinkake.model.Cantor;
import br.com.boa50.kinkake.util.CantorUtil;
import br.com.boa50.kinkake.util.ExtrasNomes;

public class CantoresFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private ArrayList<Cantor> cantores;

    public CantoresFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycle, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        cantores = new ArrayList<>();
        cantores.addAll(CantorUtil.getTodosCantores());

        adapter = new CantorAdapter(cantores);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void filtrar(String texto, boolean apenasFavoritas){
        if(cantores == null)
            cantores = new ArrayList<>();

        cantores.clear();
        cantores.addAll(CantorUtil.filtrar(texto, apenasFavoritas));
        adapter.notifyDataSetChanged();
    }

    public void itemClickListener(int position){
        Cantor cantor = cantores.get(position);
        Intent intent = new Intent(getActivity(), MusicasCantorActivity.class);
        intent.putExtra(ExtrasNomes.ID_CANTOR.getValor(), cantor.getId());
        intent.putExtra(ExtrasNomes.NOME_CANTOR.getValor(), cantor.getNome());
        intent.putExtra(ExtrasNomes.LISTA_MUSICAS_CANTOR.getValor(), cantor.getCodigosMusicas());
        startActivity(intent);
    }
}
